package ru.rakhimova.githubclient.view.detail;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface DetailView extends MvpView {

    void showUser(String avatarUrl, String userName, String userLogin, String userUrl, String userBio, String userFollowers, String profileCreatedAt);

    void showToast(String message);

    void hideProgressBar();

    void showProgressBar();
}
