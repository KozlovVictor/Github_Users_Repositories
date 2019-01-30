package ru.kozlov_victor.github_users_repositories.mvp.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.subjects.PublishSubject;
import ru.kozlov_victor.github_users_repositories.mvp.model.entity.Repository;
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
        loadUser();
        loadRepository();
//        repositoryListPresenter.clickSubject.subscribe((IUsersRepositoryItemView item) - > {
//            getViewState().showMessage();
//        });
    }

    @SuppressLint("CheckResult")
    private void loadUser() {
        usersRepo.getUser(USERNAME)
                .observeOn(mainThreadScheduler)
                .subscribe(user -> {
                            getViewState().setUsernameText(user.getLogin());
                            getViewState().setUserImageUrl(user.getAvatarUrl());
                        },
                        Timber::e);
    }

    @SuppressLint("CheckResult")
    private void loadRepository() {
        usersRepo.getUserRepository(USERNAME)
                .subscribeOn(mainThreadScheduler)
                .subscribe(repositoryList -> {
                    repositoryListPresenter.userRepositoryList = repositoryList;
                    getViewState().updateRepositoryList();
        });
    }

    public class UserRepositoryListPresenter implements IUsersRepositoryListPresenter {

        PublishSubject<IUsersRepositoryItemView> clickSubject = PublishSubject.create();
        List<Repository> userRepositoryList = new ArrayList<>();

        @Override
        public PublishSubject<IUsersRepositoryItemView> getClickSubject() {
            return clickSubject;
        }

        @Override
        public void bindView(IUsersRepositoryItemView view) {
            Repository repository = userRepositoryList.get(view.getPos());
            view.setTitle(repository.getName());
        }

        @Override
        public int getUsersRepositoryCount() {
            return userRepositoryList.size();
        }
    }
}
