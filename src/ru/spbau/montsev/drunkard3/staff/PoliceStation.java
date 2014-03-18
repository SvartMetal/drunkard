package ru.spbau.montsev.drunkard3.staff;

import ru.spbau.montsev.drunkard3.Settings;
import ru.spbau.montsev.drunkard3.actors.PoliceMan;
import ru.spbau.montsev.drunkard3.fields.Field;
import ru.spbau.montsev.drunkard3.interfaces.Detainable;
import ru.spbau.montsev.drunkard3.interfaces.Detaining;

import java.util.ArrayList;

/**
 * @author Montsev Mikhail
 *         Date: 10.03.13
 *         Time: 1:33
 */
public class PoliceStation extends Static implements Detaining {
    ArrayList<Detainable> prisoners = new ArrayList<>();
    PoliceMan policeMan = null;

    public PoliceStation(Field f) {
        super(f);
    }

    @Override
    public void arrest(Detainable d) {
        prisoners.add(d);
    }

    @Override
    public Detainable release() {
        return null;
    }

    @Override
    public String toString() {
        return Settings.POLICE_STATION;
    }

    public void setPoliceMan(PoliceMan policeMan) {
        this.policeMan = policeMan;
    }

    @Override
    public Field move() {
        super.move();
        if (policeMan != null)
            return policeMan.move();
        return null;
    }

    public PoliceMan getPoliceMan() {
        return policeMan;
    }

}
