package ru.spbau.montsev.drunkard3.util;

import ru.spbau.montsev.drunkard3.fields.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Montsev Mikhail
 *         Date: 04.06.13
 *         Time: 6:21
 */
public class Node {
    private List<Field> neighbors;
    private Field field;

    public Node(List<Field> neighbors, Field field) {
        this.neighbors = neighbors;
        this.field = field;
    }

    public List<Field> getNeighbors() {
        return new ArrayList<>(neighbors);
    }

    public Field getField() {
        return field;
    }
}
