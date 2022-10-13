package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.app.*;
import org.dom4j.Element;


public class Step{

    private String move;
    private long time;

    /**
     * Default Constructor used for XmlConversion using Jackson
     *
     * @author Santino Gaeta
     */
    public Step(){}

    /**
     * Constructor used when Chip moves during a Level being recorded
     * @param move - Direction Chip moved
     * @param time - Time remaining in level when Chip executed this move
     *
     * @author Santino Gaeta
     */
    public Step(String move, long time){
        this.move = move;
        this.time = time;
    }

    /**
     * Returns move of the Step
     * @return move Chip made contained in this Step
     *
     * @author Santino Gaeta
     */
    public String getMove(){
        return move;
    }

    /**
     * Returns time of the Step
     * @return time Chip made move contained in this Step
     *
     * @author Santino Gaeta
     */
    public long getTime(){
        return time;
    }

    /**
     * Sets the move of the Step
     *
     * @author Santino Gaeta
     */
    public void setMove(String move) {
        this.move = move;
    }

    /**
     * Sets the time of the Step
     *
     * @author Santino Gaeta
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * For IntegrationDay what will be printed to console when RecordedLevel is being replayed
     * @return String - Displays what the recorded move was and what time it was made
     *
     * @author Santino Gaeta
     */
    public String replayerToString(){
        return "Replaying step: Chip moved "+move+" at time: "+time;
    }

}
