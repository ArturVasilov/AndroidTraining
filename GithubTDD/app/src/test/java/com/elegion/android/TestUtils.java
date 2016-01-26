package com.elegion.android;

import android.content.Context;
import android.support.annotation.NonNull;

import com.elegion.android.app.Settings;

import org.mockito.internal.util.io.IOUtil;

import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Artur Vasilov
 */
public class TestUtils {

    @NonNull
    public static String readFile(@NonNull String fileName) {
        Collection<String> strings = IOUtil.readLines(TestUtils.class.getResourceAsStream(fileName));
        StringBuilder data = new StringBuilder();
        for (String line: strings) {
            data.append(line).append("\r\n");
        }
        return data.toString();
    }

    @NonNull
    public static Context mockedContextWithSettings(@NonNull Settings settings) {
        Context context = mock(Context.class);
        AppDelegate mockedApp = mock(AppDelegate.class);

        when(mockedApp.getSettings()).thenReturn(settings);
        when(context.getApplicationContext()).thenReturn(mockedApp);

        return context;
    }

}
