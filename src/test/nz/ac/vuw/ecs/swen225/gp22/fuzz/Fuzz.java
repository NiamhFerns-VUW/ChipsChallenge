package nz.ac.vuw.ecs.swen225.gp22.fuzz;

import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.app.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class Fuzz{
    private GameHandler game;
    static final Random random = new Random();
    // keys to be pressed
    private static List<Integer> keys = List.of(
            KeyEvent.VK_UP,
            KeyEvent.VK_DOWN,
            KeyEvent.VK_LEFT,
            KeyEvent.VK_RIGHT,
            KeyEvent.VK_SPACE,
            KeyEvent.VK_ESCAPE
    );

    public Fuzz() {
        game = GameHandler.get();
        game.reset();
        game.start();
    }
    /**
     * This method is used Strategy Pattern to generate random inputs
     * @throws AWTException
     */
    public void testInputStrategy(InputStrategy inputStrategy, int size, String level) throws AWTException {
        game.skipTo(level);
        Robot robot = new Robot();
        robot.delay(2000);
        for (int i = 0; i < size; i++) {
            int key = inputStrategy.nextInput();
            if (inputStrategy.isPressCtrl()) {
                robot.keyPress(KeyEvent.VK_CONTROL);
                System.out.println("Pressing control");
            }
            testClock();
            robot.keyPress(key);
            System.out.println("Pressing " + KeyEvent.getKeyText(key));
            robot.keyRelease(key);
            if (inputStrategy.isPressCtrl()) {
                robot.keyRelease(KeyEvent.VK_CONTROL);
            }
        }
    }

    /**
     * @param size the number of random inputs to generate
     * this method generates a random sequence of inputs
     *             and then executes them
     * @throws AWTException
     * @throws IllegalArgumentException
     */
    public void randomKeys(int size, String level) throws AWTException, IllegalArgumentException {
        Robot robot = new Robot();

        game.skipTo(level);
        robot.delay(2000 ) ;

         for (int i = 0; i < size; i++) {
            int key = keys.get(random.nextInt(keys.size()))  ;
            robot.keyPress(key);
            System.out.println("Key: " + KeyEvent.getKeyText(key));
            robot.keyRelease(key);
            robot.delay(100);
        }
    }
    /**
         * This method generates a mouse click at a location
      */
    public void mouseTest() throws AWTException {
        GameHandler game = null;
        Robot robot = new Robot();

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
    public void actiontest(int size, String level) throws AWTException {
        game.skipTo(level);
        InputGenerator input = new InputGenerator(game);
        Robot robot = new Robot();

        List<Runnable> actions_without_up = List.of(input::down, input::down, input::left, input::right);
        List<Runnable> actions_without_down = List.of(input::up, input::up, input::left, input::right);
        List<Runnable> actions_without_left = List.of(input::up, input::down, input::right, input::right);
        List<Runnable> actions_without_right = List.of(input::up, input::down, input::left, input::left);

        robot.delay(2000);
        int index = 3;
        for (int i = 0; i < size; i++) {

            // prevent the player from making meaningless moves( like moving up and down at the same time)
            List<Runnable> from = switch(index){
                case 0 -> actions_without_down;
                case 1 -> actions_without_up;
                case 2 -> actions_without_right;
                default -> actions_without_left;
            };

            testClock();

            from.get(index).run();
            System.out.println("Action: " + from.get(index));
            robot.delay(10);
            index = random.nextInt(from.size());
        }
    }
    /**
     * This method tests the clock
     * @throws IllegalStateException
     */
    public void testClock() throws IllegalStateException {
        if(GameClock.get().currentLevelTime() < 0) {
            throw new IllegalStateException("Time is negative");
        }
    }
    public static void main(String[] args) throws AWTException, IllegalArgumentException {
        Fuzz f = new Fuzz();

        //f.randomKeys(100, "level1");
        f.actiontest(100, "level1");
    }
    @Test
    public void test1() throws AWTException {
        Fuzz f = new Fuzz();
        //use comment and uncomment to switch between random keys and actions

        //f.testInputStrategy(inputStrategy, 100000, "level1");
//        InputStrategy inputStrategy = new ProbInput();
//        f.testInputStrategy(inputStrategy, 100000, "level1");

//        f.randomKeys(10000, "level1");
        f.actiontest(100000, "level1");
    }
     @Test
    public void test2() throws AWTException {
        Fuzz f = new Fuzz();

        // use comment and uncomment to switch between random keys and actions
        f.randomKeys(10000, "level2");
//        f.actiontest(100000, "level2");
    }
}