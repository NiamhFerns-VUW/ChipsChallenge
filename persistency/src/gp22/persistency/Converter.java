package gp22.persistency;

import nz.ac.vuw.ecs.swen225.gp22.persistency.domain_classes.Cell;
import nz.ac.vuw.ecs.swen225.gp22.persistency.domain_classes.Chap;
import nz.ac.vuw.ecs.swen225.gp22.persistency.domain_classes.Entity;
import nz.ac.vuw.ecs.swen225.gp22.persistency.domain_classes.FreeTile;
import nz.ac.vuw.ecs.swen225.gp22.persistency.domain_classes.Monster;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Converter {

    /**
     * Takes entity properties (name and optionally direction) and creates xml element from it.
     * @param entity
     * @return
     */
    public static Element entityToXMLElement(Entity entity) {
        System.out.println("entity: " + entity);
        Document document = DocumentHelper.createDocument();
        Element entityElement = document.addElement("entity");
        Element name = entityElement.addAttribute("name", entity.getClass().getSimpleName());
        if (entity instanceof Chap c) {
            entityElement.addAttribute("direction",c.getDirection().toString());
        }
        if (entity instanceof Monster m) {
            entityElement.addAttribute("direction",m.getDirection().toString());
        }
        return entityElement;
    }

    /**
     * Takes freeTile and gets its classname and creates xml element from it.
     * @param freeTile
     * @return
     */
    public static Element freeTileToXMLElement(FreeTile freeTile) {
        Document document = DocumentHelper.createDocument();
        Element freeTileElement = document.addElement("freetile");
        Element name = freeTileElement.addAttribute("name", freeTile.getClass().getSimpleName());
        System.out.println(document.asXML());
        return freeTileElement;
    }

    /**
     * Takes a cell and creates a xml element from it, utilising freeTiletoXMLElement and
     * entityToXMLElement to convert the cells entity list and its freeTile to xml and nest
     * them in the cell element.
     * @param cell
     * @return
     */
    public static Element cellToXMLElement(Cell cell) {
        Document document = DocumentHelper.createDocument();
        Element cellElement = document.addElement("cell");
        cellElement.add(freeTileToXMLElement(cell.getFreeTile()));
        Element cellEntities = cellElement.addElement("entities");
        for (Entity entity : cell.getEntities()) {
            Element element = entityToXMLElement(entity);
            cellEntities.add(element);
        }
        return cellElement;
    }
}
