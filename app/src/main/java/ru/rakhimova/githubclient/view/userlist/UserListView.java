package ru.rakhimova.githubclient.view.userlist;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface UserListView extends MvpView {

    void updateRecyclerView();

    void hideProgressBar();

    void showProgressBar();

    void showToast(String message);

    void showDetailActivity(String login);

    void setLoading(boolean loading);
}
