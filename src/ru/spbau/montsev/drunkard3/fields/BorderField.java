package ru.spbau.montsev.drunkard3.fields;


import ru.spbau.montsev.drunkard3.Settings;

/**
 * @author Montsev Mikhail
 *         Date: 26.05.13
 *         Time: 17:53
 */
public class BorderField extends Field {

    public BorderField(Field f) {
        super(f.getBoard(), f.getGameObject(), f.getPosition());
    }

    @Override
    public String toString() {
        if (isEmpty()) return Settings.BORDER_FIELD;
        return getGameObject().toString();
    }
}
