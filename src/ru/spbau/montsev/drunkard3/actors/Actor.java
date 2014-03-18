package ru.spbau.montsev.drunkard3.actors;

import ru.spbau.montsev.drunkard3.fields.Field;
import ru.spbau.montsev.drunkard3.interfaces.Comprised;
import ru.spbau.montsev.drunkard3.interfaces.Containing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Montsev Mikhail
 *         Date: 09.03.13
 *         Time: 21:09
 */
public abstract class Actor extends Moving implements Containing {
    private ArrayList<Comprised> items = new ArrayList<>();

    public Actor(Field f) {
        super(f);
    }

    public void addItem(Comprised c) {
        items.add(c);
        if (c.getOwner() != null)
            c.getOwner().removeItem(c);
        c.setOwner(this);
    }

    public List<Comprised> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void removeItem(Comprised c) {
        items.remove(c);
        c.setOwner(null);
    }

}
