package ru.spbau.montsev.drunkard3.actors;

import ru.spbau.montsev.drunkard3.Game;
import ru.spbau.montsev.drunkard3.Settings;
import ru.spbau.montsev.drunkard3.fields.Field;
import ru.spbau.montsev.drunkard3.interfaces.Comprised;
import ru.spbau.montsev.drunkard3.staff.Bottle;
import ru.spbau.montsev.drunkard3.staff.GlassPoint;
import ru.spbau.montsev.drunkard3.util.HoboPathSearcher;
import ru.spbau.montsev.drunkard3.util.PolicePathSearcher;

import java.util.List;

/**
 * @author Montsev Mikhail
 *         Date: 05.06.13
 *         Time: 12:46
 */
public class Hobo extends Actor {
    private int returnTimer = 0;

    public Hobo(Field f) {
        super(f);
    }

    @Override
    public Field move() {
        if (getTime() > Game.getInstance().getTime()) return null;

        super.move();
        if (isDrinking()) {
            returnTimer--;
            return null;
        }

        List<Comprised> items = getItems();
        if (items.isEmpty()) {
            HoboPathSearcher pathSearcher = new HoboPathSearcher(getField(), Bottle.class);
            Field result = pathSearcher.getHint();
            if (result == null && getField().getGameObject() instanceof Hobo) {
                return new PolicePathSearcher(getField(), GlassPoint.class).getHint();
            }
            return result;
        } else {
            HoboPathSearcher pathSearcher = new HoboPathSearcher(getField(), GlassPoint.class);
            return pathSearcher.getHint();
        }
    }

    public boolean isDrinking() {
        return returnTimer > 0;
    }

    @Override
    public String toString() {
        return Settings.HOBO;
    }

    public void setReturnTimer(int returnTimer) {
        this.returnTimer = returnTimer;
    }
}
