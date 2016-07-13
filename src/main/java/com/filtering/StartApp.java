package com.filtering;

/**
 * Realization start app
 *
 * @author Nicolas Asinovich.
 */
public class StartApp {

    public static void main (String[] args) {

        FilteringHandler start = new FilteringHandler();
        start.parseXML("src/main/resources/input.xml");
    }
}
