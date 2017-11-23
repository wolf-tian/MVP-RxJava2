package cn.compal.wolf.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.compal.wolf.app.App;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by wolf on 2017/11/14.
 */

public abstract class SimpleActivity extends SupportActivity
{
    protected Activity mContext;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        App.getInstance().addActivity(this);
        initEventAndData();
    }

    /**
     * 手动处理view创建时相关事宜
     */
    protected void onViewCreated()
    {

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        mUnBinder.unbind();
    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();
}
