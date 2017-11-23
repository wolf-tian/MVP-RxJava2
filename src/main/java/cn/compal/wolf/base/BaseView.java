package cn.compal.wolf.base;

/**
 * Created by wolf on 2017/9/2.
 */

public interface BaseView
{
    void showErrorMsg(String msg);

    void useNightMode(boolean isNight);

    //=======  State  =======
    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();
}