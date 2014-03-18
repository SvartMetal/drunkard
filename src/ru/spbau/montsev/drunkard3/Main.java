package ru.spbau.montsev.drunkard3;

/**
 * @author Montsev Mikhail
 *         Date: 09.03.13
 *         Time: 21:03
 */
public class Main {
    public static void printStates() {
        Game.getInstance().printState();
        for (int i = 0; i < 200; i++) {
            Game.getInstance().nextState();
        }
        System.out.println();
        System.out.println();
        System.out.println("Number of steps: 200");
        System.out.println();
        Game.getInstance().printState();
        for (int i = 0; i < 100; i++) {
            Game.getInstance().nextState();
        }
        System.out.println();
        System.out.println();
        System.out.println("Number of steps: 300");
        System.out.println();
        Game.getInstance().printState();
        for (int i = 0; i < 200; i++) {
            Game.getInstance().nextState();
        }
        System.out.println();
        System.out.println();
        System.out.println("Number of steps: 500");
        System.out.println();
        Game.getInstance().printState();
    }

    public static void stepByStepPrint(int count) {
        Game.getInstance().printState();
        for (int i = 0; i < count; i++) {
            System.out.println();
            System.out.println();
            Game.getInstance().nextState();
            Game.getInstance().printState();
        }
    }

    public static void main(String[] args) {
        stepByStepPrint(500);
//        printStates();
    }
}
