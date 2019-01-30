package ru.kozlov_victor.github_users_repositories.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndStrategy.class)
public interface MainView extends MvpView {

    void setUsernameText(String username);

    void setUserImageUrl(String avatarUrl);
}
