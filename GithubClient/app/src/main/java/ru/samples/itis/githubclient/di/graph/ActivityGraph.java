package ru.samples.itis.githubclient.di.graph;

import javax.inject.Singleton;

import dagger.Component;
import ru.samples.itis.githubclient.activity.auth.LogInActivity;
import ru.samples.itis.githubclient.di.module.ApiModule;
import ru.samples.itis.githubclient.di.module.MainModule;
import ru.samples.itis.githubclient.di.module.ServerModule;

/**
 * @author Artur Vasilov
 */
@Singleton
@Component(modules = {ServerModule.class, ApiModule.class, MainModule.class})
public interface ActivityGraph {

    void injectLogInActivity(LogInActivity logInActivity);

}
