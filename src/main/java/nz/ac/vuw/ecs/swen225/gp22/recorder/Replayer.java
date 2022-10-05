package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.util.Stack;

/**
 *
 */
public class Replayer {

    private int replaySpeed = 1;
    Stack<Step> history;
    Stack<Step> prev;
    boolean autoReplay = false;

    public Replayer(Stack<Step> gameHistory){
        history = gameHistory;
        prev = new Stack<Step>();
    }

    /**
     * Step by Step replay
     */
    //peek at Stack to see if the Move is different/not NONE
    //if NONE, push(Step Stack.pop()) into other Stack until reaching a different Step
    //If not NONE, Step replay = Stack.pop(); OtherStack.push(replay); replay.draw();

    /**
     * Automatic Replay
     */
    //Set this.autoReplay = true;
    //while(autoreplay) - true
    //(time in between each step) / replaySpeed



    public void setReplaySpeed(int newSpeed){
        this.replaySpeed = newSpeed;
    }

}
