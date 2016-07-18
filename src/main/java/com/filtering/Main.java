package com.filtering;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Realization start application
 *
 * @author Nicolas Asinovich.
 */
public class Main {
    public static void main (String[] args) throws Exception{
        try {
            if (args.length == 0) {
                System.out.println("Please, select full path to your XML file in arguments.");
                System.exit(0);
            }
            FilteringHandler handler = new FilteringHandler(args[1]); // my args[0] = //E:/fileRules/output.xml
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            File file = new File(args[0]);
            if (file.exists()) {
                saxParser.parse(args[0], handler); // my args[0] = E:/fileRules/input.xml
                System.out.println("File existed and has been tested =)");
            } else {
                System.out.println("File not found! =(");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There are an error of the program.");
        }
        System.out.println("Program successfully completed!");
    }
}
