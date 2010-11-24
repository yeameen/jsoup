package org.jsoup.safety;

import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.nodes.Document;
import org.junit.Test;

import static org.junit.Assert.*;

public class RemoverTest {

    @Test
    public void removeAllSpecificTag() throws Exception {
        String inputHtml = "<p>what this <span>bad <i>text</i></span> get <div>into</div> <span>this</span> place?";
        String expectedHtml = "<p>what this bad <i>text</i> get </p><div>into</div> this place?";
        Document document = Jsoup.parse(inputHtml);

        BlackList blackList = new BlackList();
        blackList.addRule(SelectiveCleanerRule.matchByTagName("span", true));
        Remover remover = new Remover(blackList);
        document = remover.clean(document);

        assertEquals(expectedHtml, TextUtil.stripNewlines(document.body().html()));

    }

    @Test
    public void removeSpecificTagHavingSpecificAttribute() throws Exception {
        String inputHtml = "<p>what this <span>bad <i>text</i></span> get <div>into</div> <span seid='123'>this</span> place?";
        String expectedOutput = "<p>what this <span>bad <i>text</i></span> get </p><div>into</div> this place?";

        Document document = Jsoup.parse(inputHtml);

        BlackList blackList = new BlackList();
        blackList.addRule(SelectiveCleanerRule.matchByTagNameAndAttributeRule("span", AttributeMatchingRule.hasAttribute("seid"), true));
        Remover remover = new Remover(blackList);
        document = remover.clean(document);
        assertEquals(expectedOutput, TextUtil.stripNewlines(document.body().html()));
    }
}
