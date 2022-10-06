package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.util.Stack;


public class Replayer {

    private int replaySpeed = 1;
    private final Stack<Step> setHistory;
    private Stack<Step> history;
    private Stack<Step> prev;
    boolean autoReplay = false;


    public Replayer(Stack<Step> gameHistory){
        setHistory = gameHistory;
        history = gameHistory;
        prev = new Stack<>();
    }

    /**
     * Automatic Replay
     * While the Stack isn't empty will print each move every 2secs
     * Also pushes each move into the prev Stack in case User wants to go backwards (Backwards not implemented yet)
     */
    public void autoReplay(){
        autoReplay = true;
        while(!history.isEmpty()){
            Step currentStep = history.pop();
            prev.push(currentStep);
            //System.out.println(currentStep.replayerToString());
            try {
                Thread.sleep(2000/replaySpeed);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException Exception" + e.getMessage());
            }
        }
    }

    /**
     * Step by Step replay
     */
    public Step stepByStep(){
        autoReplay = false;
        Step currentMove = history.pop();
        prev.push(currentMove);
        if(currentMove.move().equals("None")){
            Step validMove = skipEmptySteps();
            return validMove;
        }
        return currentMove;

    }

    //peek at Stack to see if the Move is different/not NONE
    //if NONE, push(Step Stack.pop()) into other Stack until reaching a different Step
    //If not NONE, Step replay = Stack.pop(); OtherStack.push(replay); replay.draw();
    public Step skipEmptySteps(){
        while(!history.isEmpty()){
            Step move = history.pop();
            prev.push(move);
            if(!move.move().equals("None")){
                return move;
            }
        }
        //takes the last move (Still None) off prev stack
        history.push(prev.pop());
        return history.pop();
    }

    public void resetReplayer(){
        //display level back to the beginning (reload through app?)
        history = setHistory;
        prev = new Stack<>();
    }

    public Stack<Step> getHistory(){
        return history;
    }

    public void setReplaySpeed(int newSpeed){
        this.replaySpeed = newSpeed;
    }

}
