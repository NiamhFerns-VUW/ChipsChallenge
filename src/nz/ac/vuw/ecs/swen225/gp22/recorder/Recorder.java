package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.app.*;
import nz.ac.vuw.ecs.swen225.gp22.persistency.*;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Stack;

public class Recorder {

    private ArrayList<Stack<Step>> recordings;
    private ArrayList<Step> currentRecording;

    private String currentLevel = "zero";
    private boolean startRecording = false;


    /**
     * Initialises List of RecordedLevels and List of moves from currentRecording
     */
    public Recorder(){
        recordings = new ArrayList<>();
        currentRecording = new ArrayList<>();
    }

    /**
     * Sets Recorder to start recording inputs from App
     */
    public void start(){
        startRecording = true;
    }

    /**
     * Triggers once currentRecording is saved at the end of Level
     * Clears the currentRecording of all moves from the level, ready for recording new level
     * Sets currentLevel to zero - should be set at beginning of each level
     * Sets Recorder to stop recording inputs - in case of a lull period before levels
     */
    public void reset(){
        currentRecording.clear();
        currentLevel = "zero";
        startRecording = false;
    }

    /**
     * From App, will let recorder know what level is currently being recorded
     * @param level - that Player is currently playing
     */
    public void setLevel(String level){
        this.currentLevel = level;
    }

    /**
     * Recording when Player moves Chip 'Up'
     */
    public void up(){
        if(startRecording){
            long time = GameClock.get().currentLevelTime();
            currentRecording.add(new Step("Up", time));
            System.out.println("Move UP recorded at time " +time);
        }
    }

    /**
     * Recording when Player moves Chip 'Down'
     */
    public void down(){
        if(startRecording){
            long time = GameClock.get().currentLevelTime();
            currentRecording.add(new Step("Down", time));
            System.out.println("Move DOWN recorded at time " +time);
        }
    }

    /**
     * Recording when Player moves Chip 'Left'
     */
    public void left(){
        if(startRecording){
            long time = GameClock.get().currentLevelTime();
            currentRecording.add(new Step("Left", time));
            System.out.println("Move LEFT recorded at time " +time);
        }
    }

    /**
     * Recording when Player moves Chip 'Right'
     */
    public void right(){
        if(startRecording){
            long time = GameClock.get().currentLevelTime();
            currentRecording.add(new Step("Right", time));
            System.out.println("Move RIGHT recorded at time " +time);
        }
    }

    /**
     * Recording when game updates and Player has not moved
     */
    public void none(){
        if(startRecording){
            long time = GameClock.get().currentLevelTime();
            currentRecording.add(new Step("None", time));
        }
    }

    /**public Element setGameSave(long time){
        GameSave gs = new GameSave();
        gs.time = (int) time;
        return gs.toXml();
    }*/

    /**
     * Reverse inputs Steps from currentRecording into a Stack of Steps to be called at end of level
     * Then resets the Recorder - ready for recording a new level
     */
    public void saveRecording(){
        Stack<Step> gameHistory = new Stack<>();
        for(int i = currentRecording.size()-1; i >= 0; i--){
            gameHistory.push(currentRecording.get(i));
        }
        recordings.add(gameHistory);
        //System.out.println(currentLevel+" recorded and saved.");
        reset();
    }

    /**
     *  For IntegrationDay will only pull the first Recording from the List
     * @return Replayer  - automatically plays the Stack of all the moves from the recorded level
     */
    public Replayer loadRecording(){
        return new Replayer(recordings.get(0));
    }

    /**
     * @return currentRecording - List of moves from the current recorded level
     */
    public ArrayList<Step> getCurrentRecording(){
        return currentRecording;
    }

    /**
     * @return recordings - Contains List of Recorded levels
     */
    public ArrayList<Stack<Step>> getListOfRecordings(){
        return recordings;
    }

}
