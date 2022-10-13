package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.app.*;

import java.util.Stack;

/**
 * Replayer represents an object that can replay levels that have been recorded.
 * Implements Observer from App so the Automatic replay is synced with the GameClock update()
 * Two modes of Replaying include AutoReplay, where each step is invoked at the LevelTime they
 * were executed in the original play-through, as the GameClock ticks. This ReplaySpeed can be altered by
 * the user to make the AutoReplay faster or slower.
 * And StepByStep allows User to move through each step made by chip in their own time, on Step at a time.
 *
 * @author Santino Gaeta
 */
public class Replayer implements Observer { //need to make Observer public in App

    private int replaySpeed = 1;
    private Stack<Step> history;
    private Stack<Step> prev;
    boolean autoReplayState = true;
    private InputGenerator inputGenerator;
    private GameHandler gameHandler;

    /**
     * Replayer initialised with a Stack of Chip's moves to go through and replay
     * Also GameHandler of currently level and InputGenerator invoking moves on Chip
     * AutoReplay mode on default
     * @param gameHistory - Stack of Steps
     *
     * @author Santino Gaeta
     */
    public Replayer(Stack<Step> gameHistory){
        history = gameHistory;
        prev = new Stack<>();
        gameHandler = GameHandler.get();
        inputGenerator = new InputGenerator(gameHandler);
    }

    /**
     * Implementing GameClock in App, Replayer will update each tick of GameClock
     * If the history Stack is empty nothing happens
     * Depending on the autoReplayState either StepbyStep or AutomaticReplay will be called
     *
     * @author Santino Gaeta
     */
    @Override
    public void update() {
        if(history.isEmpty()){return;}
        if(!autoReplayState){stepByStep(); return;} //take this out? And just have button call stepByStep method itself?
        else {autoReplay();}
    }

    /**
     * Automatic Replay
     * If the next move occurs at the currentLeveltime of the GameClock - will replay move on update()
     * Also pushes each move into the prev Stack in case User wants to go backwards (Backwards not implemented yet)
     *
     * @author Santino Gaeta
     */
    public void autoReplay(){
        if(history.peek().getTime()!=GameClock.get().currentLevelTime()){return;}
        Step currentStep = history.pop();
        prev.push(currentStep);
        replayStep(currentStep);
        System.out.println(currentStep.replayerToString());
    }

    /**
     * Step-by-Step Replay
     * Sets Automatic replay off in case Automatic replay was currently on
     * Moves Step from history Stack to prev Stack and replays that move direction
     * If move was direction "None" will look for next step not "None" and return that instead
     *
     * @return - move in direction that Chip had made
     *
     * @author Santino Gaeta
     */
    public void stepByStep(){
        autoReplayState = false;
        Step currentMove = history.pop();
        prev.push(currentMove);
        if(currentMove.getMove().equals("None")){
            Step validMove = skipEmptySteps();
            System.out.println("StepbyStep skipping None Actions to : "+validMove.replayerToString());
            replayStep(validMove);
        }
        System.out.println("StepbyStep: "+currentMove.replayerToString());
        replayStep(currentMove);
    }

    /**
     * Invokes inputGenerator from App to move Chip in Game during replay
     * @param step - Step popped from history Stack to be invoked on Chip
     *
     * @author Santino Gaeta
     */
    public void replayStep(Step step){
        if(step.getMove().equals("Left")){inputGenerator.left();}
        else if (step.getMove().equals("Right")){inputGenerator.right();}
        else if (step.getMove().equals("Up")){inputGenerator.up();}
        else if (step.getMove().equals("Down")){inputGenerator.down();}
    }

    /**
     * Used during Step-by-Step replay when there is a "None" direction move
     * Will look through history Stack until a move that is not "None" direction appears and returns it
     * If Stack is empty and no moves other than "None" are found, the last move is returned anyway, even if "None"
     *
     * @return - move that is not "None" or returns the last move of the stack
     *
     * @author Santino Gaeta
     */
    public Step skipEmptySteps(){
        while(!history.isEmpty()){
            Step move = history.pop();
            prev.push(move);
            if(!move.getMove().equals("None")){
                return move;
            }
        }
        //takes the last move (even "None") off prev stack
        history.push(prev.pop());
        return history.pop();
    }

    /**
     * Resets Replay if player wants to replay the same level again
     * Moves Steps from prev Stack back to history Stack and sets speed back to default
     *
     * @author Santino Gaeta
     */
    public void resetReplayer(){
        //display level back to the beginning (reload through app?)
        while(!prev.isEmpty()){history.push(prev.pop());}
        autoReplayState = false;
        replaySpeed = 1;
    }

    /**
     * Returns the Stack containing Chip's history of moves
     * @return Stack of Steps (Chip's move history)
     *
     * @author Santino Gaeta
     */
    public Stack<Step> getHistory(){
        return history;
    }

    /**
     * Returns the Stack containing Chip's moves already made by the Replayer
     * @return Stack of Steps (Chip's replay moves history)
     *
     * @author Santino Gaeta
     */
    public Stack<Step> getPrevStack(){
        return prev;
    }

    /**
     * Changes speed of automatic replay to play faster or slower by changing tick interval on GameClock
     * @param newSpeed - int the user wishes to change the automatic replay speed to
     *
     * @author Santino Gaeta
     */
    public void setReplaySpeed(int newSpeed){
        this.replaySpeed = newSpeed;
        //GameClock.setTickRate(1000/GameClock.getFrameRate() * replaySpeed);
    }

    /**
     * Switches the Replayer's state to change from StepByStep into Automatic Replay mode
     * Sets autoReplayState boolean to true (false for StepByStep)
     *
     * @author Santino Gaeta
     */
    public void setAutoReplay(){
        autoReplayState = true;
        //update();
    }

}
