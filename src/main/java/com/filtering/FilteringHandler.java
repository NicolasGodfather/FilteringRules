package com.filtering;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
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

    public FilteringHandler (String filePathOut) {
        this.filePathOut = filePathOut;
    }

    @Override
    public void startDocument () throws SAXException {
        try {
            outputStream = new FileOutputStream(filePathOut, true);
            out = XMLOutputFactory.newInstance().createXMLStreamWriter(
                    new OutputStreamWriter(outputStream, "UTF-8"));
            out.writeStartDocument("UTF-8", "1.0");
            out.writeDTD("<?xml-stylesheet type=\"text/xsl\" href=\"transform.xsl\"?>");
            out.writeStartElement("rules");
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
            String name = attributes.getValue("name");
            Rule rule = new Rule(RuleType.valueOf(attributes.getValue("type").toUpperCase()),
                    Integer.parseInt(attributes.getValue("weight")));
            try {
                out.writeStartElement("rule");
                out.writeAttribute("name", name);
                out.writeAttribute("type", rule.getRuleType().toString().toLowerCase());
                out.writeAttribute("weight", String.valueOf(rule.getWeight()));
                out.writeEndElement();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void endDocument () throws SAXException {
        try {
            out.writeEndElement();
            out.writeEndDocument();
            out.writeEndDocument();
            out.close();
            outputStream.close();
        } catch (XMLStreamException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
