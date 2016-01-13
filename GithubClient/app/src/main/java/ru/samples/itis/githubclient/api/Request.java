package ru.samples.itis.githubclient.api;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * @author Artur Vasilov
 */
public interface Request extends Serializable {

    void process(@NonNull GithubService service);

}
