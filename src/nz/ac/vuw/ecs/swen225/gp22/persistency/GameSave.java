package nz.ac.vuw.ecs.swen225.gp22.persistency;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import javax.print.Doc;
import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Entity;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class GameSave {

    public Cell[][] cells;
    public int time;
    public List<Entity> inventory = List.of();
    public GameSave(){
        cells = new Cell[16][17];
    }

    public Element toXml(){
        Document document = DocumentHelper.createDocument();
        Element gameElement = document.addElement("game");
        gameElement.addElement("time").addText(String.valueOf(time));
        Element inventoryElement = gameElement.addElement("inventory");
        inventory.forEach(e->{
            Element entityElement = Converter.entityToXmlElement(e);
            inventoryElement.add(entityElement);
        });
        Element cellsElement = gameElement.addElement("cells");
        for (Cell[] cell : cells) {
            Element rowElement = cellsElement.addElement("row");
            for (int col = 0; col < cells[0].length; col++) {
                Element cellElement = Converter.cellToXmlElement(cell[col]);
                rowElement.add(cellElement);
            }
        }
        return gameElement;
    }
    public void fromXml(Element element) {
        String timeString = Objects.requireNonNull(
            element.elements().stream().filter(e -> e.getName().equals("time")).findFirst()
                .orElse(null)).getText();
        time = Integer.parseInt(timeString);
        Element cellsElement = element.elements().stream().filter(e -> e.getName().equals("cells"))
            .findFirst().orElse(null);
        if (cellsElement == null) {
            throw new RuntimeException("Cells element shouldn't be null!");
        }
        Element inventoryElement = element.elements().stream()
            .filter(e -> e.getName().equals("inventory")).findFirst().orElse(null);
        if (inventoryElement == null) {
            throw new RuntimeException("hey! inventory element can't be null!");
        }
        inventoryElement.elements().forEach(inventoryItemElement->{
            System.out.println("ie: " + inventoryItemElement);
        });
        IntStream.range(0,cellsElement.elements().size()).forEach(rowInd->{
            Element rowElement = cellsElement.elements().get(rowInd);
            IntStream.range(0,rowElement.elements().size()).forEach(colIndex->{
                Element cellElement = rowElement.elements().get(colIndex);
                Cell cell = Converter.xmlElementToCell(cellElement);
                cells[rowInd][colIndex] = cell;
            });
        });
    }
}