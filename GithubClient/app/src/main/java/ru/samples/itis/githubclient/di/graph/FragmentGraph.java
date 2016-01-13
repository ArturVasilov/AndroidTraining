package ru.samples.itis.githubclient.di.graph;

import javax.inject.Singleton;

import dagger.Component;
import ru.samples.itis.githubclient.di.module.ApiModule;
import ru.samples.itis.githubclient.di.module.MainModule;
import ru.samples.itis.githubclient.di.module.ServerModule;
import ru.samples.itis.githubclient.fragment.LogInFragment;

/**
 * @author Artur Vasilov
 */
@Singleton
@Component(modules = {ServerModule.class, ApiModule.class, MainModule.class})
public interface FragmentGraph {

    void injectLogInFragment(LogInFragment fragment);

}
