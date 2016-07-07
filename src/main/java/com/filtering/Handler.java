package com.filtering;

import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

/**
 * Realization Handler
 *
 * @author Nicolas Asinovich.
 */
class Handler extends DefaultHandler {

    private HashMap<Integer, Rule> data = new HashMap<Integer, Rule>();


}
