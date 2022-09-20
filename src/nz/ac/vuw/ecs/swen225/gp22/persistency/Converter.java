package nz.ac.vuw.ecs.swen225.gp22.persistency;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Converter {

    /**
     * Used to get all public non-null strings of an object which are the properties I'll be saving to xml.
     * @param o
     * @return
     */
    public static List<Field> getNonNullPublicStringFields(Object o) {
        return Arrays.stream(o.getClass().getDeclaredFields()).
            filter(e -> e.getType().getSimpleName().equals("String"))
            .filter(e -> {
                try {
                    Object o1 = e.get(o);
                    System.out.println("o1: " + o1);
                    return o1 != null && e.getModifiers() == Modifier.PUBLIC;
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }).toList();
    }


    /**
     * Pretty prints xml element
     * @param element
     */
    public static void printElement(Element element) {
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(System.out, format);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.write( element );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets entity class name and its public non-null string fields and creates an
     * xml element &lt;entity&gt; with the class name and its public non-null string
     * fields and their values as child elements.
     * @param entity
     * @return
     */
    public static Element entityToXmlElement(Entity entity) {
        List<Field> nonNullPublicStringFields = getNonNullPublicStringFields(entity);
        Document document = DocumentHelper.createDocument();
        Element entityElement = document.addElement("entity");
        if (entity.getClass().isAnonymousClass()) {
            entityElement.addElement("name").addText(entity.getClass().getSuperclass().getSimpleName());
        } else {
            entityElement.addElement("name").addText(entity.getClass().getSimpleName());
        }
        nonNullPublicStringFields.forEach(field->{
            Element fieldElement = entityElement.addElement(field.getName());
            try {
                Object o = field.get(entity);
                fieldElement.addText(o.toString());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return entityElement;
    }
    public static Entity xmlElementToEntity(Element element) {
        return null;
    }

    // tileToXMLElement
    // xmlElementToTile

    // cellToXMLElement
    // xmlElementToCell

}
