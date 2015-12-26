package ru.samples.itis.githubclient;

import android.support.annotation.NonNull;

import org.mockito.internal.util.io.IOUtil;

import java.util.Collection;

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

}
