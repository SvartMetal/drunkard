package ru.spbau.montsev.drunkard3.actors;

import ru.spbau.montsev.drunkard3.GameObject;
import ru.spbau.montsev.drunkard3.fields.Field;

/**
 * @author Montsev Mikhail
 *         Date: 09.03.13
 *         Time: 23:18
 */
public abstract class Moving extends GameObject {
    public Moving(Field f) {
        super(f);
    }
}
