package nz.ac.vuw.ecs.swen225.gp22.persistency;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
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
    public static String elementString(Element element) {
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            writer = new XMLWriter(byteArrayOutputStream, format);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.write( element );
            return byteArrayOutputStream.toString();
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
        System.out.println("xmlElementToEntity: \n" + elementString(element));
        Optional<Element> optionalName = element.elements().stream()
            .filter(e -> e.getName().equals("name"))
            .findAny();
        String name = "";
        List<Element> entityProperties = element.elements().stream().filter(e -> {
            return !(e.getName().equals("name"));
        }).toList();

        if (optionalName.isPresent()) {
            name = optionalName.get().getText();
        }
        try {
            Class<?> aClass = Class.forName("nz.ac.vuw.ecs.swen225.gp22.domain."+name);
            Object o = aClass.getDeclaredConstructor().newInstance();
            Entity entity = (Entity) o;
            entityProperties.forEach(e->{
                String fieldName = e.getName();
                String fieldValue = e.getText();
                Field[] declaredFields = entity.getClass().getDeclaredFields();
                Optional<Field> optionalField = Arrays.stream(declaredFields).filter(field -> {
                    return field.getName().equals(fieldName);
                }).findAny();
                if (optionalField.isEmpty()) {
                    throw new RuntimeException();
                }
                Field field = optionalField.get();
                try {
                    field.set(entity,fieldValue);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            });
            System.out.println("en prop: " + entityProperties);
            return entity;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    // tileToXMLElement
    // xmlElementToTile

    // cellToXMLElement
    // xmlElementToCell

}
