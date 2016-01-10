package ru.samples.itis.githubclient.di.component;


import android.support.annotation.NonNull;

import dagger.Component;
import ru.samples.itis.githubclient.GithubApplication;
import ru.samples.itis.githubclient.di.graph.FragmentGraph;
import ru.samples.itis.githubclient.di.module.ApiModule;
import ru.samples.itis.githubclient.di.module.MainModule;
import ru.samples.itis.githubclient.di.module.ServerModule;

/**
 * @author Artur Vasilov
 */
@Component(modules = {MainModule.class, ServerModule.class, ApiModule.class})
public interface FragmentComponent extends FragmentGraph {

    final class Initializer {

        private Initializer() {
        } // No instances.

        @NonNull
        public static FragmentComponent init(@NonNull GithubApplication application) {
            return DaggerFragmentComponent.builder()
                    .mainModule(new MainModule(application))
                    .serverModule(new ServerModule())
                    .apiModule(new ApiModule())
                    .build();
        }
    }

}
