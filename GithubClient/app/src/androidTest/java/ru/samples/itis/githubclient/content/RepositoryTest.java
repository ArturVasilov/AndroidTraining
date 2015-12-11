package ru.samples.itis.githubclient.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.Assert.assertNotNull;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class RepositoryTest {

    @Test
    public void testConstructor() throws Exception {
        Repository repository = new Repository();
        assertNotNull(repository);
    }
}
