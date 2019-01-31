package ru.kozlov_victor.github_users_repositories.mvp.model.entity;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class User {

    @Expose
    private String login;

    @Expose
    private String avatarUrl;

    private List<Repository> repositories = new ArrayList<>();
    @Expose
    private String reposUrl;

    public String getReposUrl() {
        return reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

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
