package ru.rakhimova.githubclient.presenter;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import ru.rakhimova.githubclient.di.DaggerTestComponent;
import ru.rakhimova.githubclient.di.TestComponent;
import ru.rakhimova.githubclient.di.TestModule;
import ru.rakhimova.githubclient.model.entity.User;
import ru.rakhimova.githubclient.model.network.GithubApi;
import ru.rakhimova.githubclient.view.userlist.UserListView;

import static ru.rakhimova.githubclient.di.ConstantsTest.PAGE;
import static ru.rakhimova.githubclient.di.ConstantsTest.SINCE;

@RunWith(MockitoJUnitRunner.class)
public class UserListPresenterTest {

    private UserListPresenter presenter;

    @Mock
    private UserListView userListView;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }

    @Before
    public void before() {
        presenter = Mockito.spy(new UserListPresenter());
    }

    @Test
    public void getGithubUsers_isCorrect() {
        TestComponent component = DaggerTestComponent.builder()
                .testModule(new TestModule() {

                    @Override
                    public GithubApi provideGithubApi() {
                        GithubApi retrofitApi = super.provideGithubApi();
                        User user = new User();
                        user.setId(1);
                        List<User> userList = new ArrayList<>();
                        userList.add(user);
                        Mockito.when(retrofitApi.requestAllUsers(PAGE, SINCE)).thenReturn(Observable.just(userList));
                        return retrofitApi;
                    }
                }).build();

        component.inject(presenter);
        presenter.attachView(userListView);
        Mockito.verify(userListView).updateRecyclerView();
    }
}
