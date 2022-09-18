package nz.ac.vuw.ecs.swen225.gp22.persistency;

import com.google.common.base.CaseFormat;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import org.apache.commons.text.CaseUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.util.StringUtils;

public class Converter {

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
        return Optional.empty();
    }

    // tileToXMLElement
    // xmlElementToTile

    // cellToXMLElement
    // xmlElementToCell

}
