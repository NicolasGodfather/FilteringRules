package com.filtering;

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
        if (ruleType.getRuleTypePrecedence() <  this.ruleType.getRuleTypePrecedence()){
            this.ruleType = ruleType;
        }
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

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        if (weight != rule.weight) return false;
        return ruleType == rule.ruleType;

    }

    @Override
    public int hashCode () {
        int result = ruleType != null ? ruleType.hashCode() : 0;
        result = 31 * result + weight;
        return result;
    }
}
