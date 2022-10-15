package nz.ac.vuw.ecs.swen225.gp22.recorder;


import nz.ac.vuw.ecs.swen225.gp22.app.*;

import java.awt.event.KeyEvent;
import java.util.Stack;


/**
 * Replayer represents an object that can replay levels that have been recorded.
 * Implements Observer from App so the Automatic replay is synced with the GameClock update()
 * Two modes of Replaying include AutoReplay, where each step is invoked at the LevelTime they
 * were executed in the original play-through, as the GameClock ticks. This ReplaySpeed can be altered by
 * the user to make the AutoReplay faster or slower.
 * And StepByStep allows User to move through each step made by chip in their own time, on Step at a time.
 *
 * @author Santino Gaeta 300305101
 */
public class Replayer implements Observer {

    private static int replaySpeed = GameClock.get().DEFAULT_FRAMERATE;
    private static Stack<Step> history;
    private static Stack<Step> prevSteps;
    private String currentLevel;
    static boolean autoReplayState = true;
    private static InputGenerator inputGenerator;
    private GameHandler gameHandler;

    /**
     * Replayer initialised with a Stack of Chip's moves to go through and replay
     * Also GameHandler of currently level and InputGenerator invoking moves on Chip
     * AutoReplay mode on default at default speed
     *
     * @param gameHistory - Stack<Steps> Chip's movements to be replayed
     * @param level - A String of which level was recorded
     *
     * @author Santino Gaeta
     */
    public Replayer(Stack<Step> gameHistory, String level){
        setHistory(gameHistory);
        currentLevel = level;
        setPrevStack();
        gameHandler = GameHandler.get();
        setInputGenerator(gameHandler);
        setReplaySpeed(GameClock.get().DEFAULT_FRAMERATE);
        setBindings();
    }

    private void setHistory(Stack<Step> stack){
        history = stack;
    }

    private void setPrevStack(){
        prevSteps = new Stack<>();
    }

    private void setInputGenerator(GameHandler gh){
        inputGenerator = new InputGenerator(gh);
    }

    private void setBindings(){
        GameHandler.get().addBindings(KeyEvent.VK_EQUALS, Replayer::speedUp, () -> {});
        GameHandler.get().addBindings(KeyEvent.VK_MINUS, Replayer::speedDown, () -> {});
        GameHandler.get().addBindings(KeyEvent.VK_PERIOD, Replayer::stepForward, () -> {});
        GameHandler.get().addBindings(KeyEvent.VK_COMMA, Replayer::stepBackward, () -> {});
    }

    /**
     * Implementing GameClock in App, Replayer will update each tick of GameClock
     * If the history Stack, empty time is speed up to return to Menu
     * If AutoReplay Mode and the next Step's time matches up with GameClock,
     * autoReplay() will replay the next movement of Chip
     *
     * @author Santino Gaeta
     */
    @Override
    public void update() {
        if(history.isEmpty()){GameHandler.get().skipTo("startmenu");}
        else if(autoReplayState && history.peek().getTime()==GameClock.get().currentLevelTime()){
            autoReplay();
        }
    }

    /**
     * Automatic Replay
     * If the next move occurs at the currentLeveltime of the GameClock - will replay move on update()
     * Also pushes each move into the prev Stack
     *
     * @author Santino Gaeta
     */
    public static void autoReplay(){
        Step currentStep = history.pop();
        prevSteps.push(currentStep);
        replayStep(currentStep);
    }

    /**
     * Step-by-Step Replay
     * Sets Automatic replay off in case Automatic replay was currently on
     * Moves Step from history Stack to prev Stack and replays that move direction
     * If move was direction "None" will look for next step not "None" and replay that instead
     *
     * @author Santino Gaeta
     */
    public static void stepForward(){
        if(history.isEmpty()){return;}
        autoReplayState = false;
        setReplaySpeed(15);
        Step currentMove = history.pop();
        prevSteps.push(currentMove);
        replayStep(currentMove);
        System.out.println("Backwards: " + currentMove.toString());
    }

    /**
     * Step-by-Step Replay
     * Sets Automatic replay off in case Automatic replay was currently on
     * Moves Step from history Stack to prev Stack and replays that move direction
     * If move was direction "None" will look for next step not "None" and replay that instead
     *
     * @author Santino Gaeta
     */

    public static void stepBackward(){
        if(prevSteps.isEmpty()){return;}
        setReplaySpeed(15);
        autoReplayState = false;
        Step currentMove = prevSteps.pop();
        history.push(currentMove);
        replayBackStep(currentMove);
        System.out.println("Backwards: " + currentMove.toString());
    }

    /**
     * Invokes InputGenerator from App to move Chip in Game during replay
     * @param step - Step popped from history Stack to be invoked on Chip
     *
     * @author Santino Gaeta
     */
    public static void replayStep(Step step){
        if(step.getMove().equals("Left")){inputGenerator.left();}
        else if (step.getMove().equals("Right")){inputGenerator.right();}
        else if (step.getMove().equals("Up")){inputGenerator.up();}
        else if (step.getMove().equals("Down")){inputGenerator.down();}
    }

    /**
     * Invokes InputGenerator from App to move Chip in Game during replay
     * @param step - Step popped from history Stack to be invoked on Chip
     *
     * @author Santino Gaeta
     */
    public static void replayBackStep(Step step){
        if(step.getMove().equals("Left")){inputGenerator.right();}
        else if (step.getMove().equals("Right")){inputGenerator.left();}
        else if (step.getMove().equals("Up")){inputGenerator.down();}
        else if (step.getMove().equals("Down")){inputGenerator.up();}
    }

    /**
     * Initialises the replaySpeed when Replayer is initialised
     * @param newSpeed - DEFAULT_FRAMERATE of GameClock ( = 60 )
     *
     * @author Santino Gaeta
     */
    public static void setReplaySpeed(int newSpeed){
        replaySpeed = newSpeed;
        GameClock.setTickRate(replaySpeed);
        restartClock();
    }

    public static void speedUp(){
        if(replaySpeed > 180){return;}
        replaySpeed += 15;
        GameClock.setTickRate(replaySpeed);
        restartClock();
    }

    public static void speedDown(){
        if(replaySpeed < 20){return;}
        replaySpeed -= 15;
        GameClock.setTickRate(replaySpeed);
        restartClock();
    }

    public static void restartClock(){
        GameClock.get().pause();
        GameClock.get().unpause();
    }

    /**
     * Returns the level that this Replayer was recorded from
     * @return String - containing which level this Replayer will replay
     *
     * @author Santino Gaeta
     */
    public String getReplayLevel(){return currentLevel;}

}
