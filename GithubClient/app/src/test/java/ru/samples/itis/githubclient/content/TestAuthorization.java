package ru.samples.itis.githubclient.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ru.samples.itis.githubclient.content.auth.Authorization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class TestAuthorization {

    @Test
    public void testCreated() throws Exception {
        Authorization authorization = new Authorization();
        assertNotNull(authorization);
        assertNull(authorization.getToken());
        assertEquals(0, authorization.getId());
    }

    @Test
    public void testGettersSetters() throws Exception {
        Authorization authorization = new Authorization();

        authorization.setId(1121);
        assertEquals(1121, authorization.getId());

        authorization.setToken("cfrwgwrgrwgf");
        assertEquals("cfrwgwrgrwgf", authorization.getToken());
    }
}
