package nz.ac.vuw.ecs.swen225.gp22.persistency;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.print.Doc;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class FileHandler {

    final Path levelPath;
    final Path savePath;

    FileHandler(Path levelPath, Path savePath) {
        this.levelPath = levelPath;
        this.savePath = savePath;
    }
    public Document getXML(Path xmlFilePath) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new File(xmlFilePath.toString()));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return document;
    }
    public void saveXML(Document document, String id) {
        FileWriter out = null;
        try {
            out = new FileWriter(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            document.write(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
