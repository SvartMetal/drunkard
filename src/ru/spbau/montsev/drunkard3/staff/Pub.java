package ru.spbau.montsev.drunkard3.staff;

import ru.spbau.montsev.drunkard3.Game;
import ru.spbau.montsev.drunkard3.GameObject;
import ru.spbau.montsev.drunkard3.Settings;
import ru.spbau.montsev.drunkard3.actors.Drunkard;
import ru.spbau.montsev.drunkard3.boards.Board;
import ru.spbau.montsev.drunkard3.fields.Field;
import ru.spbau.montsev.drunkard3.interfaces.Generator;
import ru.spbau.montsev.drunkard3.interfaces.Moveable;

/**
 * @author Montsev Mikhail
 *         Date: 26.05.13
 *         Time: 22:29
 */
public class Pub extends Static implements Generator, Moveable {
    private int bornTimer = 0;

    public Pub(Field f) {
        super(f);
    }

    @Override
    public GameObject generate() {
        Board board = getField().getBoard();
        return new Drunkard(new Bottle(), board.getField(Settings.DRUNKARD_INIT_COORD_X,
                Settings.DRUNKARD_INIT_COORD_Y));
    }

    @Override
    public Field move() {
        if (getTime() > Game.getInstance().getTime()) return null;
        super.move();
        if (bornTimer != 0) {
            bornTimer--;
            return null;
        }
        bornTimer = Settings.DRUNKARD_BORN_LATENCY;
        Board board = getField().getBoard();
        return board.getField(Settings.DRUNKARD_INIT_COORD_X, Settings.DRUNKARD_INIT_COORD_Y);
    }

    @Override
    public String toString() {
        return Settings.PUB;
    }

    public void setBornTimer(int time) {
        if (time < 0) return;
        bornTimer = time;
    }
}
