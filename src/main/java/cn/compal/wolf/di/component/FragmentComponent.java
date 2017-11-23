package cn.compal.wolf.di.component;

import android.app.Activity;

import cn.compal.wolf.di.module.FragmentModule;
import cn.compal.wolf.di.scope.FragmentScope;
import dagger.Component;

/**
 * Created by wolf on 2017/9/2.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent
{
    Activity getActivity();

    /*void inject(TechFragment techFragment);

    void inject(GirlFragment girlFragment);

    void inject(SettingFragment settingFragment);

    void inject(WechatMainFragment wechatMainFragment);*/
}
