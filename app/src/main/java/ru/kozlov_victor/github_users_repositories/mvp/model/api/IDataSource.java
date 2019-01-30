package ru.kozlov_victor.github_users_repositories.mvp.model.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.kozlov_victor.github_users_repositories.mvp.model.entity.User;

public interface IDataSource {

    @GET("/users/{user}")
    Single<User> getUser(@Path("user") String username);
}
