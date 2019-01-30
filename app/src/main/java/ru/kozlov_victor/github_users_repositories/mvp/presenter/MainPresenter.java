package ru.kozlov_victor.github_users_repositories.mvp.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.Scheduler;
import ru.kozlov_victor.github_users_repositories.mvp.model.repo.UsersRepo;
import ru.kozlov_victor.github_users_repositories.mvp.view.MainView;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private Scheduler mainThreadScheduler;
    private UsersRepo usersRepo;

    public MainPresenter(Scheduler mainThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.usersRepo = new UsersRepo();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadUser();
    }

    @SuppressLint("CheckResult")
    private void loadUser() {
        usersRepo.getUser("googlesamples")
                .observeOn(mainThreadScheduler)
                .subscribe(user -> {
                            getViewState().setUsernameText(user.getLogin());
                            getViewState().setUserImageUrl(user.getAvatarUrl());
                        },
                        Timber::e);
    }
}
