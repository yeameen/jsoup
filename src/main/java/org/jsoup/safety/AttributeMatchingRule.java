package org.jsoup.safety;

public class AttributeMatchingRule {

    private AttributeMatchingCondition condition;
    private String name;
    private String value;

    private AttributeMatchingRule() {
        throw new UnsupportedOperationException();
    }

    private AttributeMatchingRule(String name) {
        this(name, null);
    }

    private AttributeMatchingRule(String name, String value) {
        this(name, value, AttributeMatchingCondition.MATCHES);
    }

    private AttributeMatchingRule(String name, String value, AttributeMatchingCondition condition) {
        this.name = name;
        this.value = value;
        this.condition = condition;
    }



    public static AttributeMatchingRule hasAttribute(String name) {
        return new AttributeMatchingRule(name, null, AttributeMatchingCondition.HAS);
    }

    public static AttributeMatchingRule attributeValueMatches(String name, String value) {
        return new AttributeMatchingRule(name, value);
    }

    public static AttributeMatchingRule attributeValueStartsWith(String name, String value) {
        return new AttributeMatchingRule(name, value, AttributeMatchingCondition.STARTS_WITH);
    }

    public static AttributeMatchingRule attributeValueContains(String name, String value) {
        return new AttributeMatchingRule(name, value, AttributeMatchingCondition.CONTAINS);
    }

    public static AttributeMatchingRule attributeValueEndsWith(String name, String value) {
        return new AttributeMatchingRule(name, value, AttributeMatchingCondition.ENDS_WITH);
    }

    public AttributeMatchingCondition getCondition() {
        return condition;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
