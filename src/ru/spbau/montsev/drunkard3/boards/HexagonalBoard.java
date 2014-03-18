package ru.spbau.montsev.drunkard3.boards;

import ru.spbau.montsev.drunkard3.GameObject;
import ru.spbau.montsev.drunkard3.fields.BorderField;
import ru.spbau.montsev.drunkard3.fields.FakeField;
import ru.spbau.montsev.drunkard3.fields.Field;
import ru.spbau.montsev.drunkard3.util.Graph;

/**
 * @author Montsev Mikhail
 *         Date: 27.05.13
 *         Time: 0:14
 */
public class HexagonalBoard extends Board {

    public HexagonalBoard(int sizeX, int sizeY) {
        super(sizeX, 2 * sizeY + 1);
        fields = new Field[sizeX][2 * sizeY + 1];
        initField(fields);
        initFakeField(fields);
        graph = new Graph(fields);
    }

    private void initFakeField(Field[][] fields) {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
                    fields[i][j] = new FakeField(fields[i][j]);
                }
            }
        }
    }

    private void initField(Field[][] fields) {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                fields[i][j] = new Field(this, i + j * sizeX);
            }
        }
        for (int i = 0; i < sizeX; i++) {
            fields[i][0] = new BorderField(fields[i][0]);
        }
        for (int i = 0; i < sizeX; i++) {
            fields[i][sizeY - 1] = new BorderField(fields[i][sizeY - 1]);
        }
        for (int j = 0; j < sizeY; j++) {
            fields[0][j] = new BorderField(fields[0][j]);
        }
        for (int j = 0; j < sizeY; j++) {
            fields[sizeX - 1][j] = new BorderField(fields[sizeX - 1][j]);
        }
    }

    @Override
    public void printState() {
        for (Field field : this) {
            if (field.getPosition() != 0 && field.getPosition() % sizeX == 0) {
                System.out.println("");
            }
            if (field.getPosition() % sizeX == 0 && field instanceof FakeField)
                System.out.print(" ");
            if (field instanceof FakeField)
                System.out.print(field);
            else System.out.print("/" + field + "\\");
        }
    }

    private int convertY(int x, int y) {
        if (x % 2 == 0) return 2 * y + 1;
        else return 2 * y;
    }

    @Override
    public void setField(Field field, int x, int y) {
        int newY = convertY(x, y);
        fields[x][newY] = field;
    }

    @Override
    public void setField(GameObject gameObject, int x, int y) {
        int newY = convertY(x, y);
        fields[x][newY].setGameObject(gameObject);
    }

    @Override
    public Field getField(int x, int y) {
        int newY = convertY(x, y);
        return fields[x][newY];
    }

    @Override
    public void setLighted(int x, int y, int radius) {
        int newY = convertY(x, y);
        for (int i = x - radius; i <= x + radius; i++) {
            for (int j = newY - 2 * radius; j <= newY + 2 * radius; j++) {
                fields[i][j].setLighted();
            }
        }
    }

}
