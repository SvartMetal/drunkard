package ru.spbau.montsev.drunkard3;

import ru.spbau.montsev.drunkard3.actors.Drunkard;
import ru.spbau.montsev.drunkard3.actors.Hobo;
import ru.spbau.montsev.drunkard3.actors.PoliceMan;
import ru.spbau.montsev.drunkard3.boards.Board;
import ru.spbau.montsev.drunkard3.boards.BoardFactory;
import ru.spbau.montsev.drunkard3.fields.Field;
import ru.spbau.montsev.drunkard3.staff.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Montsev Mikhail
 *         Date: 09.03.13
 *         Time: 21:03
 */
public class Game {
    private Board board;
    private int time = 0;

    private static Game instance = new Game(BoardFactory.getBoard(Settings.BOARD_TYPE,
            Settings.BOARD_CONSTRUCTOR_CLASSES, Settings.BOARD_CONSTRUCTOR_PARAMETERS));

    public static Game getInstance() {
        return instance;
    }

    private Game(Board b) {
        board = b;
        initialize();
    }

    public void nextState() {
        for (Field field : board) {
            GameRules.performAction(field, field.move());
        }
        time++;
    }

    public void printState() {
        board.printState();
    }

    private void initialize() {
        initField(Pub.class, Settings.PUB_COORD_X, Settings.PUB_COORD_Y);
        Pub pub = (Pub) board.getField(Settings.PUB_COORD_X, Settings.PUB_COORD_Y).getGameObject();
        pub.setBornTimer(Settings.DRUNKARD_BORN_LATENCY);

        board.setField(new Drunkard(new Bottle(), board.getField(Settings.DRUNKARD_INIT_COORD_X,
                Settings.DRUNKARD_INIT_COORD_Y)), Settings.DRUNKARD_INIT_COORD_X, Settings.DRUNKARD_INIT_COORD_Y);

        initField(Lantern.class, Settings.LANTERN_COORD_X, Settings.LANTERN_COORD_Y);

        initField(PoliceStation.class, Settings.POLICE_STATION_COORD_X, Settings.POLICE_STATION_COORD_Y);
        GameObject go = board.getField(Settings.POLICE_STATION_COORD_X,
                Settings.POLICE_STATION_COORD_Y).getGameObject();
        PoliceStation policeStation = (PoliceStation) go;
        policeStation.setPoliceMan(new PoliceMan(policeStation.getField()));

        initField(GlassPoint.class, Settings.GLASS_POINT_COORD_X, Settings.GLASS_POINT_COORD_Y);
        go = board.getField(Settings.GLASS_POINT_COORD_X, Settings.GLASS_POINT_COORD_Y).getGameObject();
        GlassPoint glassPoint = (GlassPoint) go;
        glassPoint.setHobo(new Hobo(glassPoint.getField()));

        board.setLighted(Settings.LANTERN_COORD_X, Settings.LANTERN_COORD_Y,
                Settings.LANTERN_LIGHT_RADIUS);
        initField(Column.class, Settings.COLUMN_COORD_X, Settings.COLUMN_COORD_Y);
    }

    private void initField(Class<?> gameObjectClass, int x, int y) {
        try {
            Constructor<?> constructor = gameObjectClass.getConstructor(Field.class);
            board.setField((GameObject) constructor.newInstance(board.getField(x, y)), x, y);
        } catch (NoSuchMethodException | InvocationTargetException |
                InstantiationException | IllegalAccessException ignored) {
        }
    }

    public int getTime() {
        return time;
    }

}
