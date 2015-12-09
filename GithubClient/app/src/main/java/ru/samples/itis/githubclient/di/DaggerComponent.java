package ru.samples.itis.githubclient.di;

import android.support.annotation.NonNull;

import dagger.Component;
import ru.samples.itis.githubclient.GithubApplication;
import ru.samples.itis.githubclient.di.module.ApiModule;
import ru.samples.itis.githubclient.di.module.MainModule;
import ru.samples.itis.githubclient.di.module.ServerModule;

/**
 * @author Artur Vasilov
 */
@Component(modules = {MainModule.class, ServerModule.class, ApiModule.class})
public interface DaggerComponent extends DaggerGraph {

    final class Initializer {

        private Initializer() {
        } // No instances.

        public static DaggerComponent init(@NonNull GithubApplication application) {
            return DaggerDaggerComponent.builder()
                    .mainModule(new MainModule(application))
                    .serverModule(new ServerModule())
                    .apiModule(new ApiModule())
                    .build();
        }
    }

}
