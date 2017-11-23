package cn.compal.wolf.di.module;

import android.app.Activity;


import cn.compal.wolf.di.scope.ActivityScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by wolf on 2017/9/2.
 */


@Module
public class ActivityModule
{
    private Activity mActivity;

    public ActivityModule(Activity activity)
    {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity()
    {
        return mActivity;
    }
}
