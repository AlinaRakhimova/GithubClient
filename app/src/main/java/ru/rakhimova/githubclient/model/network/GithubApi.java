package ru.rakhimova.githubclient.model.network;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ru.rakhimova.githubclient.di.App;
import ru.rakhimova.githubclient.model.entity.User;

import static ru.rakhimova.githubclient.model.Constants.USERS_PER_PAGE;

public class GithubApi {

    @Inject
    GithubService githubService;

    public GithubApi() {
        App.getAppComponent().inject(this);
    }

    public Observable<List<User>> requestAllUsers(String page, String since) {
        return githubService.getAllUsers(USERS_PER_PAGE, page, since).subscribeOn(Schedulers.io());
    }

    public Observable<User> requestUser(String login) {
        return githubService.getUser(login).subscribeOn(Schedulers.io());
    }
}
