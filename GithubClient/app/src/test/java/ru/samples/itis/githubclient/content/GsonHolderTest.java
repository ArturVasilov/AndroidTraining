package ru.samples.itis.githubclient.content;

import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class GsonHolderTest {

    @Test
    public void testHolderInitialized() throws Exception {
        Gson gson = GsonHolder.getGson();
        assertNotNull(gson);
    }
}
