package ru.spbau.montsev.drunkard3.interfaces;

/**
 * @author Montsev Mikhail
 *         Date: 10.03.13
 *         Time: 6:20
 */
public interface Detaining {
    public void arrest(Detainable d);

    public Detainable release();
}
