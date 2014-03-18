package ru.spbau.montsev.drunkard3.fields;

import ru.spbau.montsev.drunkard3.GameObject;
import ru.spbau.montsev.drunkard3.Settings;
import ru.spbau.montsev.drunkard3.boards.Board;
import ru.spbau.montsev.drunkard3.interfaces.Moveable;

import java.util.List;

/**
 * @author Montsev Mikhail
 *         Date: 09.03.13
 *         Time: 21:06
 */
public class Field implements Moveable {
    private GameObject gameObject = null;
    private final Board board;
    private final int position;
    private boolean lighted = false;

    public Field(Board b, int position) {
        this.position = position;
        board = b;
    }

    public Field(Board b, GameObject go, int position) {
        this(b, position);
        gameObject = go;
        if (go != null)
            go.setField(this);
    }

    public boolean isEmpty() {
        return gameObject == null;
    }

    public GameObject remove() {
        GameObject go = gameObject;
        gameObject = null;
        return go;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public boolean isLighted() {
        return lighted;
    }

    public void setLighted() {
        lighted = true;
    }

    @Override
    public Field move() {
        if (gameObject != null)
            return gameObject.move();
        return null;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public String toString() {
        if (isEmpty()) return Settings.EMPTY_FIELD;
        return gameObject.toString();
    }

    public List<Field> getNeighbors() {
        return getBoard().getGraph().getNode(position).getNeighbors();
    }

    public int getPosition() {
        return position;
    }
}
