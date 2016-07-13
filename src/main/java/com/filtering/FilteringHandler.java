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
    private HashMap<String, Rule> dataRule = new HashMap<String, Rule>();

    public HashMap<String, Rule> getDataRule () {
        return dataRule;
    }

//    private ArrayList list = new ArrayList();
    private RuleType ruleType;

    /**
     * Read xml file
     * @param filePath
     */
    void parseXML (String filePath) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(filePath, this);

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
            HashMap<String, Rule> dataRule = this.getDataRule();

            out.writeStartDocument("UTF-8", "1.0");
            out.writeStartElement("rules");

            for (String s : dataRule.keySet()) {
                out.writeStartElement("rule");
                out.writeAttribute("name", s);
                out.writeAttribute("type", dataRule.get(s).getRuleType().toString().toLowerCase());
                out.writeAttribute("weight", String.valueOf(dataRule.get(s).getWeight()));
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
            ruleType = RuleType.valueOf(attributes.getValue("type").toUpperCase());
            dataRule.put(attributes.getValue("name"), new Rule(ruleType, Integer.parseInt(attributes.getValue("weight"))));
        }
    }
}
