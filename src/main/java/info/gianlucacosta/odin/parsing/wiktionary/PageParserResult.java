package info.gianlucacosta.odin.parsing.wiktionary;

import info.gianlucacosta.balmung.lexicon.Lemma;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

class PageParserResult {
    private final int skippedLemmasCount;

    private final Set<Lemma> validLemmas;


    public PageParserResult(int skippedLemmasCount, Set<Lemma> validLemmas) {
        this.skippedLemmasCount = skippedLemmasCount;
        this.validLemmas = Collections.unmodifiableSet(validLemmas);
    }


    public int getSkippedLemmasCount() {
        return skippedLemmasCount;
    }


    public Set<Lemma> getValidLemmas() {
        return validLemmas;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageParserResult)) return false;
        PageParserResult that = (PageParserResult) o;
        return skippedLemmasCount == that.skippedLemmasCount &&
                Objects.equals(validLemmas, that.validLemmas);
    }


    @Override
    public int hashCode() {
        return Objects.hash(skippedLemmasCount, validLemmas);
    }


    @Override
    public String toString() {
        return "PageParserResult{" +
                "skippedLemmasCount=" + skippedLemmasCount +
                ", validLemmas=" + validLemmas +
                '}';
    }
}
