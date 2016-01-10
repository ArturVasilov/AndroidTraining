package ru.samples.itis.githubclient.di.component;

import android.support.annotation.NonNull;

import dagger.Component;
import ru.samples.itis.githubclient.GithubApplication;
import ru.samples.itis.githubclient.di.graph.ActivityGraph;
import ru.samples.itis.githubclient.di.module.ApiModule;
import ru.samples.itis.githubclient.di.module.MainModule;
import ru.samples.itis.githubclient.di.module.ServerModule;

/**
 * @author Artur Vasilov
 */
@Component(modules = {MainModule.class, ServerModule.class, ApiModule.class})
public interface ActivityComponent extends ActivityGraph {

    final class Initializer {

        private Initializer() {
        } // No instances.

        @NonNull
        public static ActivityComponent init(@NonNull GithubApplication application) {
            return DaggerActivityComponent.builder()
                    .mainModule(new MainModule(application))
                    .serverModule(new ServerModule())
                    .apiModule(new ApiModule())
                    .build();
        }
    }

}
