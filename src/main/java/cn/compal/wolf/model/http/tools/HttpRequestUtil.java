package cn.compal.wolf.model.http.tools;

import cn.compal.wolf.base.BaseView;
import cn.compal.wolf.model.DataManager;
import cn.compal.wolf.model.http.api.BaseApi;
import cn.compal.wolf.model.http.exception.RetryWhenNetworkException;
import cn.compal.wolf.model.http.subscriber.CommonSubscriber;
import cn.compal.wolf.util.RxUtil;

/**
 * Created by wolf on 2017/11/21.
 */

public class HttpRequestUtil
{
    /**
     * 获取http连接客户端
     * @param api 请求参数
     * @param mDataManager http获取工具实体
     * @param mView http请求绑定view
     * @param responseMethod 成功返回相应方法
     * @return
     */
    public static CommonSubscriber<Object> fetchHttpClient(BaseApi api, DataManager mDataManager, BaseView mView, final ResponseMethod responseMethod)
    {
        return mDataManager.getZqzhHttpStringResponse(api)
                .retryWhen(new RetryWhenNetworkException())
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.<Object>handleStringResult())
                .subscribeWith(new CommonSubscriber<Object>(mView)
                               {
                                   @Override
                                   public void onNext(Object response)
                                   {
                                       responseMethod.onNext(response);
                                   }
                               }
                );
    }

    public interface ResponseMethod
    {
        void onNext(Object response);
    }
}
