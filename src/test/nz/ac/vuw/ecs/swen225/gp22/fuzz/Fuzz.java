package nz.ac.vuw.ecs.swen225.gp22.fuzz;

import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.app.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;

class Fuzz extends GameHandler{
    static final Random random = new Random();
    // keys to be pressed
    private static List<Integer> keys = List.of(
            KeyEvent.VK_UP,
            KeyEvent.VK_DOWN,
            KeyEvent.VK_LEFT,
            KeyEvent.VK_RIGHT );
    // actions to be performed
    private List<Runnable> actions = List.of(
//            input::mvUp,
//            input::mvDown,
//            input::mvLeft,
//            input::mvRight
    );
    /**
     * @param size the number of random inputs to generate
     * this method generates a random sequence of inputs
     *             and then executes them
     * @throws AWTException
     * @throws IllegalArgumentException
     */
    public static void randomKeys(int size) throws AWTException, IllegalArgumentException {

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
    /**
     * This method generates a mouse click at a location
     */
    public static void mouseTest(){
        GameHandler gHandler = new GameHandler();
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.mouseMove(100, 100);
        robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
    }
    /**
     * @param size the number of random inputs to generate
     * this method generates a random sequence of actions functions from the
     *             list of actions and then executes them
     */
    public void actiontest(int size){
        GameHandler game = new GameHandler();

        for (int i = 0; i < size; i++) {
            actions.get(random.nextInt(actions.size())).run();
            System.out.println("Action: " + i);
        }
    }
    public void main(String[] args) throws IllegalArgumentException, AWTException {
        randomKeys(100);
//        actiontest(100);
//        mouseTest();
    }

//    @Test
//    public void testLevel1() {
//
//    }
//    @Test
//    public void testLevel2() {
//
//    }
}