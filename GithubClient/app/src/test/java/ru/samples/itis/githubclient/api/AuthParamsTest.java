package ru.samples.itis.githubclient.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ru.samples.itis.githubclient.api.body.AuthParams;

import static org.junit.Assert.assertNotNull;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class AuthParamsTest {

    @Test
    public void testAuthParamsCreated() throws Exception {
        String accessCode = "adofwrhxaxqdef";
        AuthParams params = AuthParams.create(accessCode);
        assertNotNull(params);
    }
}
