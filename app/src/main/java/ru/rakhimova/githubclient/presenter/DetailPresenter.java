package ru.rakhimova.githubclient.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.rakhimova.githubclient.model.entity.User;
import ru.rakhimova.githubclient.model.entity.UserList;
import ru.rakhimova.githubclient.model.network.GithubApi;
import ru.rakhimova.githubclient.view.detail.DetailView;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    @Inject
    UserList userList;
    @Inject
    GithubApi githubApi;

    public void onStart(String login) {
        getGithubUser(login);
    }

    @SuppressLint("CheckResult")
    void getGithubUser(String login) {
        getViewState().showProgressBar();
        githubApi.requestUser(login)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    getDetailUser(user);
                    getViewState().hideProgressBar();
                }, throwable -> {
                    getViewState().showToast("Error retrieving data from server ");
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
}
