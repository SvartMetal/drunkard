package ru.spbau.montsev.drunkard3.actors;

import ru.spbau.montsev.drunkard3.Game;
import ru.spbau.montsev.drunkard3.Settings;
import ru.spbau.montsev.drunkard3.fields.BorderField;
import ru.spbau.montsev.drunkard3.fields.Field;
import ru.spbau.montsev.drunkard3.interfaces.Comprised;
import ru.spbau.montsev.drunkard3.interfaces.Detainable;
import ru.spbau.montsev.drunkard3.staff.Bottle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Montsev Mikhail
 *         Date: 10.03.13
 *         Time: 1:21
 */
public class Drunkard extends Actor implements Detainable {
    private int sleepTimer = 0;
    private int lyingTimer = 0;

    public Drunkard(Bottle b, Field f) {
        super(f);
        addItem(b);
    }

    public boolean isSleeping() {
        return sleepTimer > 0;
    }

    public boolean isLaying() {
        return lyingTimer > 0;
    }

    public void setSleep(int sleepTimer) {
        this.sleepTimer = sleepTimer;
    }

    public void setLying(int lyingTimer) {
        this.lyingTimer = lyingTimer;
    }

    public Bottle dropBottle() {
        for (Comprised item : getItems()) {
            if (item instanceof Bottle) {
                Random random = new Random();
                if (random.nextInt((int) Math.round(1 / Settings.BOTTLE_DROP_PROBABILITY)) == 0) {
                    removeItem(item);
                    return (Bottle) item;
                }
            }
        }
        return null;
    }

    @Override
    public Field move() {
        if (getTime() > Game.getInstance().getTime()) return null;
        super.move();
        if (isSleeping()) {
//            sleepTimer--;
            return null;
        }
        if (isLaying()) {
//            lyingTimer--;
            return null;
        }
        List<Field> neighbors = getField().getNeighbors();
        List<Field> list = new ArrayList<>();
        for (Field field : neighbors) {
            if (field instanceof BorderField || field.getGameObject() instanceof Drunkard &&
                    !((Drunkard) field.getGameObject()).isLaying())
                continue;
            list.add(field);
        }
        Random random = new Random();
        if (list.size() == 0)
            return null;
        return list.get(random.nextInt(list.size()));
    }

    @Override
    public String toString() {
        if (isSleeping()) return Settings.SLEEPING_DRUNKARD;
        if (isLaying()) return Settings.LAYING_DRUNKARD;
        return Settings.DRUNKARD;
    }
}
