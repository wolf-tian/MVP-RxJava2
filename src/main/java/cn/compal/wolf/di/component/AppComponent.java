package cn.compal.wolf.di.component;


import javax.inject.Singleton;

import cn.compal.wolf.app.App;
import cn.compal.wolf.di.module.AppModule;
import cn.compal.wolf.di.module.HttpModule;
import cn.compal.wolf.model.DataManager;
import cn.compal.wolf.model.http.RetrofitHelper;
import cn.compal.wolf.model.prefs.impl.PreferencesHelperImpl;
import dagger.Component;

/**
 * Created by wolf on 2017/8/2.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent
{
    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    PreferencesHelperImpl preferencesHelper(); //提供sp帮助类
}
