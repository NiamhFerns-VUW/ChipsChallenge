package nz.ac.vuw.ecs.swen225.gp22.fuzz;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.app.*;

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

    private static List<InputHandler> actions = List.of(
            input::moveUp,
            input::moveDown,
            input::moveLeft,
            input::moveRight
            );

    public static void randomKeys(int size) throws AWTException {
        GameHandler game = new GameHandler();
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

    public static void actiontest(int size){
        GameHandler game = new GameHandler();

        for (int i = 0; i < size; i++) {
            input.get(random.nextInt(input.size())).run();
            System.out.println("Action: " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException, AWTException {
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