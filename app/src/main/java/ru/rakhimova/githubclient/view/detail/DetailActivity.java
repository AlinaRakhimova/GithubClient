package ru.rakhimova.githubclient.view.detail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.rakhimova.githubclient.R;
import ru.rakhimova.githubclient.di.App;
import ru.rakhimova.githubclient.model.PicassoLoader;
import ru.rakhimova.githubclient.presenter.DetailPresenter;

import static ru.rakhimova.githubclient.model.Constants.LOGIN;


public class DetailActivity extends MvpAppCompatActivity implements DetailView {

    @BindView(R.id.avatar)
    ImageView avatar;

    @BindView(R.id.login)
    TextView login;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.url)
    TextView url;

    @BindView(R.id.bio)
    TextView bio;

    @BindView(R.id.followers)
    TextView followers;

    @BindView(R.id.created_at)
    TextView createdAt;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @InjectPresenter
    DetailPresenter presenter;

    @Inject
    PicassoLoader picassoLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        App.getAppComponent().inject(this);
        App.getAppComponent().inject(presenter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String login = getIntent().getStringExtra(LOGIN);
        presenter.onStart(login);
    }

    @Override
    public void showUser(String avatarUrl, String userName, String userLogin, String userUrl, String userBio, String userFollowers, String profileCreatedAt) {
        picassoLoader.loadImage(avatarUrl, avatar);
        name.setText(userName);
        login.setText(userLogin);
        url.setText(userUrl);
        bio.setText(userBio);
        followers.setText(userFollowers);
        createdAt.setText(profileCreatedAt);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }
}
