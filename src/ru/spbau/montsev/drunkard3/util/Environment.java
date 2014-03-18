package ru.spbau.montsev.drunkard3.util;

import ru.spbau.montsev.drunkard3.boards.HexagonalBoard;
import ru.spbau.montsev.drunkard3.boards.SquareBoard;
import ru.spbau.montsev.drunkard3.fields.FakeField;
import ru.spbau.montsev.drunkard3.fields.Field;

import java.util.Iterator;

/**
 * @author Montsev Mikhail
 *         Date: 26.05.13
 *         Time: 22:30
 */
public class Environment implements Iterable<Field> {
    private final Field field;
    private final Field[][] fields;
    private final int x;
    private final int y;

    public Environment(Field field, Field[][] fields, int x, int y) {
        this.field = field;
        this.fields = fields;
        this.x = x;
        this.y = y;
    }

    public class IteratorBoard implements Iterator<Field> {
        int x;
        int y;
        final int sizeX;
        final int sizeY;
        final int[] itersX;
        final int[] itersY;
        int count = 0;
        final int size;

        protected IteratorBoard(int x, int y, int[] itersX, int[] itersY, int size) {
            this.x = x;
            this.y = y;
            this.sizeX = fields.length;
            this.sizeY = fields[0].length;
            this.itersX = itersX;
            this.itersY = itersY;
            this.size = size;
        }

        @Override
        public boolean hasNext() {
            if (field instanceof FakeField)
                return false;
            while (count < size &&
                    (itersX[count] < 0 || itersX[count] > sizeX - 1 || itersY[count] < 0
                            || itersY[count] > sizeY - 1 ||
                            fields[x][y] instanceof FakeField)) {
                count++;
            }
            return count < size;
        }

        @Override
        public Field next() {
            x = itersX[count];
            y = itersY[count];
            count++;
            return fields[x][y];
        }

        @Override
        public void remove() {
        }
    }

    @Override
    public Iterator<Field> iterator() {
        int x = this.x;
        int y = this.y;
        if (field.getBoard() instanceof SquareBoard) {
            int[] itersX = {x - 1, x, x + 1, x};
            int[] itersY = {y, y + 1, y, y - 1};
            return new IteratorBoard(x, y, itersX, itersY, 4);

        }

        if (field.getBoard() instanceof HexagonalBoard) {
            int[] itersX = {x - 1, x, x + 1, x + 1, x, x - 1};
            int[] itersY = {y + 1, y + 2, y + 1, y - 1, y - 2, y - 1};
            return new IteratorBoard(x, y, itersX, itersY, 6);
        }

        return null;
    }
}
