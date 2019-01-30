package ru.kozlov_victor.github_users_repositories;

import android.app.Application;

import timber.log.Timber;

public class App extends Application {

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
