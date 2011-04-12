/**
 * @author Chowdhury Ashabul Yeameen
 */
package org.jsoup.safety;

/**
 * Attribute matching rule. The attribute may match just by name, exact name-value pair,
 * or name-value condition
 */
public class AttributeMatchingRule {

    private AttributeMatchingCondition condition;
    private String name;
    private String value;

    /**
     * Default constructor without any parameter. Should not be called
     * because rule cannot be created without any attribute name
     */
    private AttributeMatchingRule() {
        throw new UnsupportedOperationException();
    }

    /**
     * @param name The name of the attribute
     */
    private AttributeMatchingRule(String name) {
        this(name, null);
    }

    /**
     *
     * @param name name of the attribute
     * @param value value of the attribute
     */
    private AttributeMatchingRule(String name, String value) {
        this(name, value, AttributeMatchingCondition.MATCHES);
    }

    /**
     *
     * @param name name of the attribute
     * @param value value of the attribute
     * @param condition whether the attribute-value matches exactly, starts-with or contains the value
     */
    private AttributeMatchingRule(String name, String value, AttributeMatchingCondition condition) {
        this.name = name;
        this.value = value;
        this.condition = condition;
    }

    /**
     * Rule that specifies if the certain attribute exists or not
     * @param name
     * @return
     */
    public static AttributeMatchingRule hasAttribute(String name) {
        return new AttributeMatchingRule(name, null, AttributeMatchingCondition.HAS);
    }

    /**
     * Rule that specifies if the attribute-value matches exactly
     *
     * @param name attribute name
     * @param value attribute value
     * @return
     */
    public static AttributeMatchingRule attributeValueMatches(String name, String value) {
        return new AttributeMatchingRule(name, value);
    }

    /**
     * Rule that specifies if the attribute value starts with the value specified
     * @param name attribute name
     * @param value attribute value
     * @return
     */
    public static AttributeMatchingRule attributeValueStartsWith(String name, String value) {
        return new AttributeMatchingRule(name, value, AttributeMatchingCondition.STARTS_WITH);
    }

    /**
     * Rule that specifies if the attribute-value contains the value specified
     *
     * @param name attribute name
     * @param value attribute value
     * @return
     */
    public static AttributeMatchingRule attributeValueContains(String name, String value) {
        return new AttributeMatchingRule(name, value, AttributeMatchingCondition.CONTAINS);
    }

    /**
     * Rule that specifies attribute-value ends with the value
     *
     * @param name attribute name
     * @param value attribute value
     * @return
     */
    public static AttributeMatchingRule attributeValueEndsWith(String name, String value) {
        return new AttributeMatchingRule(name, value, AttributeMatchingCondition.ENDS_WITH);
    }

    /**
     * @return the matching condition
     */
    public AttributeMatchingCondition getCondition() {
        return condition;
    }

    /**
     * @return name of the attribute
     */
    public String getName() {
        return name;
    }

    /**
     * @return value of the attribute
     */
    public String getValue() {
        return value;
    }
}
