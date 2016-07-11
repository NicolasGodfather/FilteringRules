package com.filtering;

import com.filtering.element.Rule;
import com.filtering.element.RuleType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

/**
 * Realization filtering
 *
 * @author Nicolas Asinovich.
 */
public class FilteringHandler extends DefaultHandler {
    /**
     * The HashMap will store our rules, where attribute of rule the name is unique.
     */
    private HashMap<String, Rule> dataRule = new HashMap<String, Rule>();
    private RuleType ruleType;

    public HashMap<String, Rule> getDataRule () {
        return dataRule;
    }

    public void setDataRule (HashMap<String, Rule> dataRule) {
        this.dataRule = dataRule;
    }

    public RuleType getRuleType () {
        return ruleType;
    }

    public void setRuleType (RuleType ruleType) {
        this.ruleType = ruleType;
    }

    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("rule")) {
            addRule(attributes);
        }
    }

    /**
     * Create new Rule element
     * @param attributes
     */
    public void addRule (Attributes attributes) {
        dataRule.put(attributes.getValue("name"), new Rule(ruleType, Integer.parseInt("weight")));
    }

    /**
     *
     * @param attributes
     */
    public void getRule (Attributes attributes) {

    }

}
