package ru.rakhimova.githubclient.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.rakhimova.githubclient.presenter.DetailPresenter;
import ru.rakhimova.githubclient.presenter.UserListPresenter;

@Singleton
@Component(modules = {TestModule.class})
public interface TestComponent {

    void inject(UserListPresenter presenter);

    void inject(DetailPresenter presenter);
}
