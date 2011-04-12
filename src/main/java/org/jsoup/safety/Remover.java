/**
 * @author: Chowdhury Ashabul Yeameen
 */
package org.jsoup.safety;

import org.jsoup.helper.Validate;
import org.jsoup.nodes.*;
import org.jsoup.parser.Tag;

import java.util.List;

/**
 * The blacklist based HTML tag remover. This class selectively removes tags based on the rules provided.
 *
 *
 */
public class Remover {

    private BlackList blackList;

    /**
     * Creates a new remover, that sanitized the document from the rules supplied
     * @param blackList The black-list of rules to remove
     */
    public Remover(BlackList blackList) {
        Validate.notNull(blackList);
        this.blackList = blackList;
    }

    /**
     * Clean the document off remover rule and return the cleaned document
     *
     * @param inputDocument The dirty document
     * @return  The cleaned Document
     */
    public Document clean(Document inputDocument) {
        Validate.notNull(inputDocument);
        Document cleanedDocument = Document.createShell(inputDocument.baseUri());
        copyCleanElements(cleanedDocument.body(), inputDocument.body());
        return cleanedDocument;
    }

    /**
     * Check if the element mathches with the rule
     * @param el
     * @param rule
     * @return
     */
    private boolean matchesAttributeCondition(Element el, AttributeMatchingRule rule) {

        String name = rule.getName();
        String value = rule.getValue();

        switch(rule.getCondition()) {
            case HAS:
                return el.hasAttr(name);
            case MATCHES:
                return el.hasAttr(name) && el.attr(name).equals(value);
            case STARTS_WITH:
                return el.hasAttr(name) && el.attr(name).startsWith(value);
            case ENDS_WITH:
                return el.hasAttr(name) && el.attr(name).endsWith(value);
            case CONTAINS:
                return el.hasAttr(name) && el.attr(name).indexOf(value) != -1;
        }
        return false;
    }

    /**
     * Checks if the tagname of the element matches with the tagname given in the rule
     * The first consideration of matching the rule is to match the tagname. Then comes
     * others.
     *
     * @param el the element under consideration
     * @param rule The rule from black-list
     * @return true if the element's tagname matches with the tagname of the rule
     */
    private boolean matchesTagName(Element el, SelectiveCleanerRule rule) {
        return el.tagName().equals(rule.getTagName());
    }

    /**
     * Checks if the rule matches with the element under consideration
     *
     * @param el the element under consideration
     * @param rule The rule from black-list
     * @return true if the element matches with the rule
     */
    private boolean matchesRule(Element el, SelectiveCleanerRule rule) {

        SelectiveMatchingCondition condition = rule.getCondition();
        switch(rule.getCondition()) {
            case BY_TAGNAME:
                return matchesTagName(el, rule);
            case BY_ATTRIBUTE_RULE:
                return matchesAttributeCondition(el, rule.getAttributeMatchingRule());
            case BY_TAGNAME_AND_ATTRIBUTE_RULE:
                return matchesTagName(el, rule) && matchesAttributeCondition(el, rule.getAttributeMatchingRule());
        }
        return false;
    }

    /**
     * Copy only the elements that don't match with the rules
     *
     * @param dest The destination element or the tree
     * @param src The source element or the tree
     * @return number of elements copied
     */
    private int copyCleanElements(Element dest, Element src) {
         List<Node> childNodes = src.childNodes();

        int discardCount = 0;
        for(Node sourceChild : childNodes) {
            if(sourceChild instanceof Element) {
                Element sourceElement = (Element) sourceChild;
                boolean isFound = false;
                // now check all the conditions
                for(SelectiveCleanerRule rule : blackList) {
                    if(matchesRule(sourceElement, rule)) {
                        discardCount += copyCleanElements(dest, sourceElement);
                        isFound = true;
                        break;
                    }
                }
                if(!isFound) {
                    Element destElement = new Element(Tag.valueOf(sourceElement.tagName()),
                            sourceElement.baseUri(), sourceElement.attributes());
                    dest.appendChild(destElement);
                    discardCount += copyCleanElements(destElement, sourceElement);
                }
            } else if (sourceChild instanceof TextNode) {
                TextNode sourceText = (TextNode) sourceChild;
                TextNode destText = new TextNode(sourceText.getWholeText(), sourceText.baseUri());
                dest.appendChild(destText);
            }
        }
        return discardCount;
    }
}
