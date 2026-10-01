package info.gianlucacosta.odin.storage;

import info.gianlucacosta.balmung.lexicon.Adjective;
import info.gianlucacosta.balmung.lexicon.Lemma;
import info.gianlucacosta.balmung.lexicon.Noun;
import info.gianlucacosta.balmung.lexicon.Verb;

import java.util.stream.Stream;

public interface Lexicon extends LemmaSaver {
    Stream<Lemma> findLemmas();

    Stream<Noun> findNouns();

    Stream<Verb> findVerbs();

    Stream<Adjective> findAdjectives();
}
