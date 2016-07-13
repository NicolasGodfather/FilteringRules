package com.filtering;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.HashMap;

/**
 * Realization filtering
 * Use SAXParser cause app will use XML files more than 100MB
 *
 * @author Nicolas Asinovich.
 */
class FilteringHandler extends DefaultHandler {
    /**
     * The HashMap will store our rules, where attribute of rule the name is unique.
     */
    private HashMap<String, Rule> dataRule;
    private RuleType ruleType;
    private FilteringHandler handler = new FilteringHandler();

    private HashMap<String, Rule> getDataRule () {
        return dataRule;
    }

    /**
     * Read xml file
     * @param filePath
     */
    void parseXML (String filePath) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(filePath, handler);

            writeXML();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write xml file
     */
    private void writeXML () {
        try {
            String filePathOut = "src/main/resources/output.xml";
            OutputStream outputStream = new FileOutputStream((filePathOut), true);
            XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(
                    new OutputStreamWriter(outputStream, "UTF-8"));
//перенести в фильтр!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            dataRule = handler.getDataRule();
// TODO: maybe here is mistake
            out.writeStartDocument();
            out.writeStartElement("rules");

//            for (Map.Entry<String, Rule> entry : dataRule.entrySet()) {
//                out.writeStartElement("rules");
//                out.writeStartElement(entry.getKey() + " " + entry.getValue());
//                out.writeEndElement();
//                out.writeEndElement();
//            }
            for (String s : dataRule.keySet()) {
                out.writeStartElement("rules");
                out.writeStartElement(dataRule.get(s).toString());
                out.writeEndElement();
                out.writeEndElement();
            }
            out.writeEndElement();
            out.writeEndDocument();

            out.close();
            outputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("rule")) {
            getFilteringRule(attributes);
        }
    }

    /**
     * Create new Rule element
     * @param attributes
     */
    private void addRule (Attributes attributes) {
        dataRule.put(attributes.getValue("name"), new Rule(ruleType, Integer.parseInt(attributes.getValue("weight"))));
    }

    /**
     * Replace by new Rule
     * @param attributes
     */
    private void replaceRule (Attributes attributes) {
//        dataRule.get(attributes.getValue("name").replace(attributes.getValue("name"),
//                (CharSequence) new Rule(ruleType, Integer.parseInt(attributes.getValue("weight")))));

        dataRule.put(attributes.getValue("name"), dataRule.get(attributes.getValue("name")));
    }

    /**
     * Made logic filtering
     * @param attributes
     */
    // TODO: 13.07.2016 CHECK THIS
    public void getFilteringRule (Attributes attributes) {
        ruleType = RuleType.valueOf(attributes.getValue("type").toUpperCase());

        /* If element hasn't key 'name', we will add it. */
        if (!dataRule.containsKey(attributes.getValue("name"))) {
            addRule(attributes);
        }
        else {
            /* If elements have some types, we will compare them by weight. */
            if (ruleType.getRuleTypePrecedence() == dataRule.get(attributes.getValue("name"))
                    .getRuleType().getRuleTypePrecedence()) {
                if (Integer.parseInt(attributes.getValue("weight")) > dataRule.get(attributes.getValue("name"))
                        .getWeight()) {
                    replaceRule(attributes);

                }
            }
            else if (ruleType.getRuleTypePrecedence() < dataRule.get(attributes.getValue("name"))
                    .getRuleType().getRuleTypePrecedence()){
            /* If elements have some keys, we will compare them by type. */
                    replaceRule(attributes);
            }
        }
    }
}
