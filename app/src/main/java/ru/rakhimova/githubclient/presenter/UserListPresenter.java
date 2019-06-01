package ru.rakhimova.githubclient.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.rakhimova.githubclient.model.entity.User;
import ru.rakhimova.githubclient.model.entity.UserList;
import ru.rakhimova.githubclient.model.network.GithubApi;
import ru.rakhimova.githubclient.view.userlist.IViewHolder;
import ru.rakhimova.githubclient.view.userlist.UserListView;

@InjectViewState
public class UserListPresenter extends MvpPresenter<UserListView> {

    @Inject
    GithubApi githubApi;
    @Inject
    UserList userList;
    private RecyclerUserListPresenter recyclerGalleryPresenter;

    public UserListPresenter() {
        recyclerGalleryPresenter = new RecyclerUserListPresenter();
    }

    @Override
    public void onFirstViewAttach() {
        int startPage = 1;
        int startPosition = 0;
        getGithubUsers(startPage, startPosition);
    }

    @SuppressLint("CheckResult")
    public void getGithubUsers(int page, int position) {
        getViewState().showProgressBar();
        String pageString = String.valueOf(page);
        int since;
        if (!userList.getUsers().isEmpty()) {
            since = (int) userList.getUsers().get(position).getId();
        } else since = 0;
        String sinceString = String.valueOf(since);
        githubApi.requestAllUsers(pageString, sinceString)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newUsers -> {
                    userList.getUsers().addAll(newUsers);
                    ifRequestSuccess();
                }, throwable -> {
                    getViewState().showToast("Error retrieving data from server");
                    getViewState().hideProgressBar();
                });
    }

    private void ifRequestSuccess() {
        getViewState().updateRecyclerView();
        getViewState().hideProgressBar();
        getViewState().setLoading(false);
    }

    public RecyclerUserListPresenter getRecyclerGalleryPresenter() {
        return recyclerGalleryPresenter;
    }

    public class RecyclerUserListPresenter implements IRecyclerUserListPresenter {

        @Override
        public void bindView(IViewHolder holder) {
            User user = getUser(holder);
            holder.setUser(user.getLogin(), user.getAvatarUrl());
        }

        @Override
        public int getItemCount() {
            if (userList.getUsers() != null) {
                return userList.getUsers().size();
            }
            return 0;
        }

        @Override
        public void onClickDetail(IViewHolder holder) {
            getViewState().showDetailActivity(getUser(holder).getLogin());
        }

        private User getUser(IViewHolder holder) {
            return userList.getUsers().get(holder.getPos());
        }
    }
}
