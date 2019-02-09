package ru.kozlov_victor.github_users_repositories.mvp.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.Scheduler;
import io.reactivex.subjects.PublishSubject;
import ru.kozlov_victor.github_users_repositories.mvp.model.entity.Repository;
import ru.kozlov_victor.github_users_repositories.mvp.model.entity.User;
import ru.kozlov_victor.github_users_repositories.mvp.model.repo.UsersRepo;
import ru.kozlov_victor.github_users_repositories.mvp.presenter.list.IUsersRepositoryListPresenter;
import ru.kozlov_victor.github_users_repositories.mvp.view.MainView;
import ru.kozlov_victor.github_users_repositories.mvp.view.item.IUsersRepositoryItemView;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private static final String USERNAME = "googlesamples";

    private UserRepositoryListPresenter repositoryListPresenter;

    private Scheduler mainThreadScheduler;
    private UsersRepo usersRepo;
    private User targetUser;

    public MainPresenter(Scheduler mainThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.usersRepo = new UsersRepo();
        this.repositoryListPresenter = new UserRepositoryListPresenter();
    }

    public UserRepositoryListPresenter getRepositoryListPresenter() {
        return repositoryListPresenter;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
        loadData();
    }

    @SuppressLint("CheckResult")
    private void loadData() {
        usersRepo.getUser(USERNAME)
                .observeOn(mainThreadScheduler)
                .subscribe(user -> {
                            this.targetUser = user;
                            getViewState().setUsernameText(user.getLogin());
                            getViewState().setUserImageUrl(user.getAvatarUrl());
                            usersRepo.getUserRepository(targetUser)
                                    .observeOn(mainThreadScheduler)
                                    .subscribe(repositories -> {
                                        targetUser.setRepositories(repositories);
                                        getViewState().updateRepositoryList();
                                    }, Timber::e);
                        },
                        Timber::e);
    }

    public class UserRepositoryListPresenter implements IUsersRepositoryListPresenter {

        PublishSubject<IUsersRepositoryItemView> clickSubject = PublishSubject.create();

        @Override
        public PublishSubject<IUsersRepositoryItemView> getClickSubject() {
            return clickSubject;
        }

        @Override
        public void bindView(IUsersRepositoryItemView view) {
            Repository repository = targetUser.getRepositories().get(view.getPos());
            view.setTitle(repository.getName());
        }

        @Override
        public int getUsersRepositoryCount() {
            return targetUser == null || targetUser.getRepositories() == null ? 0 : targetUser.getRepositories().size();
        }
    }
}
