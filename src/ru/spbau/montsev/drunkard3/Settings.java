package ru.spbau.montsev.drunkard3;

/**
 * @author Montsev Mikhail
 *         Date: 26.05.13
 *         Time: 0:37
 */
public class Settings {
    public static final int BOARD_SIZE = 17;
    public static final int LANTERN_LIGHT_RADIUS = 3;
    public static final double BOTTLE_DROP_PROBABILITY = 1.0 / 30;

    public static final int LANTERN_COORD_X = 11;
    public static final int LANTERN_COORD_Y = 4;
    public static final int COLUMN_COORD_X = 8;
    public static final int COLUMN_COORD_Y = 8;
    public static final int POLICE_STATION_COORD_X = 16;
    public static final int POLICE_STATION_COORD_Y = 4;

    public static final int PUB_COORD_X = 11;
    public static final int PUB_COORD_Y = 0;
    public static final int DRUNKARD_BORN_LATENCY = 20;

    public static final int GLASS_POINT_COORD_X = 0;
    public static final int GLASS_POINT_COORD_Y = 5;
    public static final int HOBO_RETURN_LATENCY = 30;

    public static final int DRUNKARD_LYING_TIME = 5;
    public static final int DRUNKARD_SLEEPING_TIME = 5;
    public static final int DRUNKARD_INIT_COORD_X = 11;
    public static final int DRUNKARD_INIT_COORD_Y = 1;

    public static final String DRUNKARD = " D ";
    public static final String LAYING_DRUNKARD = " & ";
    public static final String SLEEPING_DRUNKARD = " Z ";
    public static final String COLUMN = " C ";
    public static final String POLICE_MAN = " P ";
    public static final String HOBO = " z ";
    public static final String PUB = " T ";
    public static final String GLASS_POINT = " R ";
    public static final String BOTTLE = " B ";
    public static final String POLICE_STATION = " S ";
    public static final String LANTERN = " L ";
    public static final String EMPTY_FIELD = " . ";
    public static final String BORDER_FIELD = "   ";
    public static final String FAKE_FIELD = "___";

    public static final String BOARD_TYPE = "ru.spbau.montsev.drunkard3.boards.HexagonalBoard";
    //    public static final String BOARD_TYPE = "ru.spbau.montsev.drunkard3.boards.SquareBoard";
    public static final Object[] BOARD_CONSTRUCTOR_PARAMETERS = {BOARD_SIZE, BOARD_SIZE};
    public static final Class<?>[] BOARD_CONSTRUCTOR_CLASSES = {int.class, int.class};

}
