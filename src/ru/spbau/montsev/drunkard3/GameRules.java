package ru.spbau.montsev.drunkard3;

import ru.spbau.montsev.drunkard3.actors.Drunkard;
import ru.spbau.montsev.drunkard3.actors.Hobo;
import ru.spbau.montsev.drunkard3.actors.PoliceMan;
import ru.spbau.montsev.drunkard3.fields.Field;
import ru.spbau.montsev.drunkard3.interfaces.Comprised;
import ru.spbau.montsev.drunkard3.interfaces.Detainable;
import ru.spbau.montsev.drunkard3.staff.*;

import java.util.List;

/**
 * @author Montsev Mikhail
 *         Date: 09.03.13
 *         Time: 23:37
 */
public class GameRules {

    public static void performAction(Field actuator, Field passivator) {
        if (actuator == null || passivator == null)
            return;
        if (!actuator.isEmpty() && !passivator.isEmpty())
            performAction(actuator.getGameObject(), passivator.getGameObject());
        else if (!actuator.isEmpty() && actuator.getGameObject() instanceof Static)
            specificAction((Static) (actuator.getGameObject()), passivator);
        else if (!actuator.isEmpty())
            performMove(actuator.getGameObject(), passivator);
    }

    private static void performMove(GameObject actuator, Field field) {
        Field oldField = actuator.getField();
        oldField.setGameObject(null);
        specificAction(actuator, oldField);
        field.setGameObject(actuator);
        actuator.setField(field);
    }

    private static void specificAction(Static stat, Field field) {
        if (stat instanceof Pub) {
            specificAction((Pub) stat, field);
        } else if (stat instanceof PoliceStation) {
            specificAction((PoliceStation) stat, field);
        } else if (stat instanceof GlassPoint) {
            specificAction((GlassPoint) stat, field);
        }
    }

    private static void specificAction(Pub pub, Field field) {
        if (!field.isEmpty()) {
            return;
        }
        Drunkard drunkard = (Drunkard) pub.generate();
        drunkard.setField(field);
        field.setGameObject(drunkard);
    }

    private static void specificAction(GlassPoint glassPoint, Field field) {
        if (!field.isEmpty() && glassPoint.getHobo() == null) {
            return;
        }
        field.setGameObject(glassPoint.getHobo());
        glassPoint.getHobo().setField(field);
        glassPoint.setHobo(null);
    }

    private static void specificAction(PoliceStation policeStation, Field field) {
        if (!field.isEmpty() && policeStation.getPoliceMan() == null) {
            return;
        }
        field.setGameObject(policeStation.getPoliceMan());
        policeStation.getPoliceMan().setField(field);
        policeStation.setPoliceMan(null);
    }

    private static void specificAction(GameObject gameObject, Field oldField) {
        if (gameObject instanceof Drunkard)
            specificAction((Drunkard) gameObject, oldField);
    }

    private static void specificAction(Drunkard drunkard, Field oldField) {
        Bottle bottle = drunkard.dropBottle();
        if (bottle != null && oldField.isEmpty()) {
            oldField.setGameObject(bottle);
            bottle.setField(oldField);
        }
    }

    private static void performAction(GameObject actuator, GameObject passivator) {
        if (actuator instanceof Drunkard && passivator instanceof Column) {
            performAction((Drunkard) actuator, (Column) passivator);
        } else if (actuator instanceof Drunkard && passivator instanceof Drunkard) {
            performAction((Drunkard) actuator, (Drunkard) passivator);
        } else if (actuator instanceof Drunkard && passivator instanceof Bottle) {
            performAction((Drunkard) actuator, (Bottle) passivator);
        } else if (actuator instanceof PoliceMan && passivator instanceof Drunkard) {
            performAction((PoliceMan) actuator, (Drunkard) passivator);
        } else if (actuator instanceof PoliceMan && passivator instanceof PoliceStation) {
            performAction((PoliceMan) actuator, (PoliceStation) passivator);
        } else if (actuator instanceof Hobo && passivator instanceof Bottle) {
            performAction((Hobo) actuator, (Bottle) passivator);
        } else if (actuator instanceof Hobo && passivator instanceof GlassPoint) {
            performAction((Hobo) actuator, (GlassPoint) passivator);
        }
    }

    private static void performAction(Drunkard drunkard, Bottle bottle) {
        drunkard.setLying(Settings.DRUNKARD_LYING_TIME);
    }

    private static void performAction(Drunkard drunkard1, Drunkard drunkard2) {
        if (drunkard2.isSleeping())
            drunkard1.setSleep(Settings.DRUNKARD_SLEEPING_TIME);
    }

    private static void performAction(Drunkard drunkard, Column column) {
        drunkard.setSleep(Settings.DRUNKARD_SLEEPING_TIME);
    }

    private static void performAction(PoliceMan policeMan, Drunkard drunkard) {
        if (drunkard.isLaying() || drunkard.isSleeping()) {
            drunkard.getField().setGameObject(null);
            drunkard.setField(null);
            policeMan.arrest(drunkard);
        }
    }

    private static void performAction(PoliceMan policeMan, PoliceStation policeStation) {
        Detainable detainable = policeMan.release();
        if (detainable != null) {
            policeStation.arrest(detainable);
            policeMan.getField().setGameObject(null);
            policeMan.setField(policeStation.getField());
            policeStation.setPoliceMan(policeMan);
        }
    }

    private static void performAction(Hobo hobo, Bottle bottle) {
        bottle.getField().setGameObject(null);
        bottle.setField(null);
        hobo.addItem(bottle);
    }

    private static void performAction(Hobo hobo, GlassPoint glassPoint) {
        List<Comprised> items = hobo.getItems();
        if (items.isEmpty()) return;
        Comprised item = items.get(0);
        hobo.removeItem(item);
        hobo.getField().setGameObject(null);
        hobo.setField(glassPoint.getField());
        glassPoint.setHobo(hobo);
        hobo.setReturnTimer(Settings.HOBO_RETURN_LATENCY);
    }

}
