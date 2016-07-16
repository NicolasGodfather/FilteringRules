package com.filtering;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Realization start application
 *
 * @author Nicolas Asinovich.
 */
public class StartApp {
    public static void main (String[] args) throws Exception{

        try {
            if (args.length == 0) {
                throw new ArrayIndexOutOfBoundsException("Please, select full path to your XML file in argument.");
            }
            FilteringHandler handler = new FilteringHandler(args[1]); //E:\JAVA\TECHTASK\FilteringRules\src\main\resources\output.xml
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(args[0], handler); // my args[0] = E:\JAVA\TECHTASK\FilteringRules\src\main\resources\input.xml
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
