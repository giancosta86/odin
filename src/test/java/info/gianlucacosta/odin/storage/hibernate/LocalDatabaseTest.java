package info.gianlucacosta.odin.storage.hibernate;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class LocalDatabaseTest extends LocalDatabaseTestBase {
    @Test
    public void creatingTheSessionFactoryShouldWorkWithoutError() {
        assertThat(
                sessionFactory,
                notNullValue()
        );
    }
}
