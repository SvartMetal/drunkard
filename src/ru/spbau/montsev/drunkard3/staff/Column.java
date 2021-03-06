package ru.spbau.montsev.drunkard3.staff;

import ru.spbau.montsev.drunkard3.Settings;
import ru.spbau.montsev.drunkard3.fields.Field;

/**
 * @author Montsev Mikhail
 *         Date: 10.03.13
 *         Time: 1:32
 */
public class Column extends Static {

    public Column(Field f) {
        super(f);
    }

    @Override
    public String toString() {
        return Settings.COLUMN;
    }
}
