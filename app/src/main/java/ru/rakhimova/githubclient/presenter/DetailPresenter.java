package ru.rakhimova.githubclient.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ru.rakhimova.githubclient.model.entity.User;
import ru.rakhimova.githubclient.model.network.GithubApi;
import ru.rakhimova.githubclient.view.detail.DetailView;

import static ru.rakhimova.githubclient.model.Constants.ERROR_RETRIEVING_DATA_FROM_SERVER;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    @Inject
    GithubApi githubApi;

    private Disposable disposable;

    public void onStart(String login) {
        getGithubUser(login);
    }

    void getGithubUser(String login) {
        getViewState().showProgressBar();
        disposable = githubApi.requestUser(login)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    getDetailUser(user);
                    getViewState().hideProgressBar();
                }, throwable -> {
                    getViewState().showToast(ERROR_RETRIEVING_DATA_FROM_SERVER);
                    getViewState().hideProgressBar();
                });
    }

    void getDetailUser(User user) {
        String avatarUrl = user.getAvatarUrl();
        String userName = user.getName();
        String userLogin = user.getLogin();
        String userUrl = user.getHtmlUrl();
        String userBio = user.getBio();
        String userFollowers = String.valueOf((int) user.getFollowers());
        String profileCreatedAt = user.getCreatedAt().substring(0, 10);
        getViewState().showUser(avatarUrl, userName, userLogin, userUrl, userBio, userFollowers, profileCreatedAt);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) disposable.dispose();
    }
}
