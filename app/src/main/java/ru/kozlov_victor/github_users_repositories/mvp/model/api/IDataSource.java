package ru.kozlov_victor.github_users_repositories.mvp.model.api;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import ru.kozlov_victor.github_users_repositories.mvp.model.entity.Repository;
import ru.kozlov_victor.github_users_repositories.mvp.model.entity.User;

public interface IDataSource {

    @GET("/users/{user}")
    Single<User> getUser(@Path("user") String username);

    @GET
    Single<List<Repository>> getUserRepository(@Url String url);
}
