package ru.spbau.montsev.drunkard3.boards;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Montsev Mikhail
 *         Date: 27.05.13
 *         Time: 3:12
 */
public class BoardFactory {

    public static Board getBoard(String className, Class<?>[] classes, Object[] parameters) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Board board = null;
        try {
            Class<?> clazz = classLoader.loadClass(className);
            Constructor constructor = clazz.getConstructor(classes);
            board = (Board) constructor.newInstance(parameters);
        } catch (ClassNotFoundException e) {
            System.err.println("No such class. " + e.getMessage());
        } catch (NoSuchMethodException e) {
            System.err.println("No such constructor. " + e.getMessage());
        } catch (InvocationTargetException e) {
            System.err.println("Invocation target. " + e.getMessage());
        } catch (InstantiationException e) {
            System.err.println("Instantiation failed. " + e.getMessage());
        } catch (IllegalAccessException e) {
            System.err.println("Illegal access to constructor. " + e.getMessage());
        }
        return board;
    }
}
