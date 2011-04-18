/**
 * @author Chowdhury Ashabul Yeameen
 */
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

    private SelectiveCleanerRule(String tagName, AttributeMatchingRule attributeMatchingRule, boolean retainTag) {
        this.tagName = tagName;
        this.attributeMatchingRule = attributeMatchingRule;
        this.retainTag = retainTag;
        this.condition = SelectiveMatchingCondition.BY_TAGNAME_AND_ATTRIBUTE_RULE;
    }

    private SelectiveCleanerRule(AttributeMatchingRule attributeMatchingRule, boolean retainTag) {
        this.tagName = null;
        this.attributeMatchingRule = attributeMatchingRule;
        this.retainTag = retainTag;
        this.condition = SelectiveMatchingCondition.BY_ATTRIBUTE_RULE;
    }

    public static SelectiveCleanerRule matchByTagName(String tagName, boolean retainTag) {
        return new SelectiveCleanerRule(tagName, retainTag);
    }

    /**
     * TODO: not yet called
     * @param attributeMatchingRule
     * @param retainTag
     * @return
     */
    public static SelectiveCleanerRule matchByAttributeRule(AttributeMatchingRule attributeMatchingRule, boolean retainTag) {
        return new SelectiveCleanerRule(attributeMatchingRule, retainTag);
    }

    public static SelectiveCleanerRule matchByTagNameAndAttributeRule(String tagName,
                                                               AttributeMatchingRule rule, boolean retainTag) {
        return new SelectiveCleanerRule(tagName, rule, retainTag);
    }

    public String getTagName() {
        return tagName;
    }

    public AttributeMatchingRule getAttributeMatchingRule() {
        return attributeMatchingRule;
    }

    public SelectiveMatchingCondition getCondition() {
        return condition;
    }

    public boolean isRetainTag() {
        return retainTag;
    }
}
