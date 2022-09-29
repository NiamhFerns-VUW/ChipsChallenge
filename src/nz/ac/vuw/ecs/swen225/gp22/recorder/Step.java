package nz.ac.vuw.ecs.swen225.gp22.recorder;

import nz.ac.vuw.ecs.swen225.gp22.app.*;
import org.dom4j.Element;


record Step(String move, long time){

    /**
     * For IntegrationDay what will be printed to console when RecordedLevel is being replayed
     * @return String - Displays what the recorded move was and what time it was made
     */
    public String replayerToString(){
        return "Replaying step: Chip moved "+move+" at time: "+time;
    }

}
