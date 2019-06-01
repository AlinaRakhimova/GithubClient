package ru.rakhimova.githubclient.view.userlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.rakhimova.githubclient.R;
import ru.rakhimova.githubclient.di.App;
import ru.rakhimova.githubclient.presenter.UserListPresenter;
import ru.rakhimova.githubclient.view.detail.DetailActivity;

import static ru.rakhimova.githubclient.model.Constants.LOGIN;

public class UserListActivity extends MvpAppCompatActivity implements UserListView {

    @BindView(R.id.listUsers)
    RecyclerView userRecycler;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @InjectPresenter
    UserListPresenter presenter;
    private UserListAdapter userListAdapter;
    private LinearLayoutManager mLayoutManager;
    private boolean loading;
    private int pageCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ButterKnife.bind(this);
        App.getAppComponent().inject(presenter);
        initUI();
        initPagination();
    }

    private void initUI() {
        showUserRecycler();
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(userRecycler);
    }

    private void showUserRecycler() {
        mLayoutManager = new LinearLayoutManager(this);
        userRecycler.setLayoutManager(mLayoutManager);
        userRecycler.setHasFixedSize(true);
        userListAdapter = new UserListAdapter(presenter.getRecyclerGalleryPresenter());
        userRecycler.setAdapter(userListAdapter);
    }

    private void initPagination() {
        userRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition != userListAdapter.getItemCount() - 1) return;
                if (!loading) {
                    loading = true;
                    pageCount++;
                    presenter.getGithubUsers(pageCount, lastVisibleItemPosition);
                }
            }
        });
    }

    @Override
    public void showDetailActivity(String login) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(LOGIN, login);
        startActivity(intent);
    }

    @Override
    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    @Override
    public void updateRecyclerView() {
        userListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
