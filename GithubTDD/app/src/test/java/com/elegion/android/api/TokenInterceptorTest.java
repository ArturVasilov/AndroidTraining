package com.elegion.android.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import okhttp3.Interceptor;

import static org.junit.Assert.assertNotNull;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class TokenInterceptorTest {

    private static final String TEST_TOKEN = "aaaaa";

    @Test
    public void testConstructor() throws Exception {
        Interceptor interceptor = TokenInterceptor.getInstance(TEST_TOKEN);
        assertNotNull(interceptor);
    }

    /*

    bullshit, Request is a final class, it's impossible to mock it

    @Test
    public void testProceed() throws Exception {
        Interceptor interceptor = TokenInterceptor.getInstance(TEST_TOKEN);

        Interceptor.Chain chain = mock(Interceptor.Chain.class);
        final Request request = mock(Request.class);

        final Request.Builder builder = mock(Request.Builder.class);
        when(builder.addHeader(anyString(), anyString())).then(new Answer<Request.Builder>() {
            @Override
            public Request.Builder answer(InvocationOnMock invocation) throws Throwable {
                CharSequence name = (CharSequence) invocation.getArguments()[0];
                CharSequence value = (CharSequence) invocation.getArguments()[1];
                assertEquals("Authorization", name);
                assertEquals("token " + TEST_TOKEN, value);
                return builder;
            }
        });
        when(builder.build()).thenReturn(request);
        when(request.newBuilder()).thenReturn(builder);

        final Response response = mock(Response.class);

        when(chain.request()).thenReturn(request);
        when(chain.proceed(any(Request.class))).then(new Answer<Response>() {
            @Override
            public Response answer(InvocationOnMock invocation) throws Throwable {
                Request chainRequest = (Request) invocation.getArguments()[0];
                assertTrue(chainRequest == request);
                return response;
            }
        });

        assertTrue(response == interceptor.intercept(chain));
    }*/
}
