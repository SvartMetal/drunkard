package ru.spbau.montsev.drunkard3.util;

import ru.spbau.montsev.drunkard3.fields.Field;
import ru.spbau.montsev.drunkard3.staff.Bottle;
import ru.spbau.montsev.drunkard3.staff.GlassPoint;

/**
 * @author Montsev Mikhail
 *         Date: 05.06.13
 *         Time: 12:55
 */
public class HoboPathSearcher extends PathSearcher {

    public HoboPathSearcher(Field field, Class<?> searchingClass) {
        super(field, searchingClass);
    }

    @Override
    public boolean checkSearchObject(Field t) {
        if (getSearchingClass() == Bottle.class) {
            return !t.isEmpty() && t.getGameObject() instanceof Bottle;
        }
        if (getSearchingClass() == GlassPoint.class) {
            return !t.isEmpty() && t.getGameObject() instanceof GlassPoint;
        }
        return false;
    }
}
