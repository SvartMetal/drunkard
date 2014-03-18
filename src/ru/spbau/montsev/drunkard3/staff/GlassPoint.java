package ru.spbau.montsev.drunkard3.staff;

import ru.spbau.montsev.drunkard3.Settings;
import ru.spbau.montsev.drunkard3.actors.Hobo;
import ru.spbau.montsev.drunkard3.fields.Field;

/**
 * @author Montsev Mikhail
 *         Date: 05.06.13
 *         Time: 12:48
 */
public class GlassPoint extends Static {
    private Hobo hobo = null;

    public GlassPoint(Field f) {
        super(f);
    }

    public Hobo getHobo() {
        return hobo;
    }

    public void setHobo(Hobo hobo) {
        this.hobo = hobo;
    }

    @Override
    public Field move() {
        super.move();
        if (hobo != null) {
            return hobo.move();
        }
        return null;
    }

    @Override
    public String toString() {
        return Settings.GLASS_POINT;
    }
}
