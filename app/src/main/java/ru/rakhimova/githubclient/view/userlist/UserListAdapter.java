package ru.rakhimova.githubclient.view.userlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.rakhimova.githubclient.R;
import ru.rakhimova.githubclient.di.App;
import ru.rakhimova.githubclient.model.PicassoLoader;
import ru.rakhimova.githubclient.presenter.IRecyclerUserListPresenter;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private final IRecyclerUserListPresenter presenter;

    @Inject
    PicassoLoader glideLoader;

    UserListAdapter(IRecyclerUserListPresenter presenter) {
        App.getAppComponent().inject(this);
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_user, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.avatar.setOnClickListener(v -> presenter.onClickDetail(holder));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.position = position;
        presenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getItemCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements IViewHolder {

        @BindView(R.id.avatar)
        ImageView avatar;

        @BindView(R.id.login)
        TextView login;

        private int position = 0;

        private ViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void setUser(String title, String url) {
            login.setText(title);
            glideLoader.loadImage(url, avatar);
        }

        @Override
        public int getPos() {
            return position;
        }
    }
}
