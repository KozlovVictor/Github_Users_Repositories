package ru.kozlov_victor.github_users_repositories.mvp.presenter.list;

import io.reactivex.subjects.PublishSubject;
import ru.kozlov_victor.github_users_repositories.mvp.view.item.IUsersRepositoryItemView;

public interface IUsersRepositoryListPresenter {

    PublishSubject<IUsersRepositoryItemView> getClickSubject();

    void bindView(IUsersRepositoryItemView view);

    int getUsersRepositoryCount();

}
