package info.gianlucacosta.odin.storage.hibernate;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;

public abstract class LocalDatabaseTestBase {
    protected LocalDatabase localDatabase;
    protected SessionFactory sessionFactory;

    @Before
    public void init() {
        localDatabase = new LocalDatabase();
        sessionFactory = localDatabase.createSessionFactory();
    }

    @After
    public void clean() {
        sessionFactory.close();
    }
}
