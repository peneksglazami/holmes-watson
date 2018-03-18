import org.apache.xerces.jaxp.DocumentBuilderFactoryImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ScheduleFileReader {

    public static List<Talk> readFromFile(File file) throws Exception {
        List<Talk> talks = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactoryImpl.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        NodeList nodes = document.getElementsByTagName("talk");

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            NodeList children = node.getChildNodes();

            String title = null;
            String author = null;
            for (int j = 0; j < children.getLength(); j++) {
                Node child = children.item(j);
                switch (child.getNodeName()) {
                    case "title":
                        title = child.getTextContent();
                        break;
                    case "author":
                        author = child.getTextContent();
                        break;
                }
            }
            talks.add(new Talk(title, author, TalkComplexityResolver.resolveTalkComplexity(title)));
        }

        return talks;
    }
}
