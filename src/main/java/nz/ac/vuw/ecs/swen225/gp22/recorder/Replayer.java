package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.util.Stack;


public class Replayer {

    private int replaySpeed = 1;
    private Stack<Step> history;
    private Stack<Step> prev;
    boolean autoReplay = false;


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
    //peek at Stack to see if the Move is different/not NONE
    //if NONE, push(Step Stack.pop()) into other Stack until reaching a different Step
    //If not NONE, Step replay = Stack.pop(); OtherStack.push(replay); replay.draw();

    public Stack<Step> getHistory(){
        return history;
    }

    public void setReplaySpeed(int newSpeed){
        this.replaySpeed = newSpeed;
    }

}
