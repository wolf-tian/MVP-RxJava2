package cn.compal.wolf.test;

import cn.compal.wolf.base.BasePresenter;
import cn.compal.wolf.base.BaseView;
import cn.compal.wolf.model.http.api.BaseApi;

/**
 * Created by wolf on 2017/11/17.
 */

public class MainContract{

    interface View extends BaseView
    {

        //void showContent(WelcomeBean welcomeBean);

        void jumpToMain();

    }

    interface  Presenter extends BasePresenter<View>
    {
        void getZqzhHttpResponse(BaseApi api);

        void getZqzhHttpStringResponse(BaseApi api);
    }
}
