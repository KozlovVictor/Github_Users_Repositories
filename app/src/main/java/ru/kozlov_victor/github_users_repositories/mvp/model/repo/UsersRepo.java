package ru.kozlov_victor.github_users_repositories.mvp.model.repo;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import ru.kozlov_victor.github_users_repositories.mvp.model.api.ApiHolder;
import ru.kozlov_victor.github_users_repositories.mvp.model.entity.User;

public class UsersRepo {

    public Single<User> getUser(String username) {
        return ApiHolder.getApi().getUser(username).subscribeOn(Schedulers.io());
    }
}
