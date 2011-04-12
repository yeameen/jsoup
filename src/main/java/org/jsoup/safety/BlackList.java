/**
 * @author Chowdhury Ashabul Yeameen
 */
package org.jsoup.safety;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The blacklist of the list of cleaning rule
 */
public class BlackList implements Iterable<SelectiveCleanerRule> {

    private List<SelectiveCleanerRule> rules;

    /**
     *
     */
    public BlackList() {
        rules = new LinkedList<SelectiveCleanerRule>();
    }

    public Iterator<SelectiveCleanerRule> iterator() {
        return rules.iterator();
    }

    public void addRule(SelectiveCleanerRule rule) {
        rules.add(rule);
    }
}
