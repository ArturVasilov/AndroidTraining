package com.elegion.android.utils;

import android.util.Base64;

import com.elegion.android.BuildConfig;
import com.google.gson.JsonObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * @author Artur Vasilov
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Base64.class)
public class AuthorizationUtilsTest {

    @Before
    public void setUp() throws Exception {
        mockStatic(Base64.class);
        when(Base64.encodeToString(any(byte[].class), any(Integer.class))).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                byte[] bytes = (byte[]) invocation.getArguments()[0];
                return new String(bytes);
            }
        });
    }

    @Test
    public void testJsonParams() throws Exception {
        JsonObject json = AuthorizationUtils.createAuthorizationParam();
        assertNotNull(json);

        assertEquals(2, json.entrySet().size());

        assertTrue(json.has("client_id"));
        assertEquals(BuildConfig.CLIENT_ID, json.get("client_id").getAsString());

        assertTrue(json.has("client_secret"));
        assertEquals(BuildConfig.CLIENT_SECRET, json.get("client_secret").getAsString());
    }

    @Test
    public void testAuthorizationString() throws Exception {
        String login = "login";
        String password = "password";

        String result = AuthorizationUtils.createAuthorizationString(login, password);
        assertNotNull(result);

        assertTrue(result.startsWith("Basic "));
        assertTrue(result.contains(login));
        assertTrue(result.contains(password));
        assertTrue(result.contains(login + ":" + password));
    }
}
