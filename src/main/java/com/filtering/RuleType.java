package com.filtering;

/**
 * Realization types of Rule
 *
 * @author Nicolas Asinovich.
 */
enum RuleType {
    CHILD(1), SUB(2), ROOT(3);

    private final int ruleTypePrecedence;

    RuleType (int ruleTypePrecedence) {
        this.ruleTypePrecedence = ruleTypePrecedence;
    }

    public int getRuleTypePrecedence () {
        return ruleTypePrecedence;
    }
}
