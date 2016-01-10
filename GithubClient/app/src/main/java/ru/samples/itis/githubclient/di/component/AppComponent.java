package ru.samples.itis.githubclient.di.component;

import android.support.annotation.NonNull;

import dagger.Component;
import ru.samples.itis.githubclient.GithubApplication;
import ru.samples.itis.githubclient.di.graph.ComponentGraph;
import ru.samples.itis.githubclient.di.module.ApiModule;
import ru.samples.itis.githubclient.di.module.MainModule;
import ru.samples.itis.githubclient.di.module.ServerModule;

/**
 * @author Artur Vasilov
 */
@Component(modules = {MainModule.class, ServerModule.class, ApiModule.class})
public interface AppComponent extends ComponentGraph {

    final class Initializer {

        private Initializer() {
        } // No instances.

        @NonNull
        public static AppComponent init(@NonNull GithubApplication application) {
            return DaggerAppComponent.builder()
                    .mainModule(new MainModule(application))
                    .serverModule(new ServerModule())
                    .apiModule(new ApiModule())
                    .build();
        }
    }

}