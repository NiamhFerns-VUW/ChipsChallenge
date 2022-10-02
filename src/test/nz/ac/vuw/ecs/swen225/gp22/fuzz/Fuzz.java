package nz.ac.vuw.ecs.swen225.gp22.fuzz;

import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.app.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.*;

class Fuzz extends GameHandler{

    private GameHandler game;
    static final Random random = new Random();
    // keys to be pressed
    private static List<Integer> keys = List.of(
            KeyEvent.VK_UP,
            KeyEvent.VK_DOWN,
            KeyEvent.VK_LEFT,
            KeyEvent.VK_RIGHT );
    // actions to be performed
    private List<Runnable> actions = List.of();

    Fuzz() {
        super();
        game = GameHandler.get();
    }

    /**
     * @param size the number of random inputs to generate
     * this method generates a random sequence of inputs
     *             and then executes them
     * @throws AWTException
     * @throws IllegalArgumentException
     */
    public static void randomKeys(int size) throws AWTException, IllegalArgumentException {

        GameHandler game = null;
        Robot robot = new Robot();

        //game.skipTo("Level1");
        robot.mouseMove(1050, 550);
        robot.delay(200);
        robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);

        robot.delay(100);

        for (int i = 0; i < size; i++) {
            robot.keyPress(keys.get(random.nextInt(keys.size())));
            System.out.println("Key: " + keys.get(random.nextInt(keys.size())));
            robot.keyRelease(keys.get(random.nextInt(keys.size())));
            robot.delay(100);
        }
    }
    /**
     * This method generates a mouse click at a location
     */
    public static void mouseTest() throws AWTException {
        GameHandler game = null;
        Robot robot = new Robot();
//        try {
//            robot = new Robot();
//        } catch (AWTException e) {
//            e.printStackTrace();
//        }
        robot.mouseMove(1050, 550);
        robot.delay(200);
        robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
    }
    /**
     * @param size the number of random inputs to generate
     * this method generates a random sequence of actions functions from the
     *             list of actions and then executes them
     */
    public void actiontest(int size){

//        game.reset();
//        game.start();

//        GameHandler game = new GameHandler();
//        System.out.println("game created");

        InputGenerator input = new InputGenerator(game);
        System.out.println("inputG created");

        actions = List.of(
                input::up,
                input::down,
                input::left,
                input::right
        );
        System.out.println("actions created");

        game.skipTo("Level1");

        for (int i = 0; i < size; i++) {
            actions.get(random.nextInt(actions.size())).run();
            System.out.println("Action: " + actions.get(random.nextInt(actions.size())));
        }
    }
    public static void main(String[] args) throws AWTException, IllegalArgumentException {
        SwingUtilities.invokeLater(GameHandler::new);
        Fuzz f = new Fuzz();
        f.actiontest(100);
        //f.mouseTest();
        //f.randomKeys(100);
    }
}