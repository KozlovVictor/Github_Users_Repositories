package ru.kozlov_victor.github_users_repositories.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
import ru.kozlov_victor.github_users_repositories.ui.image.GlideImageLoader;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter mainPresenter;

    @BindView(R.id.iv_avatar)
    ImageView avatarImageView;

    @BindView(R.id.tv_username)
    TextView usernameTextView;

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
    public void setUsernameText(String username) {
        usernameTextView.setText(username);
    }

    @Override
    public void setUserImageUrl(String avatarUrl) {
        imageLoader.loadInto(avatarUrl, avatarImageView);
    }
}
