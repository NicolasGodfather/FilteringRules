package com.filtering;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.TreeMap;

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
    private TreeMap<String, Rule> dataRule = new TreeMap<>();

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
            // here line will be convert output-file
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
            String name = attributes.getValue("name");
            Rule ruleName = dataRule.get(attributes.getValue("name"));
            RuleType type = RuleType.valueOf(attributes.getValue("type").toUpperCase());
            int weight = Integer.parseInt(attributes.getValue("weight"));

            if (!dataRule.containsKey(attributes.getValue("name"))) {
                dataRule.put(name, new Rule(type, weight));
            } else {
            /* If elements have some types, we will compare them by weight. */
                if (type.getRuleTypePrecedence() == ruleName.getRuleType().getRuleTypePrecedence()) {
                    if (weight > ruleName.getWeight()) {
                        ruleName.setWeight(weight);
                    }
                }
                else if (type.getRuleTypePrecedence() < ruleName.getRuleType().getRuleTypePrecedence()){
            /* If elements have some keys, we will compare them by type. */
                    ruleName.setRuleType(type);
                }
            }
            try {
                out.writeStartElement("rule");
                out.writeAttribute("name", name);
                out.writeAttribute("type", dataRule.get(name).getRuleType().toString().toLowerCase());
                out.writeAttribute("weight", String.valueOf(dataRule.get(name).getWeight()));
                out.writeEndElement();
                out.writeCharacters("\n");
            } catch (XMLStreamException e) {
                System.out.println("An error has occurred in XML Stream.");
            }
        }
    }

    @Override
    public void endDocument () throws SAXException {
        try {
            out.writeEndElement();
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
