package ru.spbau.montsev.drunkard3.boards;

import ru.spbau.montsev.drunkard3.GameObject;
import ru.spbau.montsev.drunkard3.fields.BorderField;
import ru.spbau.montsev.drunkard3.fields.Field;
import ru.spbau.montsev.drunkard3.util.Graph;

/**
 * @author Montsev Mikhail
 *         Date: 26.05.13
 *         Time: 22:39
 */
public class SquareBoard extends Board {

    public SquareBoard(int sizeX, int sizeY) {
        super(sizeX, sizeY);
        fields = new Field[sizeX][sizeY];
        initField(fields);
        graph = new Graph(fields);
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
    public void setField(Field field, int x, int y) {
        fields[x][y] = field;
    }

    @Override
    public void setField(GameObject gameObject, int x, int y) {
        fields[x][y].setGameObject(gameObject);
    }

    @Override
    public Field getField(int x, int y) {
        return fields[x][y];
    }

    @Override
    public void setLighted(int x, int y, int radius) {
        for (int i = x - radius; i <= x + radius; i++) {
            for (int j = y - radius; j <= y + radius; j++) {
                fields[i][j].setLighted();
            }
        }
    }

    @Override
    public void printState() {
        for (Field field : this) {
            if (field.getPosition() != 0 && field.getPosition() % (sizeX) == 0) {
                System.out.println("");
            }
            System.out.print(field);
        }
    }
}
