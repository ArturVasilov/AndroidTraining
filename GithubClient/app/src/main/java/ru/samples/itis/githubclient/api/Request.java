package ru.samples.itis.githubclient.api;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * @author Artur Vasilov
 */
public interface Request extends Serializable {

    void process(Context context, @NonNull GithubService service);

}
