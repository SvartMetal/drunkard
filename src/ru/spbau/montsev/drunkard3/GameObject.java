package ru.spbau.montsev.drunkard3;


import ru.spbau.montsev.drunkard3.fields.Field;
import ru.spbau.montsev.drunkard3.interfaces.Moveable;

/**
 * @author Montsev Mikhail
 *         Date: 09.03.13
 *         Time: 21:05
 */
public class GameObject implements Moveable {
    private Field field;
    private int time = 0;

    public GameObject(Field f) {
        field = f;
    }

    public int getTime() {
        return time;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field f) {
        field = f;
    }

    @Override
    public Field move() {
        time = Game.getInstance().getTime();
        time++;
        return null;
    }
}
