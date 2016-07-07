package com.filtering.element;

/**
 * Realization Rule like entity
 *
 * @author Nicolas Asinovich.
 */
public class Rule {

    private RuleType ruleType;

    private int weight;

    public Rule (RuleType ruleType, int weight) {
        this.ruleType = ruleType;
        this.weight = weight;
    }

    public RuleType getRuleType () {
        return ruleType;
    }

    public void setRuleType (RuleType ruleType) {
        this.ruleType = ruleType;
    }

    public int getWeight () {
        return weight;
    }

    public void setWeight (int weight) {
        if (weight > 0) {
            this.weight = weight;
        } else {
            throw new IllegalArgumentException("The weight should be a positive integer!");
        }
    }
}
