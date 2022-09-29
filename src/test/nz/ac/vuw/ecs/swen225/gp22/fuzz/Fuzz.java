package nz.ac.vuw.ecs.swen225.gp22.fuzz;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.app.*;
import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;

import java.io.*;

class Fuzz extends GameHandler{
    static final Random random = new Random();
    private static List<Integer> keys = List.of(
            KeyEvent.VK_UP,
            KeyEvent.VK_DOWN,
            KeyEvent.VK_LEFT,
            KeyEvent.VK_RIGHT );

    private List<Runnable> actions = List.of(
            input::mvUp,
            input::mvDown,
            input::mvLeft,
            input::mvRight );

    public static void randomKeys(int size) throws AWTException {
        GameHandler gHandler = new GameHandler();
        Robot robot = new Robot();

        // game.run().newApp();  // skip to first level

        robot.delay(100);

        for (int i = 0; i < size; i++) {
            robot.keyPress(keys.get(random.nextInt(keys.size())));
            System.out.println("Key: " + keys.get(random.nextInt(keys.size())));
            robot.keyRelease(keys.get(random.nextInt(keys.size())));
            robot.delay(100);
        }
    }

    public void actiontest(int size){
        GameHandler game = new GameHandler();

        for (int i = 0; i < size; i++) {
            actions.get(random.nextInt(actions.size())).run();
            System.out.println("Action: " + i);
        }
    }

    public void main(String[] args) throws InterruptedException, AWTException {
        randomKeys(100);
        actiontest(100);
    }


//    @Test
//    public void testLevel1() {
//
//    }
//
//    @Test
//    public void testLevel2() {
//
//    }

}