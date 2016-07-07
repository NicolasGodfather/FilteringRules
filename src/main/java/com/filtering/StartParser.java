package com.filtering;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Realization SAXParser
 * cause app will use XML files more than 100MB
 *
 * @author Nicolas Asinovich.
 */
public class StartParser {

    public void readXML(String addressFile) {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        Handler handler = new Handler();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(addressFile, handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
