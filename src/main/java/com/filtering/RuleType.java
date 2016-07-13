package com.filtering;

/**
 * Realization types of Rule, each type has it's own Precedence(number) that necessary in comparing
 *
 * @author Nicolas Asinovich.
 */
public enum RuleType {
    CHILD(1), SUB(2), ROOT(3);

    private final int ruleTypePrecedence;

    RuleType (int ruleTypePrecedence) {
        this.ruleTypePrecedence = ruleTypePrecedence;
    }

    public int getRuleTypePrecedence () {
        return ruleTypePrecedence;
    }
}
