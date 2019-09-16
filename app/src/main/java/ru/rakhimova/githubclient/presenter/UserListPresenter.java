package ru.rakhimova.githubclient.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ru.rakhimova.githubclient.model.entity.User;
import ru.rakhimova.githubclient.model.entity.UserList;
import ru.rakhimova.githubclient.model.network.GithubApi;
import ru.rakhimova.githubclient.view.userlist.IViewHolder;
import ru.rakhimova.githubclient.view.userlist.UserListView;

import static ru.rakhimova.githubclient.model.Constants.ERROR_RETRIEVING_DATA_FROM_SERVER;

@InjectViewState
public class UserListPresenter extends MvpPresenter<UserListView> {

    @Inject
    GithubApi githubApi;

    @Inject
    UserList userList;

    private RecyclerUserListPresenter recyclerGalleryPresenter;

    private Disposable disposable;

    public UserListPresenter() {
        recyclerGalleryPresenter = new RecyclerUserListPresenter();
    }

    public RecyclerUserListPresenter getRecyclerGalleryPresenter() {
        return recyclerGalleryPresenter;
    }

    @Override
    public void onFirstViewAttach() {
        int startPage = 1;
        int startPosition = 0;
        getGithubUsers(startPage, startPosition);
    }

    public void getGithubUsers(int page, int position) {
        getViewState().showProgressBar();
        String pageString = String.valueOf(page);
        int since;
        if (!userList.getUsers().isEmpty()) {
            since = (int) userList.getUsers().get(position).getId();
        } else since = 0;
        String sinceString = String.valueOf(since);
        disposable = githubApi.requestAllUsers(pageString, sinceString)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    userList.getUsers().addAll(users);
                    ifRequestSuccess();
                }, throwable -> {
                    getViewState().showToast(ERROR_RETRIEVING_DATA_FROM_SERVER);
                    getViewState().hideProgressBar();
                });
    }

    private void ifRequestSuccess() {
        getViewState().updateRecyclerView();
        getViewState().hideProgressBar();
        getViewState().setLoading(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) disposable.dispose();
    }

    public class RecyclerUserListPresenter implements IRecyclerUserListPresenter {

        @Override
        public void bindView(IViewHolder holder) {
            User user = getUser(holder.getPos());
            holder.setUser(user.getLogin(), user.getAvatarUrl());
        }

        @Override
        public void onClickDetail(int position) {
            getViewState().showDetailActivity(getUser(position).getLogin());
        }

        private User getUser(int position) {
            return userList.getUsers().get(position);
        }

        @Override
        public int getItemCount() {
            if (userList.getUsers() != null) {
                return userList.getUsers().size();
            }
            return 0;
        }
    }
}
