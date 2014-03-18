package ru.spbau.montsev.drunkard3.boards;


import ru.spbau.montsev.drunkard3.GameObject;
import ru.spbau.montsev.drunkard3.fields.Field;
import ru.spbau.montsev.drunkard3.util.Graph;

import java.util.Iterator;

/**
 * @author Montsev Mikhail
 *         Date: 09.03.13
 *         Time: 21:04
 */
public abstract class Board implements Iterable<Field> {
    protected Graph graph;
    protected Field[][] fields;
    protected final int sizeX;
    protected final int sizeY;

    protected Board(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public Graph getGraph() {
        return graph;
    }

    public abstract void printState();

    public abstract void setField(Field field, int x, int y);

    public abstract void setField(GameObject gameObject, int x, int y);

    public abstract Field getField(int x, int y);

    public abstract void setLighted(int x, int y, int radius);

    @Override
    public Iterator<Field> iterator() {
        return new Iterator<Field>() {
            int x = 0;
            int y = 0;

            @Override
            public boolean hasNext() {
                return (y < sizeY);
            }

            @Override
            public Field next() {
                Field result = fields[x][y];
                x = (x + 1) % sizeX;
                if (x == 0)
                    y++;
                return result;
            }

            @Override
            public void remove() {
            }
        };
    }

}
