package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.app.*;
import nz.ac.vuw.ecs.swen225.gp22.persistency.*;
import org.dom4j.Document;

import java.util.ArrayList;
import java.util.Stack;

public class Recorder {

    private ArrayList<Stack<Step>> recordings;
    private ArrayList<Step> currentRecording;

    private String currentLevel = "zero";
    private boolean startRecording = false;


    public Recorder(){
        recordings = new ArrayList<>();
        currentRecording = new ArrayList<>();
    }

    public void start(){
        startRecording = true;
    }

    public void reset(){
        currentRecording.clear();
        currentLevel = "zero";
        startRecording = false;
    }

    public void setLevel(String level){
        this.currentLevel = level;
    }
    public void up(){
        if(startRecording){
            long time = GameClock.get().currentTime();
            currentRecording.add(new Step("Up", time));
        }
    }

    public void down(){
        if(startRecording){
            long time = GameClock.get().currentTime();
            currentRecording.add(new Step("Down", time));
        }
    }

    public void left(){
        if(startRecording){
            long time = GameClock.get().currentTime();
            currentRecording.add(new Step("Left", time));
        }
    }

    public void right(){
        if(startRecording){
            long time = GameClock.get().currentTime();
            currentRecording.add(new Step("Right", time));
        }
    }

    public void none(){
        if(startRecording){
            long time = GameClock.get().currentTime();
            currentRecording.add(new Step("None", time));
        }
    }

    /**
    public Element setGameSave(GameState gs){
        GameSave gs
        return gs.toXML();
    }
    */

    public void saveRecording(){
        //Reverse input allSteps into a Stack as each step is being converted into XML
        Stack<Step> gameHistory = new Stack<Step>();
        for(int i = currentRecording.size()-1; i <= 0; i--){
            gameHistory.push(currentRecording.get(i));
        }
        recordings.add(gameHistory);
        reset();
    }

    /** LoadRecording()
     *  that has information about each
     * @return Replayer that contains all the moves/gamestates for the recorded level
     */
    public Replayer loadRecording(){

        //return new Replayer(Stack history);
        return new Replayer(recordings.get(0));
    }


}
