package com.filtering;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Realization Handler event
 *
 * @author Nicolas Asinovich.
 */
public class Handler extends DefaultHandler{

    private FilteringActionElementImpl filtering = new FilteringActionElementImpl();

    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("rule")) {
            filtering.addRule(attributes);
        }
    }

}
