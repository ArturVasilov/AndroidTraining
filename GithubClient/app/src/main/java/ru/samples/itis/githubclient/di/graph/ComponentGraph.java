package ru.samples.itis.githubclient.di.graph;

import javax.inject.Singleton;

import dagger.Component;
import ru.samples.itis.githubclient.api.RequestsService;
import ru.samples.itis.githubclient.content.auth.GithubAuthenticator;
import ru.samples.itis.githubclient.di.module.ApiModule;
import ru.samples.itis.githubclient.di.module.MainModule;
import ru.samples.itis.githubclient.di.module.ServerModule;

/**
 * @author Artur Vasilov
 */
@Singleton
@Component(modules = {ServerModule.class, ApiModule.class, MainModule.class})
public interface ComponentGraph {

    void injectAuthenticator(GithubAuthenticator authenticator);

    void injectRequestsService(RequestsService service);

}
