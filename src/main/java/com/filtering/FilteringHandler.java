package com.filtering;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

/**
 * Realization filtering
 *
 * @author Nicolas Asinovich.
 */
class FilteringHandler extends DefaultHandler {
    /**
     * The HashMap will store our rules, where attribute of rule the name is unique.
     */
    private HashMap<String, Rule> dataRule = new HashMap<String, Rule>();
    private RuleType ruleType;

    HashMap<String, Rule> getDataRule () {
        return dataRule;
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
