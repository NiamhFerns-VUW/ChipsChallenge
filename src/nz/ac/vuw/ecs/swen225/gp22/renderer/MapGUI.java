package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Domain;

import java.awt.*;

public class MapGUI {

    /**
     * A method that takes the Graphics, calls to domain for the 2d map array and the draws the map.
     * @param g
     */
    public void updateMap(Graphics g){
        //This is probably not the correct way to get access to the Cells 2d array but for now it will do
        Domain d = new Domain();
        Cell[][] array = d.getLevel().get().cells;//.getCells();
        for(int i = 0; i < array.length; i++){
            for (int j=0; j < array[i].length; j++){
                Cell cell = array[i][j];
                cell.getStoredTile(); //need to implement drawing specific for each tile
                cell.getEntities(); //need to implement drawinf specifics for entities
            }
        }
    }
}

