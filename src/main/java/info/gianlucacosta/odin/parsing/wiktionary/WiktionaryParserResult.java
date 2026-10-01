package info.gianlucacosta.odin.parsing.wiktionary;

import info.gianlucacosta.odin.parsing.ParserResult;

import java.util.Objects;

public class WiktionaryParserResult implements ParserResult {
    private final long skippedLemmasCount;
    private final long unsavedLemmasCount;
    private final long savedLemmasCount;


    public WiktionaryParserResult(long skippedLemmasCount, long unsavedLemmasCount, long savedLemmasCount) {
        this.skippedLemmasCount = skippedLemmasCount;
        this.unsavedLemmasCount = unsavedLemmasCount;
        this.savedLemmasCount = savedLemmasCount;
    }


    @Override
    public long getSkippedLemmasCount() {
        return skippedLemmasCount;
    }

    @Override
    public long getUnsavedLemmasCount() {
        return unsavedLemmasCount;
    }

    @Override
    public long getSavedLemmasCount() {
        return savedLemmasCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WiktionaryParserResult)) return false;
        WiktionaryParserResult that = (WiktionaryParserResult) o;
        return skippedLemmasCount == that.skippedLemmasCount &&
                unsavedLemmasCount == that.unsavedLemmasCount &&
                savedLemmasCount == that.savedLemmasCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(skippedLemmasCount, unsavedLemmasCount, savedLemmasCount);
    }

    @Override
    public String toString() {
        return "WiktionaryParserResult{" +
                "skippedLemmasCount=" + skippedLemmasCount +
                ", unsavedLemmasCount=" + unsavedLemmasCount +
                ", savedLemmasCount=" + savedLemmasCount +
                '}';
    }
}
