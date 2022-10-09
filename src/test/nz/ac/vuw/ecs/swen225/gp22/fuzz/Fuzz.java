package nz.ac.vuw.ecs.swen225.gp22.fuzz;

import java.lang.reflect.Field;
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
            KeyEvent.VK_RIGHT );
    // actions to be performed
    //private List<Runnable> actions = List.of();

    public Fuzz() {
        game = GameHandler.get();
        game.reset();
        game.start();
    }

    /**
     * @param size the number of random inputs to generate
     * this method generates a random sequence of inputs
     *             and then executes them
     * @throws AWTException
     * @throws IllegalArgumentException
     */
    public void randomKeys(int size) throws AWTException, IllegalArgumentException {

        game.reset();
        game.start();

        Robot robot = new Robot();

        game.skipTo("level1");

        robot.delay(100);

        for (int i = 0; i < size; i++) {
            int key = keys.get(random.nextInt(keys.size()));
            robot.keyPress(key);
            System.out.println("Key: " + key);
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
    public void actiontest(int size) throws AWTException {

        game.skipTo("level1");

        InputGenerator input = new InputGenerator(game);
        Robot robot = new Robot();

        //actions = List.of(input::up, input::down, input::left, input::right);
        List<Runnable> actions_witout_up = List.of(input::down, input::down, input::left, input::right);
        List<Runnable> actions_witout_down = List.of(input::up, input::up, input::left, input::right);
        List<Runnable> actions_witout_left = List.of(input::up, input::down, input::right, input::right);
        List<Runnable> actions_witout_right = List.of(input::up, input::down, input::left, input::left);

        robot.delay(2000);
        int index = 3;
        for (int i = 0; i < size; i++) {

            // prevent the player from making meaningless moves( like moving up and down at the same time)
            List<Runnable> from = switch(index){
                case 0 -> actions_witout_down;
                case 1 -> actions_witout_up;
                case 2 -> actions_witout_right;
                default -> actions_witout_left;
            };

            testClock();

            from.get(index).run();
            System.out.println("Action: " + from.get(index));
            robot.delay(10);
            index = random.nextInt(from.size());
        }
    }
    public void testClock() throws IllegalStateException {
        if(GameClock.get().currentLevelTime() < 0) {
            throw new IllegalStateException("Time is negative");
        }
    }
    public static void main(String[] args) throws AWTException, IllegalArgumentException {
        Fuzz f = new Fuzz();
        //f.randomKeys(100);
        f.actiontest(100);
    }
    @Test
    public void test1() throws AWTException {
        Fuzz f = new Fuzz();
        //f.randomKeys(100);
        f.actiontest(10000);
    }

    @Test
    public void test2() throws AWTException {
        Fuzz f = new Fuzz();
        //f.randomKeys(100);
        f.actiontest(10000);
    }
}