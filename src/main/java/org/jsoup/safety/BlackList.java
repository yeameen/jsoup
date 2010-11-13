package org.jsoup.safety;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BlackList implements Iterable {

    private List<SelectiveCleanerRule> rules;

    public BlackList() {
        rules = new LinkedList<SelectiveCleanerRule>();
    }

    public Iterator iterator() {
        return rules.iterator();
    }
}
