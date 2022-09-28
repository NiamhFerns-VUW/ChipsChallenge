package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.app.*;
import nz.ac.vuw.ecs.swen225.gp22.persistency.*;
import org.dom4j.Document;

import java.util.ArrayList;
import java.util.Stack;

public class Recorder {

    //GameState currentState;
    //Recording currentRecording; //Do we need Recording Class? Or stack here?
        //Store in List then in reverse (starting at back of list) store each Step into Stack

    private ArrayList<Stack<Step>> recordings;
    private ArrayList<Step> currentRecording;


    public Recorder(){
        recordings = new ArrayList<>();
        currentRecording = new ArrayList<>();
    }

    public void createStep(String direction, GameClock gc){
        currentRecording.add(new Step());
    }


    public void saveRecording(){
        //Reverse input allSteps into a Stack as each step is being converted into XML
        Stack<Step> gameHistory = new Stack<Step>();
        for(int i = currentRecording.size()-1; i <= 0; i--){
            gameHistory.push(currentRecording.get(i));
        }
        recordings.add(gameHistory);
        currentRecording.clear();
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
