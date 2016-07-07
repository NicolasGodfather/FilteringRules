package com.filtering;

import com.filtering.actions.ActionXML;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Realization SAXParserActionXMLImpl
 * cause app will use XML files more than 100MB
 *
 * @author Nicolas Asinovich.
 */
public class SAXParserActionXMLImpl implements ActionXML {

    @Override
    public void readXML (String addressFile) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        Handler handler = new Handler();

        try {
            javax.xml.parsers.SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(addressFile, handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeXML (String addressFile) {

    }
}
