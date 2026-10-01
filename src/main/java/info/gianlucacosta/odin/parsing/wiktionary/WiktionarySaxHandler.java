package info.gianlucacosta.odin.parsing.wiktionary;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

class WiktionarySaxHandler extends DefaultHandler {
    private static final String TEXT_ELEMENT = "text";

    private final ExecutorService pageParsingExecutorService = Executors.newCachedThreadPool();

    private final Consumer<PageParserResult> onPageParsed;
    private final StringBuilder textBuilder = new StringBuilder();

    private boolean readingText;


    public WiktionarySaxHandler(Consumer<PageParserResult> onPageParsed) {
        this.onPageParsed = onPageParsed;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (TEXT_ELEMENT.equals(qName)) {
            readingText = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case TEXT_ELEMENT:
                parsePageText(textBuilder.toString());

                textBuilder.setLength(0);

                readingText = false;

                break;
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (readingText) {
            textBuilder.append(ch, start, length);
        }
    }

    private void parsePageText(String pageText) {
        pageParsingExecutorService.submit(() -> {
            PageParser pageParser = new PageParser(pageText);
            PageParserResult pageParserResult = pageParser.parse();

            onPageParsed.accept(pageParserResult);
        });
    }

    public void waitForTermination() {
        pageParsingExecutorService.shutdown();

        while (true) {
            try {
                if (pageParsingExecutorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS)) {
                    return;
                }
            } catch (InterruptedException e) {
                //Just do nothing
            }
        }
    }
}
