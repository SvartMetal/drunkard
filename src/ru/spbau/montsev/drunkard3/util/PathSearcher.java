package ru.spbau.montsev.drunkard3.util;

import ru.spbau.montsev.drunkard3.fields.BorderField;
import ru.spbau.montsev.drunkard3.fields.Field;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Montsev Mikhail
 *         Date: 04.06.13
 *         Time: 5:01
 */
public abstract class PathSearcher {
    private final Field field;
    private boolean[] used;
    private Field[] predecessor;

    private final Class<?> searchingClass;

    public PathSearcher(Field field, Class<?> searchingClass) {
        this.field = field;
        this.searchingClass = searchingClass;
        Graph graph = field.getBoard().getGraph();
        used = new boolean[graph.size()];
        predecessor = new Field[graph.size()];
        for (int i = 0; i < used.length; i++) {
            used[i] = false;
        }
    }

    private Field checkAndAdd(List<Field> queue, List<Field> env, Field f) {
        for (Field t : env) {
            if (!used[t.getPosition()] && t.isEmpty() && !(t instanceof BorderField)) {
                used[t.getPosition()] = true;
                queue.add(t);
                predecessor[t.getPosition()] = f;
            } else if (checkSearchObject(t)) {
                predecessor[t.getPosition()] = f;
                return t;
            }
        }
        return null;
    }

    private Field bfsSearch(Field field) {
        used[field.getPosition()] = true;
        List<Field> queue = new LinkedList<>();
        queue.add(field);
        Field result = null;
        while (!queue.isEmpty()) {
            if (result != null) break;
            Field f = queue.remove(0);
            List<Field> env = f.getNeighbors();
            result = checkAndAdd(queue, env, f);
        }
        if (result == null) return null;
        Field f = result;
        while (predecessor[f.getPosition()] != field) {
            f = predecessor[f.getPosition()];
        }
        return f;
    }

    public Field getHint() {
        return bfsSearch(field);
    }

    public Class<?> getSearchingClass() {
        return searchingClass;
    }

    public abstract boolean checkSearchObject(Field field);

}
