package com.filtering;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;

/**
 * Realization XMLParser
 * Use SAXParser cause app will use XML files more than 100MB
 *
 * @author Nicolas Asinovich.
 */
public class XMLParser {

    private static String filePath = "src/main/resources/input/input.xml";
    private static final String CHARSET_NAME = "UTF-8";
    private static boolean append = true;
    private static int countName;
//    private static HashMap<String, FilteringHandler> parseData;

    public void parseXML() {
        readXML(filePath);
        writeXML();
    }

    /**
     * Read xml file
     * @param filePath
     */
    private void readXML (String filePath) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        FilteringHandler filteringHandler = new FilteringHandler();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(filePath, filteringHandler);
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
            OutputStream outputStream = new FileOutputStream(exists(filePath), append);

            XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(
                    new OutputStreamWriter(outputStream, CHARSET_NAME));

            out.writeStartElement("rule");
            out.writeCharacters("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

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

    /**
     * check existing output file
     * @param fileAddress
     * @return
     * @throws FileNotFoundException
     */
    private static String exists(String fileAddress) throws FileNotFoundException {
        File file = new File(fileAddress);
        if (file.exists()){
            fileAddress = "src/main/resources/output/output_" + countName++ + ".xml";
        }
        return fileAddress;
    }
}
