package ru.rakhimova.githubclient.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.rakhimova.githubclient.model.network.GithubApi;
import ru.rakhimova.githubclient.presenter.DetailPresenter;
import ru.rakhimova.githubclient.presenter.UserListPresenter;
import ru.rakhimova.githubclient.view.detail.DetailActivity;
import ru.rakhimova.githubclient.view.userlist.UserListAdapter;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(GithubApi githubApi);

    void inject(UserListAdapter userListAdapter);

    void inject(DetailPresenter presenter);

    void inject(DetailActivity detailActivity);

    void inject(UserListPresenter presenter);
}
