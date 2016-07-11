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

    private static String filePath = "src/main/resources/output/output.xml";
    private static final String CHARSET_NAME = "UTF-8";
    private static boolean append = true;
    private static int countName;

    /**
     * Read xml file
     * @param filePath
     */
    public void readXML (String filePath) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        FilteringHandler filteringHandler = new FilteringHandler();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(filePath, filteringHandler);

            OutputStream outputStream = new FileOutputStream(exists(filePath), append);

            XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(
                    new OutputStreamWriter(outputStream, CHARSET_NAME));
            out.writeStartElement("rule");

            outputStream.close();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write xml file
     * @param parsedData
     */
    public void writeXML (String parsedData) {
        try {
            OutputStream outputStream = new FileOutputStream(exists(filePath), append);

            XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(
                    new OutputStreamWriter(outputStream, CHARSET_NAME));

            out.writeStartElement(parsedData);

            outputStream.close();
            out.close();
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
        fileAddress = filePath;
        File file = new File(fileAddress);
        if (file.exists()){
            filePath = "src/main/resources/output/output_" + countName++ + ".txt";
        }
        return filePath;
    }
}
