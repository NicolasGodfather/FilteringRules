package com.filtering;

/**
 * Realization start app
 *
 * @author Nicolas Asinovich.
 */
public class StartApp {

    public static void main (String[] args) {
        XMLParser start = new XMLParser();
        start.parseXML("src/main/resources/input.xml");
    }
}
