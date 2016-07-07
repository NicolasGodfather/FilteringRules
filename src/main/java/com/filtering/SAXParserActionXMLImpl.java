package com.filtering;

import com.filtering.actions.ActionXML;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;

/**
 * Realization SAXParserActionXMLImpl
 * cause app will use XML files more than 100MB
 *
 * @author Nicolas Asinovich.
 */
public class SAXParserActionXMLImpl implements ActionXML {

    public static String filePath = "src/main/resources/output/output.xml";
    public static final String CHARSET_NAME = "UTF-8";
    public static boolean APPEND  = true;
    public static int count;

    @Override
    public void readXML (String addressFile) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        Handler handler = new Handler();

        try {
            javax.xml.parsers.SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(addressFile, handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeXML (String parsedData) {
        try {
            OutputStream outputStream = new FileOutputStream(exists(filePath), APPEND);

            XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(
                    new OutputStreamWriter(outputStream, CHARSET_NAME));

            out.writeStartElement(parsedData);

            outputStream.close();
            out.close();
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

    private static String exists(String fileAddress) throws FileNotFoundException {
        fileAddress = filePath;
        File file = new File(fileAddress);
        if (file.exists()){
            filePath = "src/main/resources/output/output_" + count++ + ".txt";
        }
        return filePath;
    }
}
