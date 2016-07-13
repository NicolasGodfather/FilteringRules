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

    @Override
    public int hashCode () {
        return getWeight() * getRuleType().hashCode();
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rule rule = (Rule) obj;
        return !(this.getRuleType() != rule.getRuleType() &&
                this.getWeight() != rule.getWeight());
    }

    @Override
    public String toString () {
        return ruleType + " " + weight;
    }
}
