package org.jsoup.safety;

import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * This class selectively removes tags based on the rules provided
 */
public class Remover {

    private BlackList blackList;

    public Remover(BlackList blackList) {
        Validate.notNull(blackList);
        this.blackList = blackList;
    }
    
    public Document clean(Document inputDocument) {
        Validate.notNull(inputDocument);
        Document cleanedDocument = Document.createShell(inputDocument.baseUri());
        copyCleanElements(cleanedDocument.body(), inputDocument.body());
        return cleanedDocument;
    }

    private void copyCleanElements(Element element, Element element1) {

    }


}
