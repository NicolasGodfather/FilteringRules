package com.filtering;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Realization XMLParser
 * Use SAXParser cause app will use XML files more than 100MB
 *
 * @author Nicolas Asinovich.
 */
class XMLParser{

    private FilteringHandler filteringHandler = new FilteringHandler();

    /**
     * Read xml file
     * @param filePath
     */
    void parseXML (String filePath) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(filePath, filteringHandler);

            writeXML();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write xml file
     */
    private void writeXML () {
        try {
            String filePathOut = "src/main/resources/output.xml";
            OutputStream outputStream = new FileOutputStream((filePathOut), true);
            XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(
                    new OutputStreamWriter(outputStream, "UTF-8"));
//перенести в фильтр!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            HashMap<String, Rule> data = filteringHandler.getDataRule();
// TODO: maybe here is mistake
            out.writeStartDocument();
            out.writeStartElement("rules");

            for (Map.Entry<String, Rule> entry : data.entrySet()) {
                out.writeStartElement(entry.getKey() + " " + entry.getValue());
            }
            out.writeEndElement();
            out.writeEndDocument();

            out.close();
            outputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
