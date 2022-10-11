package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.util.Stack;


public class Replayer {

    private int replaySpeed = 1;
    private Stack<Step> history;
    private Stack<Step> prev;
    boolean autoReplayState = false;


    public Replayer(Stack<Step> gameHistory){
        history = gameHistory;
        prev = new Stack<>();
    }

    /**
     * Automatic Replay
     * While the Stack isn't empty will print each move every 2secs
     * Also pushes each move into the prev Stack in case User wants to go backwards (Backwards not implemented yet)
     */
    public void autoReplay(){
        int count = 0;
        while(!history.isEmpty() && autoReplayState){
            if(count == 6){this.stepByStep(); System.out.println("Starting some StepBystep"); break;}
            Step currentStep = history.pop();
            prev.push(currentStep);
            count++;
            System.out.println(currentStep.replayerToString());
            try {
                Thread.sleep(1000/replaySpeed);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException Exception" + e.getMessage());
            }
        }
    }

    /**
     * Step by Step replay
     */
    public Step stepByStep(){
        autoReplayState = false;
        Step currentMove = history.pop();
        prev.push(currentMove);
        if(currentMove.move().equals("None")){
            Step validMove = skipEmptySteps();
            System.out.println("StepbyStep skipping None Actions to : "+validMove.replayerToString());
            return validMove;
        }
        System.out.println("StepbyStep: "+currentMove.replayerToString());
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
        while(!prev.isEmpty()){history.push(prev.pop());}
        autoReplayState = false;
        replaySpeed = 1;

    }

    public Stack<Step> getHistory(){
        return history;
    }

    public Stack<Step> getPrevStack(){
        return prev;
    }

    public void setReplaySpeed(int newSpeed){
        this.replaySpeed = newSpeed;
    }

    public void setAutoReplay(){
        autoReplayState = true;
    }

}
