package cn.compal.wolf.test;

import android.util.Log;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import cn.compal.wolf.base.RxPresenter;
import cn.compal.wolf.model.DataManager;
import cn.compal.wolf.model.http.api.BaseApi;
import cn.compal.wolf.model.http.exception.RetryWhenNetworkException;
import cn.compal.wolf.model.http.response.ZqzhHttpResponse;
import cn.compal.wolf.model.http.subscriber.CommonSubscriber;
import cn.compal.wolf.model.http.tools.HttpRequestUtil;
import cn.compal.wolf.util.JSONUtils;
import cn.compal.wolf.util.RxUtil;

/**
 * Created by wolf on 2017/11/17.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter
{
    private DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager mDataManager)
    {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getZqzhHttpResponse(BaseApi api)
    {
        /*addSubscribe(mDataManager.getZqzhHttpResponse(api)
                .compose(RxUtil.<ZqzhHttpResponse<List<InstructionBooksBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<InstructionBooksBean>>handleResult())
                .subscribeWith(new ResourceSubscriber<List<InstructionBooksBean>>()
                {
                    @Override
                    public void onNext(List<InstructionBooksBean> datas)
                    {
                        Log.i("--->", "datas.size() = " + datas.size());
                        Log.i("--->", "datas.get(0).getSysModuleName() = " + datas.get(0).getSysModuleName());
                    }

                    @Override
                    public void onError(Throwable t)
                    {
                        Log.i("--->", "Throwable = " + t.getMessage());
                    }

                    @Override
                    public void onComplete()
                    {

                    }
                })
        );*/

        addSubscribe(mDataManager.getZqzhHttpResponse(api)
                .retryWhen(new RetryWhenNetworkException())
                .compose(RxUtil.<ZqzhHttpResponse<List<InstructionBooksBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<InstructionBooksBean>>handleResult())
                .subscribeWith(new CommonSubscriber<List<InstructionBooksBean>>(mView)
                {
                    @Override
                    public void onNext(List<InstructionBooksBean> datas)
                    {
                        Log.i("--->", "datas.size() = " + datas.size());
                        Log.i("--->", "datas.get(0).getSysModuleName() = " + datas.get(1).getSysModuleName());
                    }
                })
        );
    }

    @Override
    public void getZqzhHttpStringResponse(BaseApi api)
    {
        /*addSubscribe(mDataManager.getZqzhHttpStringResponse(api)
                .retryWhen(new RetryWhenNetworkException())
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.<Object>handleStringResult())
                .subscribeWith(new CommonSubscriber<Object>(mView)
                {
                    @Override
                    public void onNext(Object response)
                    {
                        *//*List<InstructionBooksBean> list = JSONUtils.parserObject2BeanList(response,InstructionBooksBean.class);
                        Log.i("--->", "bean.getData().size() = " + list.size());
                        Log.i("--->", "bean.getData().get(0).getSysModuleName() = " + list.get(0).getSysModuleName());*//*

                        List<Map<String,String>> list = JSONUtils.parserObject2MapList(response);
                        Log.i("--->", "bean.getData().size() = " + list.size());
                        Log.i("--->", "bean.getData().get(1).sysModuleName = " + list.get(1).get("sysModuleName"));
                    }
                })
        );*/
        addSubscribe(HttpRequestUtil.fetchHttpClient(api, mDataManager, mView, new HttpRequestUtil.ResponseMethod()
        {

            @Override
            public void onNext(Object response)
            {
                List<Map<String, String>> list = JSONUtils.parserObject2MapList(response);
                Log.i("--->", "addSubscribe list.size() = " + list.size());
                Log.i("--->", "addSubscribe list.get(1).sysModuleName = " + list.get(1).get("sysModuleName"));
            }
        }));

    }
}
