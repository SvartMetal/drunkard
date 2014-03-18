package ru.spbau.montsev.drunkard3.staff;

import ru.spbau.montsev.drunkard3.Settings;
import ru.spbau.montsev.drunkard3.actors.Moving;
import ru.spbau.montsev.drunkard3.fields.Field;
import ru.spbau.montsev.drunkard3.interfaces.Comprised;
import ru.spbau.montsev.drunkard3.interfaces.Containing;

/**
 * @author Montsev Mikhail
 *         Date: 10.03.13
 *         Time: 1:22
 */
public class Bottle extends Moving implements Comprised {
    private Containing owner;

    public Bottle() {
        super(null);
    }

    public Containing getOwner() {
        return owner;
    }

    public void setOwner(Containing o) {
        owner = o;
    }

    @Override
    public Field move() {
        return null;
    }

    @Override
    public String toString() {
        return Settings.BOTTLE;
    }
}
