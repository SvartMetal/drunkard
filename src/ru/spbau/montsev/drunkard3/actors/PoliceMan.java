package ru.spbau.montsev.drunkard3.actors;

import ru.spbau.montsev.drunkard3.Game;
import ru.spbau.montsev.drunkard3.Settings;
import ru.spbau.montsev.drunkard3.fields.Field;
import ru.spbau.montsev.drunkard3.interfaces.Detainable;
import ru.spbau.montsev.drunkard3.interfaces.Detaining;
import ru.spbau.montsev.drunkard3.staff.PoliceStation;
import ru.spbau.montsev.drunkard3.util.PolicePathSearcher;

/**
 * @author Montsev Mikhail
 *         Date: 10.03.13
 *         Time: 1:21
 */
public class PoliceMan extends Actor implements Detaining {
    private Detainable prisoner;

    public PoliceMan(Field field) {
        super(field);
    }

    public void arrest(Detainable a) {
        prisoner = a;
    }

    @Override
    public Field move() {
        if (getTime() > Game.getInstance().getTime()) return null;

        super.move();
        if (prisoner == null) {
            PolicePathSearcher pathSearcher = new PolicePathSearcher(getField(), Drunkard.class);
            Field result = pathSearcher.getHint();
            if (result == null && getField().getGameObject() instanceof PoliceMan) {
                return new PolicePathSearcher(getField(), PoliceStation.class).getHint();
            }
            return result;
        } else {
            PolicePathSearcher pathSearcher = new PolicePathSearcher(getField(), PoliceStation.class);
            return pathSearcher.getHint();
        }
    }

    @Override
    public Detainable release() {
        Detainable released = prisoner;
        prisoner = null;
        return released;
    }

    @Override
    public String toString() {
        return Settings.POLICE_MAN;
    }
}
