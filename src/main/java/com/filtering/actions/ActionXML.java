package com.filtering.actions;

/**
 * Realization actions with XML file
 *
 * @author Nicolas Asinovich.
 */
public interface ActionXML {
    /**
     * Read xml file
     * @param addressFile
     */
    void readXML (String addressFile);

    /**
     * Write xml file
     * @param addressFile
     */
    void writeXML (String addressFile);
}
