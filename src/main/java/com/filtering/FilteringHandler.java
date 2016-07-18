package com.filtering;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;

/**
 * Realization filtering
 * Use SAXParser cause app will use XML files more than 100MB
 *
 * @author Nicolas Asinovich.
 */
class FilteringHandler extends DefaultHandler {

    private XMLStreamWriter out;
    private OutputStream outputStream;
    private String filePathOut;

    private static int count = 0;

    /**
     * Constructor.
     * @param filePathOut
     */
    public FilteringHandler (String filePathOut) {
        this.filePathOut = filePathOut;
    }

    @Override
    public void startDocument () throws SAXException {
        try {
            outputStream = new FileOutputStream(filePathOut, true);
            out = XMLOutputFactory.newInstance().createXMLStreamWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            out.writeStartDocument("UTF-8", "1.0");
            out.writeCharacters("\n");

            out.writeDTD("<?xml-stylesheet type=\"text/xsl\" href=\"transform.xsl\"?>");
            out.writeCharacters("\n");

            out.writeStartElement("rules");
            out.writeCharacters("\n");

        } catch (XMLStreamException e) {
            System.out.println("An error has occurred in XML Stream.");
        } catch (IOException e) {
            System.out.println("An error has occurred in the program.");
        }
    }

    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("rule")) {
            count++;
            String name = attributes.getValue("name");
            Rule rule = new Rule(RuleType.valueOf(attributes.getValue("type").toUpperCase()),
                    Integer.parseInt(attributes.getValue("weight")));
            if (count % 2 == 0) {
            try {
                out.writeStartElement("rule");
                out.writeAttribute("name", name);
                out.writeAttribute("type", rule.getRuleType().toString().toLowerCase());
                out.writeAttribute("weight", String.valueOf(rule.getWeight()));
                out.writeEndElement();
                out.writeCharacters("\n");
            } catch (XMLStreamException e) {
                System.out.println("An error has occurred in XML Stream.");
            }
            }
        }
    }

    @Override
    public void endDocument () throws SAXException {
        try {
            out.writeEndElement();
            out.writeEndDocument();
            out.writeEndDocument();
        } catch (XMLStreamException e) {
            System.out.println("An error has occurred in XML Stream.");
        } finally {
            try {
                out.close();
                outputStream.close();
            } catch (XMLStreamException e) {
                System.out.println("An error has occurred in XML Stream.");
            } catch(IOException e){
                System.out.println("An error has occurred in the program.");
            }
        }
    }

    @Override
    public void fatalError (SAXParseException e) throws SAXException {
        try {
            out.close();
            outputStream.close();
        } catch(IOException e1){
            System.out.println("An error has occurred in the program.");
        } catch (XMLStreamException e1) {
            System.out.println("An error has occurred in XML Stream.");
        }
    }
}
