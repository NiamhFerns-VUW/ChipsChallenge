package nz.ac.vuw.ecs.swen225.gp22.recorder;

import com.fasterxml.jackson.core.type.TypeReference;
import nz.ac.vuw.ecs.swen225.gp22.app.*;
import nz.ac.vuw.ecs.swen225.gp22.persistency.*;
import org.dom4j.Document;
import org.dom4j.Element;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Stack;


public class Recorder {

    private ArrayList<Step> currentRecording;

    private String currentLevel = "zero";
    private boolean startRecording = false;


    /**
     * Initialises List of RecordedLevels and List of moves from currentRecording
     *
     * @author Santino Gaeta
     */
    public Recorder(){
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
     * @return currentRecording - List of moves from the current recorded level
     *
     * @author Santino Gaeta
     */
    public ArrayList<Step> getCurrentRecording(){
        return currentRecording;
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
        if(currentRecording.size() == 0){return;}       //For changing from micro to Main State in App

        saveStepArrayListToXml(currentRecording);

        //Convert using Persistency from ArrayList<Step> to Xml - returns XmlFile
        //Save file to folder or Recordings/Level folder

        System.out.println(currentLevel+" recorded and saved."); //For Testing

        reset();
    }

    /**
     *  loadRecording brings up a file picker for the user to choose which recording to laod
     *  The file choosen gets sent to Persistency to be converted into a Stack<Step>
     *
     * @return Replayer  - initialised with Stack from converted xmlFile using Persistency classes
     *
     * @author Santino Gaeta
     */
    public static Replayer loadRecording(){
        JFileChooser jfc = new JFileChooser("./src/levels/");
        jfc.setDialogTitle("Select XmlFile of Recording to Replay");
        jfc.setFileFilter(new FileNameExtensionFilter("Extensible Markup Language", "xml"));
        int check = jfc.showOpenDialog(null);
        if(check == JFileChooser.CANCEL_OPTION) {
            return null;
        }
        File xmlFile = jfc.getSelectedFile();
        //TODO implement this method into Persistency?
        return new Replayer(convertXmlToHistoryStack(xmlFile)); //Not implemented in Persistency yet
    }


    public void saveStepArrayListToXml(ArrayList<Step> chipMoves){
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try{
            mapper.writeValue(new File("./src/levels/"+currentLevel+"Recording.xml"), chipMoves);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }


    public static Stack<Step> convertXmlToHistoryStack(File xmlFile){
        XmlMapper mapper = new XmlMapper();
        try{
            InputStream input = new FileInputStream(xmlFile);
            TypeReference<ArrayList<Step>> step = new TypeReference<ArrayList<Step>>(){};
            ArrayList<Step> chipHistory = mapper.readValue(input, step);
            Stack<Step> history = new Stack<>();
            for(int i = chipHistory.size()-1; i >= 0; i--){
                history.push(chipHistory.get(i));
            }
            return history;
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }


}

