package org.jsoup.safety;

/**
 * Rule for matching tag, with or without attribute options.
 */
public class SelectiveCleanerRule {

    private String tagName;
    private AttributeMatchingRule attributeMatchingRule;
    private SelectiveMatchingCondition condition;
    private boolean retainTag;

    /**
     * Default constructor. Should not be called
     */
    private SelectiveCleanerRule() {
        throw new UnsupportedOperationException();
    }

    private SelectiveCleanerRule(String tagName, boolean retainTag) {
        this.tagName = tagName;
        this.retainTag = retainTag;
        this.condition = SelectiveMatchingCondition.BY_TAGNAME;
    }

    public SelectiveCleanerRule(String tagName, AttributeMatchingRule attributeMatchingRule, boolean retainTag) {
        this.tagName = tagName;
        this.attributeMatchingRule = attributeMatchingRule;
        this.retainTag = retainTag;
        this.condition = SelectiveMatchingCondition.BY_TAGNAME_AND_ATTRIBUTE_RULE;
    }

    public SelectiveCleanerRule(AttributeMatchingRule attributeMatchingRule, boolean retainTag) {
        this.tagName = null;
        this.attributeMatchingRule = attributeMatchingRule;
        this.retainTag = retainTag;
        this.condition = SelectiveMatchingCondition.BY_ATTRIBUTE_RULE;
    }

    public SelectiveCleanerRule matchByTagName(String tagName, boolean retainTag) {
        return new SelectiveCleanerRule(tagName, retainTag);
    }

    public SelectiveCleanerRule matchByAttributeRule(AttributeMatchingRule attributeMatchingRule, boolean retainTag) {
        return new SelectiveCleanerRule(attributeMatchingRule, retainTag);
    }

    public SelectiveCleanerRule matchByTagNameAndAttributeRule(String tagName,
                                                               AttributeMatchingRule rule, boolean retainTag) {
        return new SelectiveCleanerRule(tagName, rule, retainTag);
    }
}
