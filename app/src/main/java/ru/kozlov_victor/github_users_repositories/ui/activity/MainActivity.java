package ru.kozlov_victor.github_users_repositories.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.kozlov_victor.github_users_repositories.R;
import ru.kozlov_victor.github_users_repositories.mvp.model.image.IImageLoader;
import ru.kozlov_victor.github_users_repositories.mvp.presenter.MainPresenter;
import ru.kozlov_victor.github_users_repositories.mvp.view.MainView;
import ru.kozlov_victor.github_users_repositories.ui.adapter.UserRepositoryAdapter;
import ru.kozlov_victor.github_users_repositories.ui.image.GlideImageLoader;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter mainPresenter;

    @BindView(R.id.iv_avatar)
    ImageView avatarImageView;

    @BindView(R.id.tv_username)
    TextView usernameTextView;

    @BindView(R.id.rv_user_repository)
    RecyclerView recyclerView;

    UserRepositoryAdapter adapter;

    IImageLoader<ImageView> imageLoader = new GlideImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @ProvidePresenter
    public MainPresenter provideMainPresenter() {
        return new MainPresenter(AndroidSchedulers.mainThread());
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new UserRepositoryAdapter(mainPresenter.getRepositoryListPresenter());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateRepositoryList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setUsernameText(String username) {
        usernameTextView.setText(username);
    }

    @Override
    public void setUserImageUrl(String avatarUrl) {
        imageLoader.loadInto(avatarUrl, avatarImageView);
    }
}
