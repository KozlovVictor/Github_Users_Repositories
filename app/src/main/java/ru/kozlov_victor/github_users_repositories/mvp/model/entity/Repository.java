package ru.kozlov_victor.github_users_repositories.mvp.model.entity;

import com.google.gson.annotations.Expose;

public class Repository {

    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
