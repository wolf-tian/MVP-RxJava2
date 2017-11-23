package cn.compal.wolf.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;


import javax.inject.Inject;

import cn.compal.wolf.app.App;
import cn.compal.wolf.di.component.DaggerFragmentComponent;
import cn.compal.wolf.di.component.FragmentComponent;
import cn.compal.wolf.di.module.FragmentModule;
import cn.compal.wolf.util.SnackbarUtil;

/**
 * Created by wolf on 2017/9/2.
 * MVP Fragment基类
 */

public abstract class MVPBaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView
{
    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent()
    {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule()
    {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView()
    {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg)
    {
        SnackbarUtil.show(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void useNightMode(boolean isNight)
    {

    }

    @Override
    public void stateError()
    {

    }

    @Override
    public void stateEmpty()
    {

    }


    @Override
    public void stateLoading()
    {

    }

    @Override
    public void stateMain()
    {

    }

    protected abstract void initInject();
}

