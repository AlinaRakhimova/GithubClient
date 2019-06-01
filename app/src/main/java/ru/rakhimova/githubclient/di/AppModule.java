package ru.rakhimova.githubclient.di;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.rakhimova.githubclient.model.PicassoLoader;
import ru.rakhimova.githubclient.model.entity.UserList;
import ru.rakhimova.githubclient.model.network.GithubApi;
import ru.rakhimova.githubclient.model.network.GithubService;

import static ru.rakhimova.githubclient.model.Constants.BASE_URL;


@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    UserList getUserList() {
        return new UserList();
    }

    @Provides
    Context getContext() {
        return context;
    }

    @Provides
    GithubApi getGithubApi() {
        return new GithubApi();
    }

    @Provides
    PicassoLoader getPicassoLoader() {
        return new PicassoLoader();
    }

    @Provides
    Gson getGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Provides
    GsonConverterFactory getGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    RxJava2CallAdapterFactory getRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    GithubService getRetrofitService(GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory, OkHttpClient.Builder client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .client(client.build())
                .build()
                .create(GithubService.class);
    }

    @Provides
    HttpLoggingInterceptor getHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    OkHttpClient.Builder getClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor);
    }
}
