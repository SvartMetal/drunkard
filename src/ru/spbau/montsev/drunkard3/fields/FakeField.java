package ru.spbau.montsev.drunkard3.fields;

import ru.spbau.montsev.drunkard3.Settings;

/**
 * @author Montsev Mikhail
 *         Date: 04.06.13
 *         Time: 9:09
 */
public class FakeField extends Field {

    public FakeField(Field f) {
        super(f.getBoard(), f.getGameObject(), f.getPosition());
    }

    @Override
    public String toString() {
        return Settings.FAKE_FIELD;
    }
}
