package cn.compal.wolf.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;


import cn.compal.wolf.di.scope.FragmentScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by wolf on 2017/9/2.
 */

@Module
public class FragmentModule
{

    private Fragment fragment;

    public FragmentModule(Fragment fragment)
    {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity()
    {
        return fragment.getActivity();
    }
}