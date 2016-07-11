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
        dataRule.put(attributes.getValue("name"), new Rule(ruleType, Integer.parseInt(attributes.getValue("weight"))));
    }

    /**
     * Replace by new Rule
     * @param attributes
     */
    public void replaceRule (Attributes attributes) {
        dataRule.get(attributes.getValue("name").replace(attributes.getValue("name"),
                (CharSequence) new Rule(ruleType, Integer.parseInt(attributes.getValue("weight")))));
    }

    /**
     * Made logic filtering
     * @param attributes
     */
    public void getFilteringRule (Attributes attributes) {
        ruleType = RuleType.valueOf(attributes.getValue("type").toUpperCase());

        /* If element hasn't key 'name', we will add it. */
        if (!dataRule.containsKey(attributes.getValue("name"))) {
            addRule(attributes);
        } else {
            /* If elements have some keys, we will compare them by type. */
            if (ruleType.getRuleTypePrecedence() < dataRule.get(attributes.getValue("name"))
                    .getRuleType().getRuleTypePrecedence()) {
                replaceRule(attributes);
            }
            /* If elements have some types, we will compare them by weight. */
            else if (ruleType.getRuleTypePrecedence() == dataRule.get(attributes.getValue("name"))
                    .getRuleType().getRuleTypePrecedence()) {
                if (Integer.parseInt(attributes.getValue("weight")) > dataRule.get(attributes.getValue("name"))
                        .getWeight()) {
                    replaceRule(attributes);
                }
            }
        }
    }
}
