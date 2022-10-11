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
     *
     * @author Santino Gaeta
     */
    public Recorder(){
        recordings = new ArrayList<>();
        currentRecording = new ArrayList<>();
    }

    /**
     * Sets Recorder to start recording inputs from App
     *
     * @author Santino Gaeta
     */
    public void start(){
        startRecording = true;
    }

    /**
     * Triggers once currentRecording is saved at the end of Level
     * Clears the currentRecording of all moves from the level, ready for recording new level
     * Sets currentLevel to zero - should be set at beginning of each level
     * Sets Recorder to stop recording inputs - in case of a lull period before levels
     *
     * @author Santino Gaeta
     */
    public void reset(){
        currentRecording.clear();
        currentLevel = "zero";
        startRecording = false;
    }

    /**
     * From App, will let recorder know what level is currently being recorded
     * @param level - that Player is currently playing
     *
     * @author Santino Gaeta
     */
    public void setLevel(String level){
        this.currentLevel = level;
    }

    /**
     * Recording when Player moves Chip 'Up'
     *
     * @author Santino Gaeta
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
     *
     * @author Santino Gaeta
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
     *
     * @author Santino Gaeta
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
     *
     * @author Santino Gaeta
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
     *
     * @author Santino Gaeta
     */
    public void none(){
        if(startRecording){
            long time = GameClock.get().currentLevelTime();
            currentRecording.add(new Step("None", time));
        }
    }

    /**
     * Reverse inputs Steps from currentRecording into a Stack of Steps to be called at end of level
     * Then resets the Recorder - ready for recording a new level
     *
     * @author Santino Gaeta
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
     *
     * @author Santino Gaeta
     */
    public Replayer loadRecording(){
        return new Replayer(recordings.get(0));
    }

    /**
     * @return currentRecording - List of moves from the current recorded level
     *
     * @author Santino Gaeta
     */
    public ArrayList<Step> getCurrentRecording(){
        return currentRecording;
    }

    /**
     * @return recordings - Contains List of Recorded levels
     *
     * @author Santino Gaeta
     */
    public ArrayList<Stack<Step>> getListOfRecordings(){
        return recordings;
    }

}
