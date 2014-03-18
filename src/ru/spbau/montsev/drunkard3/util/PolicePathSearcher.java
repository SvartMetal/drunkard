package ru.spbau.montsev.drunkard3.util;

import ru.spbau.montsev.drunkard3.actors.Drunkard;
import ru.spbau.montsev.drunkard3.fields.Field;
import ru.spbau.montsev.drunkard3.staff.PoliceStation;

/**
 * @author Montsev Mikhail
 *         Date: 27.05.13
 *         Time: 17:27
 */
public class PolicePathSearcher extends PathSearcher {

    public PolicePathSearcher(Field field, Class<?> searchingClass) {
        super(field, searchingClass);
    }

    @Override
    public boolean checkSearchObject(Field t) {
        if (getSearchingClass() == Drunkard.class) {
            return !t.isEmpty() && t.isLighted() && t.getGameObject() instanceof Drunkard &&
                    (((Drunkard) t.getGameObject()).isLaying() || ((Drunkard) t.getGameObject()).isSleeping());
        }
        if (getSearchingClass() == PoliceStation.class) {
            return !t.isEmpty() && t.getGameObject() instanceof PoliceStation;
        }
        return false;
    }
}
