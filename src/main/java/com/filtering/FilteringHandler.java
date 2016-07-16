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
import java.util.ArrayList;

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

    private ArrayList<Rule> list = new ArrayList();
    private String name;

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public ArrayList<Rule> getList () {
        return list;
    }

    public void setList (ArrayList<Rule> list) {
        this.list = list;
    }

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
//            SortedMap<String, Rule> dataRule = this.getDataRule();
            ArrayList<Rule> rules = this.getList();
            String name = this.getName();
            out.writeStartDocument("UTF-8", "1.0");
            out.writeStartElement("rules");

            /*for (String s : dataRule.keySet()) {
                out.writeStartElement("rule");
                out.writeAttribute("name", s);
                out.writeAttribute("type", dataRule.get(s).getRuleType().toString().toLowerCase());
                out.writeAttribute("weight", String.valueOf(dataRule.get(s).getWeight()));
                out.writeEndElement();
            }*/

            for (int i = 0; i < rules.size(); i++) {
                if (i % 2 == 0) {
                out.writeStartElement("rule");
                out.writeAttribute("name", name);
                out.writeAttribute("type", rules.get(i).getRuleType().toString().toLowerCase());
                out.writeAttribute("weight", String.valueOf(rules.get(i).getWeight()));
                out.writeEndElement();
                }
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
            name = attributes.getValue("name"); // TODO: записывает везде одинаковое имя - b
            ruleType = RuleType.valueOf(attributes.getValue("type").toUpperCase());
            list.add(new Rule(ruleType, Integer.parseInt(attributes.getValue("weight"))));
        }
    }

    /*@Override
    public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("rule")) {
            ruleType = RuleType.valueOf(attributes.getValue("type").toUpperCase());
            dataRule.put(attributes.getValue("name"), new Rule(ruleType, Integer.parseInt(attributes.getValue("weight"))));
        }
    }*/
}
