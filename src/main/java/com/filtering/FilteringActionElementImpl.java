package com.filtering;

import com.filtering.element.Rule;
import com.filtering.element.RuleType;
import com.filtering.actions.ActionElement;
import org.xml.sax.Attributes;

import java.util.HashMap;

/**
 * Realization filtering
 *
 * @author Nicolas Asinovich.
 */
public class FilteringActionElementImpl implements ActionElement {
    /**
     * The field will store our rules
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


    public void addRule (Attributes attributes) {
        dataRule.put(attributes.getValue("name"), new Rule(ruleType, Integer.parseInt("weight")));
    }

    @Override
    public void updateRule (Attributes attributes) {

    }

    @Override
    public void getRule (Attributes attributes) {

    }

}
