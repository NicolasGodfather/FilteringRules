package com.filtering.actions;

import org.xml.sax.Attributes;

/**
 * Realization actions with element in xml file
 *
 * @author Nicolas Asinovich.
 */
public interface ActionElement {
    /**
     * Create new Rule element
     * @param attributes
     */
    void addRule (Attributes attributes);

    /**
     * Replace one element to other if his names the same, cause should be unique in output
     * @param attributes
     */
    void updateRule (Attributes attributes);

    /**
     *
     * @param attributes
     */
    void getRule (Attributes attributes);
}
