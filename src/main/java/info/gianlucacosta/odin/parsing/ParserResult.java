package info.gianlucacosta.odin.parsing;

public interface ParserResult {
    long getSkippedLemmasCount();

    long getUnsavedLemmasCount();

    long getSavedLemmasCount();


    default long getTotalLemmasCount() {
        return getSkippedLemmasCount() + getUnsavedLemmasCount() + getSavedLemmasCount();
    }
}
