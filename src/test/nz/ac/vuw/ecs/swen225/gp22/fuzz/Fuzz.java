package nz.ac.vuw.ecs.swen225.gp22.fuzz;

import java.util.List;
import nz.ac.vuw.ecs.swen225.gp22.app.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;

import org.junit.jupiter.api.Test;

class Fuzz{
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
    /**
     * This the constructor of the Fuzz class
     */
    public Fuzz() {
        game = GameHandler.get();
        game.reset();
        game.start();
    }
    /**
     * This method is used Strategy Pattern to generate random inputs
     * @throws AWTException
     */
    public void testInputStrategy(Input input, int size, String level) throws AWTException {
        game.skipTo(level);
        Robot robot = new Robot();
        robot.delay(2000);
        for (int i = 0; i < size; i++) {
            int key = input.nextInput();
            if (input.isPressCtrl()) {
                robot.keyPress(KeyEvent.VK_CONTROL);
                System.out.println("Pressing control");
            }
            testClock();
            robot.keyPress(key);
            System.out.println("Pressing " + KeyEvent.getKeyText(key));
            robot.keyRelease(key);
            if (input.isPressCtrl()) {
                robot.keyRelease(KeyEvent.VK_CONTROL);
            }
        }
    }

    /**
     * this method generates a random sequence of inputs
     *             and then executes them
     * @param size the number of random inputs to generate
     * @param level the level to start the game on
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
            robot.delay(10);
        }
    }
    /**
     * This method generates a mouse click at a location
     * @throws AWTException
     */
    public void mouseTest() throws AWTException {
        Robot robot = new Robot();

        robot.mouseMove(1050, 550);
        robot.delay(200);
        robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
    }
    /**
     * this method generates a random sequence of actions functions from the
     *             list of actions and then executes them
     * @param size the number of random inputs to generate
     * @param level the level to start the game on
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
            System.out.println("Action: " + printActionName(index));
            robot.delay(10);
            index = random.nextInt(from.size());
        }
    }
    /**
     * This method is used to print the name of the action which works for actiontest() method
     * @param index
     * @return the name of the action
     */
    public String printActionName(int index) {
        switch(index) {
            case 0: return "up";
            case 1: return "down";
            case 2: return "left";
            case 3: return "right";
            default: return "unknown";
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
    /**
     * This method is used to test level 1
     * using int method to determine the strategy of generating random inputs
     * method 0: using Strategy Pattern to generate random inputs with ProbInput Strategy
     * method 1: using Strategy Pattern to generate random inputs with RandomInput Strategy
     * method 2: randomKeys() method, using Robot to generate random Key Events
     * method 3: actiontest() method, calling the action functions from the list of actions
     * @throws AWTException
     */
    @Test
    public void test1() throws AWTException {
        Fuzz f = new Fuzz();
        Input input1 = new Input( new ProbInput());
        Input input2 = new Input( new FollowedInput());

        int method = 3;
        switch (method) {
            case 0:
                f.testInputStrategy(input1, 100000, "level1");
                break;
            case 1:
                f.testInputStrategy(input2, 100000, "level1");
                break;
            case 2:
                f.randomKeys(100000, "level1");
                break;
            case 3:
                f.actiontest(100000, "level1");
                break;
            default:
                break;
        }

    }
    /**
     * This method is used to test level 2
     * using int method to determine the strategy of generating random inputs
     * method 0: using Strategy Pattern to generate random inputs with ProbInput Strategy
     * method 1: using Strategy Pattern to generate random inputs with RandomInput Strategy
     * method 2: randomKeys() method, using Robot to generate random Key Events
     * method 3: actiontest() method, calling the action functions from the list of actions
     * @throws AWTException
     */
    @Test
    public void test2() throws AWTException {
        Fuzz f = new Fuzz();
        Input input1 = new Input( new ProbInput());
        Input input2 = new Input( new FollowedInput());

        int method = 3;
        switch (method) {
            case 0:
                f.testInputStrategy(input1, 100000, "level2");
                break;
            case 1:
                f.testInputStrategy(input2, 100000, "level2");
                break;
            case 2:
                f.randomKeys(100000, "level2");
                break;
            case 3:
                f.actiontest(100000, "level2");
                break;
            default:
                break;
        }
    }
}

