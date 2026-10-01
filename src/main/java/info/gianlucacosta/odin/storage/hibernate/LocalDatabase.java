package info.gianlucacosta.odin.storage.hibernate;

import info.gianlucacosta.balmung.lexicon.Adjective;
import info.gianlucacosta.balmung.lexicon.Lemma;
import info.gianlucacosta.balmung.lexicon.Noun;
import info.gianlucacosta.balmung.lexicon.Verb;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

public class LocalDatabase {
    private final Optional<Path> rootDirectoryPathOption;
    private final Optional<Path> dataDirectoryPathOption;

    public LocalDatabase() {
        this(Optional.empty());
    }

    public LocalDatabase(Optional<Path> rootDirectoryPathOption) {
        this.rootDirectoryPathOption = rootDirectoryPathOption;
        this.dataDirectoryPathOption =
                rootDirectoryPathOption.map(rootDirectoryPath ->
                        rootDirectoryPath.resolve("data")
                );
    }

    public Optional<Path> getRootDirectoryPathOption() {
        return rootDirectoryPathOption;
    }


    public boolean isInMemory() {
        return !rootDirectoryPathOption.isPresent();
    }

    public boolean exists() {
        return !isInMemory() &&
                Files.isRegularFile(
                        rootDirectoryPathOption.get().resolve("data.properties")
                );
    }


    public String getConnectionString() {
        return isInMemory() ?
                String.format("jdbc:hsqldb:mem:%s", UUID.randomUUID())
                :
                String.format("jdbc:hsqldb:file:%s", dataDirectoryPathOption.get());
    }


    public SessionFactory createSessionFactory() {
        rootDirectoryPathOption.ifPresent(rootDirectoryPath -> {
            try {
                Files.createDirectories(rootDirectoryPath);
            } catch (IOException ex) {
                throw new RuntimeException("Error while creating the DB root directory", ex);
            }
        });


        ServiceRegistry standardRegistry =
                new StandardServiceRegistryBuilder()
                        .applySetting(AvailableSettings.URL, getConnectionString())
                        .build();

        Metadata metadata =
                new MetadataSources(standardRegistry)
                        .addAnnotatedClass(Lemma.class)
                        .addAnnotatedClass(Noun.class)
                        .addAnnotatedClass(Verb.class)
                        .addAnnotatedClass(Adjective.class)
                        .addResource(getClass().getResource("Queries.hbm.xml").toExternalForm())
                        .buildMetadata();

        return metadata
                .buildSessionFactory();
    }
}
