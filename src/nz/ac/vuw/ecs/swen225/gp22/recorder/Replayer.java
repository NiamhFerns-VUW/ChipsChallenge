package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.app.*;
import java.util.Observer;
import java.util.Stack;


public class Replayer {

    private int replaySpeed = 1;
    private Stack<Step> history;
    private Stack<Step> prev;
    boolean autoReplayState = false;

    /**
     * Replayer initialised with a Stack of Chip's moves to go through and replay
     * @param gameHistory - Stack of Steps
     *
     * @author Santino Gaeta
     */
    public Replayer(Stack<Step> gameHistory){
        history = gameHistory;
        prev = new Stack<>();
    }

    public void update(){

    }





    /**
     * Automatic Replay
     * While the Stack isn't empty and AutoReplayState is true - will print each move every 2seconds
     * Also pushes each move into the prev Stack in case User wants to go backwards (Backwards not implemented yet)
     *
     * @author Santino Gaeta
     */
    public void autoReplay(){
        int count = 0;
        while(!history.isEmpty() && autoReplayState){
            //if(count == 6){this.stepByStep(); System.out.println("Starting some StepBystep"); break;} //Used for TESTING
            Step currentStep = history.pop();
            prev.push(currentStep);
            //count++;              //Used for TESTING
            System.out.println(currentStep.replayerToString());
            try {
                Thread.sleep(2000/replaySpeed);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException Exception" + e.getMessage());
            }
        }
    }

    /**
     * Step-by-Step Replay
     * Sets Automatic replay off in case Automatic relpay was currently on
     * Moves Step from history Stack to prev Stack and return that move direction
     * If move was direction "None" will look for next step not "None" and return that instead
     *
     *
     * @return - move in direction that Chip had made
     *
     * @author Santino Gaeta
     */
    public Step stepByStep(){
        autoReplayState = false;
        Step currentMove = history.pop();
        prev.push(currentMove);
        if(currentMove.getMove().equals("None")){
            Step validMove = skipEmptySteps();
            System.out.println("StepbyStep skipping None Actions to : "+validMove.replayerToString());
            return validMove;
        }
        System.out.println("StepbyStep: "+currentMove.replayerToString());
        return currentMove;

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
     * Changes speed of automatic replay to play faster or slower
     * @param newSpeed - int the user wishes to change the automatic replay speed to
     *
     * @author Santino Gaeta
     */
    public void setReplaySpeed(int newSpeed){
        this.replaySpeed = newSpeed;
    }

    /**
     * Switches the Replayer's state to change from StepByStep into Automatic Replay mode
     * Sets autoReplayState boolean to true (false for StepByStep)
     *
     * @author Santino Gaeta
     */
    public void setAutoReplay(){
        autoReplayState = true;
    }

}
