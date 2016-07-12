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

    private static String filePathIn = "src/main/resources/input/input.xml";
    private static String filePathOut = "src/main/resources/output/output.xml";
    private static final String CHARSET_NAME = "UTF-8";
    private static boolean append = true;
    private static int countName;
    private FilteringHandler filteringHandler = new FilteringHandler();

    /**
     * Read xml file
     * @param filePath
     */
    private void readXML (String filePath) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

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
            OutputStream outputStream = new FileOutputStream(filePathOut, append);

            XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(
                    new OutputStreamWriter(outputStream, CHARSET_NAME));

            HashMap<String, Rule> data = filteringHandler.getDataRule();

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

//    /**
//     * check existing output file
//     * @param fileAddress
//     * @return
//     * @throws FileNotFoundException
//     */
//    private static String exists(String fileAddress) throws FileNotFoundException {
//        File file = new File(fileAddress);
//        if (file.exists()){
//            fileAddress = "src/main/resources/output/output_" + countName++ + ".xml";
//        }
//        return fileAddress;
//    }

    @Override
    public void run () {
        readXML(filePathIn);
        writeXML();
    }
}
