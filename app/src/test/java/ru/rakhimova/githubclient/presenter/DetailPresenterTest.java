package ru.rakhimova.githubclient.presenter;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import ru.rakhimova.githubclient.di.DaggerTestComponent;
import ru.rakhimova.githubclient.di.TestComponent;
import ru.rakhimova.githubclient.di.TestModule;
import ru.rakhimova.githubclient.model.entity.User;
import ru.rakhimova.githubclient.model.network.GithubApi;
import ru.rakhimova.githubclient.view.detail.DetailView;

import static ru.rakhimova.githubclient.di.ConstantsTest.AVATAR_URL;
import static ru.rakhimova.githubclient.di.ConstantsTest.CREATED_AT;
import static ru.rakhimova.githubclient.di.ConstantsTest.EMPTY_STRING;
import static ru.rakhimova.githubclient.di.ConstantsTest.FOLLOWERS;
import static ru.rakhimova.githubclient.di.ConstantsTest.LOGIN;

@RunWith(MockitoJUnitRunner.class)
public class DetailPresenterTest {

    private static User user;
    private static TestComponent component;

    private DetailPresenter presenter;

    @Mock
    private DetailView detailView;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }

    @BeforeClass
    public static void createUser() {
        user = new User();
        user.setId(1);
        user.setAvatarUrl(AVATAR_URL);
        user.setLogin(LOGIN);
        user.setName((LOGIN));
        user.setHtmlUrl("");
        user.setBio("");
        user.setFollowers(FOLLOWERS);
        user.setCreatedAt(CREATED_AT);
    }

    @BeforeClass
    public static void overrideTestComponent() {
        component = DaggerTestComponent.builder()
                .testModule(new TestModule() {

                    @Override
                    public GithubApi provideGithubApi() {
                        GithubApi githubApi = super.provideGithubApi();
                        Mockito.when(githubApi.requestUser(LOGIN)).thenReturn(Observable.just(user));
                        return githubApi;
                    }
                }).build();
    }

    @Before
    public void before() {
        presenter = Mockito.spy(new DetailPresenter());
    }

    @Test
    public void getGithubUser_isCorrect() {
        component.inject(presenter);
        presenter.attachView(detailView);
        presenter.getGithubUser(LOGIN);
        Mockito.verify(detailView).hideProgressBar();
    }

    @Test
    public void getDetailUser_isCorrect() {
        component.inject(presenter);
        presenter.attachView(detailView);
        presenter.getDetailUser(user);
        Mockito.verify(detailView).showUser(AVATAR_URL, LOGIN, LOGIN, EMPTY_STRING, EMPTY_STRING, String.valueOf(FOLLOWERS), CREATED_AT);
    }
}
