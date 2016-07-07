package com.filtering;

/**
 * Realization start app
 *
 * @author Nicolas Asinovich.
 */
public class StartParse {

    public static void main (String[] args) {
        SAXParserActionXMLImpl startParser = new SAXParserActionXMLImpl();
        startParser.readXML("src/main/resources/input/rule.xml");
//        startParser.writeXML();
    }
}
