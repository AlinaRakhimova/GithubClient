package ru.rakhimova.githubclient.model.network;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.rakhimova.githubclient.model.entity.User;

public interface GithubService {

    @GET("/users")
    Observable<List<User>> getAllUsers(@Query("per_page") String per_page, @Query("page") String page, @Query("since") String since);

    @GET("/users/{user}")
    Observable<User> getUser(@Path("user") String login);
}
