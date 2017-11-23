package cn.compal.wolf.di.component;

import android.app.Activity;

import cn.compal.wolf.MainActivity;
import cn.compal.wolf.di.module.ActivityModule;
import cn.compal.wolf.di.scope.ActivityScope;
import dagger.Component;

/**
 * Created by wolf on 2017/9/3.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent
{
    Activity getActivity();

    //void inject(WelcomeActivity welcomeActivity);
    void inject(MainActivity mainActivity);
}
