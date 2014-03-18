package ru.spbau.montsev.drunkard3.util;

import ru.spbau.montsev.drunkard3.fields.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Montsev Mikhail
 *         Date: 04.06.13
 *         Time: 6:10
 */
public class Graph {
    private List<Node> fieldList = new ArrayList<>();

    public Graph(Field[][] fields) {
        if (fields.length == 0) return;
        int sizeX = fields.length;
        int sizeY = fields[0].length;
        for (int j = 0; j < sizeY; j++) {
            for (int i = 0; i < sizeX; i++) {
                Environment env = new Environment(fields[i][j], fields, i, j);
                List<Field> list = new ArrayList<>();
                for (Field f : env) {
                    list.add(f);
                }
                fieldList.add(new Node(list, fields[i][j]));
            }
        }
    }

    public Node getNode(int position) {
        return fieldList.get(position);
    }

    public Field get(int position) {
        return getNode(position).getField();
    }

    public int size() {
        return fieldList.size();
    }
}
