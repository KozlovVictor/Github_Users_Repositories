package ru.kozlov_victor.github_users_repositories.mvp.model.entity;

import com.google.gson.annotations.Expose;

public class User {

    @Expose
    private String login;

    @Expose
    private String avatarUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}