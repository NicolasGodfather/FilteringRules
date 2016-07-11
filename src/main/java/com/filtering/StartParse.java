package com.filtering;

/**
 * Realization start app
 *
 * @author Nicolas Asinovich.
 */
public class StartParse {

    public static void main (String[] args) {
        XMLParser start = new XMLParser();
        start.readXML("src/main/resources/input/rule.xml");
//        start.writeXML();
    }
}
