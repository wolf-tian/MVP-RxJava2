package cn.compal.wolf.di.module;


import javax.inject.Singleton;

import cn.compal.wolf.app.App;
import cn.compal.wolf.model.DataManager;
import cn.compal.wolf.model.http.IHttpHelper;
import cn.compal.wolf.model.http.RetrofitHelper;
import cn.compal.wolf.model.prefs.IPreferencesHelper;
import cn.compal.wolf.model.prefs.impl.PreferencesHelperImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by wolf on 2017/9/2.
 */

@Module
public class AppModule
{
    private final App application;

    public AppModule(App application)
    {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext()
    {
        return application;
    }


    @Provides
    @Singleton
    IPreferencesHelper providePreferencesHelper(PreferencesHelperImpl preferencesHelper)
    {
        return preferencesHelper;
    }

    @Provides
    @Singleton
    IHttpHelper provideHttpHelper(RetrofitHelper retrofitHelper)
    {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(IHttpHelper httpHelper, IPreferencesHelper preferencesHelper)
    {
        return new DataManager(httpHelper, preferencesHelper);
    }
}
