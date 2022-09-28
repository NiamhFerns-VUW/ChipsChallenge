package nz.ac.vuw.ecs.swen225.gp22.fuzz;
package nz.ac.vuw.ecs.swen225.gp22.app;

import java.util.ArrayList;
import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.app.*;

import java.awt.AWTException;
import games.GameHandler;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;

class Fuzz{
    static final Random random = new Random();
    private List<KeyEvent> keys = List.of(  KeyEvent.VK_UP,
            KeyEvent.VK_DOWN,
            KeyEvent.VK_LEFT,
            KeyEvent.VK_RIGHT );
    public static void randomKeys(int size){
        GameHandler game = new GameHandler();
        Robot robot = new Robot();

        // game.run().newApp();  // skip to first level

        robot.delay(100);

        for (int i = 0; i < size; i++) {
            robot.keyPress(keys.get(random.nextInt(keys.size())));
            robot.keyRelease(keys.get(random.nextInt(keys.size())));
            robot.delay(100);
        }
    }

    public static void main(String[] args) throws InterruptedException, AWTException {
        randomKeys(100);
    }
}