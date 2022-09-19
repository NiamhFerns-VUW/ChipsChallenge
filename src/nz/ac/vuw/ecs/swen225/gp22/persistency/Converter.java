package nz.ac.vuw.ecs.swen225.gp22.persistency;

import com.google.common.base.CaseFormat;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import org.apache.commons.text.CaseUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.dom.DOMNodeHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.StringUtils;

public class Converter {

    public static Stream<Method> getStringSetters(Object object) {
        Document document = DocumentHelper.createDocument();
        Field[] declaredFields = object.getClass().getDeclaredFields();
        Method[] declaredMethods = object.getClass().getDeclaredMethods();
        Stream<Field> stringFields = Arrays.stream(declaredFields).filter(e -> e.getType().getSimpleName().equals("String"));
        return Arrays.stream(declaredMethods).filter(method -> {
            if (method.getName().startsWith("set")) {
                return method.getParameters().length == 1 && method.getParameters()[0].getType()
                    .getSimpleName().equals("String");
            }
            return false;
        });
    }

    public static Stream<Method> getStringGetters(Object object) {
        Document document = DocumentHelper.createDocument();
        Field[] declaredFields = object.getClass().getDeclaredFields();
        Method[] declaredMethods = object.getClass().getDeclaredMethods();
        Stream<Field> stringFields = Arrays.stream(declaredFields).filter(e -> e.getType().getSimpleName().equals("String"));
        return Arrays.stream(declaredMethods).filter(method -> {
            if (method.getName().startsWith("get")) {
                return method.getReturnType().getSimpleName().equals("String");
            }
            return false;
        });
    }
    public static Stream<Field> getValidStringFields(Object object) {
        Document document = DocumentHelper.createDocument();
        Field[] declaredFields = object.getClass().getDeclaredFields();
        Method[] declaredMethods = object.getClass().getDeclaredMethods();
        Stream<Field> stringFields = Arrays.stream(declaredFields).filter(e -> e.getType().getSimpleName().equals("String"));
        Stream<Method> stringSetters = Arrays.stream(declaredMethods).filter(method -> {
            if (method.getName().startsWith("set")) {
                return method.getParameters().length == 1 && method.getParameters()[0].getType()
                    .getSimpleName().equals("String");
            }
            return false;
        });
        Stream<Method> stringGetters = Arrays.stream(declaredMethods).filter(method -> {
            if (method.getName().startsWith("get")) {
                return method.getReturnType().getSimpleName().equals("String");
            }
            return false;
        });

        stringFields = stringFields.filter(e -> {
            // attempt to find relevant setter
            String name = e.getName();
            String substring = name.substring(0, 1).toUpperCase();
            StringBuilder pascalName = new StringBuilder();
            pascalName.append(name);
            pascalName.replace(0, 1, substring);

            // we've got a setter for it
            Optional<Method> setter = stringSetters.filter(stringSetter -> stringSetter.getName().equals("set" + pascalName)).findFirst();

            // we've got a getter for it
            Optional<Method> getter = stringGetters.filter(stringGetter -> stringGetter.getName().equals("get" + pascalName)).findFirst();
            return setter.isPresent() && getter.isPresent();
        });
        return stringFields;
    }

    // entityToXMLElement
    // xmlElementToEntity

    public static Optional<Element> entityToXmlElement(Entity entity) {
        Document document = DocumentHelper.createDocument();
        Stream<Field> validStringFields = getValidStringFields(entity);
        String simpleName = entity.getClass().getSimpleName();
        System.out.println(simpleName);
        Element entityElement = document.addElement("entity");
        entityElement.addElement("name").addText(simpleName);
        validStringFields.forEach(stringField->{
            String name = stringField.getName();
            System.out.println(name);
            Stream<Method> stringGetters = getStringGetters(entity);
            Method method = stringGetters.findFirst().orElse(null);
            try {
                Object invoke = Objects.requireNonNull(method).invoke(entity);
                String invokeString = invoke.toString();
                entityElement.addElement(name).addText(invokeString);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
        return Optional.of(entityElement);
    }
    public static Optional<Entity> xmlElementToEntity(Element element) {
        System.out.println("xmlElementToEntity on: " + element);
//        Stream<Element> elementStream = element.elements().stream();
        Optional<Element> optionalNameElement = element.elements().stream().filter(e -> e.getName().equals("name")).findFirst();
        // V elements to attempt to set
        Stream<Element> elementStream = element.elements().stream()
            .filter(e -> !(e.getName().equals("name")));
        if (optionalNameElement.isPresent()) {
            Element nameElement = optionalNameElement.get();
            String nameElementText = nameElement.getText();
            try {
                Class<?> aClass = Class.forName("nz.ac.vuw.ecs.swen225.gp22.domain."+nameElementText);
                Object o = aClass.getDeclaredConstructor().newInstance();
                Entity entity = (Entity) o;
                Stream<Method> stringSetters = getStringSetters(entity);
                elementStream.forEach(e->{
                    System.out.println("e: " + e);
                    System.out.println(e.getName());
                    Optional<Method> first = stringSetters.filter(
                        e2 -> e2.getName().substring(3).equalsIgnoreCase(e.getName())).findFirst();
                    if (first.isPresent()) {
                        Method method = first.get();
                        try {
                            method.invoke(entity,e.getName());
                        } catch (IllegalAccessException ex) {
                            throw new RuntimeException(ex);
                        } catch (InvocationTargetException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }

    // tileToXMLElement
    // xmlElementToTile

    // cellToXMLElement
    // xmlElementToCell

}
