package ru.rakhimova.githubclient.presenter;

import ru.rakhimova.githubclient.view.userlist.IViewHolder;

public interface IRecyclerUserListPresenter {

    void bindView(IViewHolder iViewHolder);

    int getItemCount();

    void onClickDetail(int position);
}
