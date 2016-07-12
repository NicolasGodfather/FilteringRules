package com.filtering;

import com.filtering.element.Rule;
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
public class XMLParser implements Runnable{

    private String filePathIn = "src/main/resources/input/input.xml";
    private String filePathOut = "src/main/resources/output/output.xml";
    private FilteringHandler filteringHandler = new FilteringHandler();

    @Override
    public void run () {
        readXML(filePathIn);
//        writeXML();
    }

    /**
     * Read xml file
     * @param filePath
     */
    private void readXML (String filePath) {
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
            OutputStream outputStream = new FileOutputStream(filePathOut, true);
            XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(
                    new OutputStreamWriter(outputStream, "UTF-8"));

            HashMap<String, Rule> data = filteringHandler.getDataRule();
// TODO: maybe here is mistake 
            out.writeCharacters("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            for (Map.Entry<String, Rule> entry : data.entrySet()) {
                out.writeStartElement(entry.getKey() + " " + entry.getValue());
            }
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
