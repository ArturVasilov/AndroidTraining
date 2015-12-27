package ru.samples.itis.githubclient.di;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import dagger.Provides;
import ru.samples.itis.githubclient.BuildConfig;
import ru.samples.itis.githubclient.di.module.ApiModule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class ApiModuleTest {

    @Test
    public void testApiEndpointMethod() throws Exception {
        Class<ApiModule> moduleClass = ApiModule.class;

        Method method = moduleClass.getDeclaredMethod("endpoint");
        assertNotNull(method);

        Annotation[] annotations = method.getAnnotations();
        assertEquals(1, annotations.length);
        Annotation annotation = annotations[0];
        assertEquals(Provides.class, annotation.annotationType());

        ApiModule module = new ApiModule();
        method.setAccessible(true);
        String result = (String) method.invoke(module);
        assertEquals(BuildConfig.API_ENDPOINT, result);
    }

    @Test
    public void testGithubServiceMethod() throws Exception {
        Class<ApiModule> moduleClass = ApiModule.class;

        Method method = moduleClass.getDeclaredMethod("endpoint");
        assertNotNull(method);

        Annotation[] annotations = method.getAnnotations();
        assertEquals(1, annotations.length);
        Annotation annotation = annotations[0];
        assertEquals(Provides.class, annotation.annotationType());

        ApiModule module = new ApiModule();
        method.setAccessible(true);
        String result = (String) method.invoke(module);
        assertEquals(BuildConfig.API_ENDPOINT, result);
    }
}
