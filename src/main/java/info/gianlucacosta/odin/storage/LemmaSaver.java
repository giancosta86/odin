package info.gianlucacosta.odin.storage;

import info.gianlucacosta.balmung.lexicon.Lemma;

@FunctionalInterface
public interface LemmaSaver {
    boolean save(Lemma lemma);
}
