package ru.kozlov_victor.github_users_repositories.mvp.model.image;

public interface IImageLoader<T> {

    void loadInto(String url, T container);

}
