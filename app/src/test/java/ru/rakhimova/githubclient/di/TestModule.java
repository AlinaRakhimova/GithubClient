package ru.rakhimova.githubclient.di;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;
import ru.rakhimova.githubclient.model.entity.UserList;
import ru.rakhimova.githubclient.model.network.GithubApi;

@Module
public class TestModule {

    @Provides
    public GithubApi provideGithubApi() {
        return Mockito.mock(GithubApi.class);
    }

    @Provides
    public UserList provideUserList() {
        return Mockito.mock(UserList.class);
    }
}
