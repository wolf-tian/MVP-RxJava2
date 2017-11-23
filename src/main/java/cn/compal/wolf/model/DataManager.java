package cn.compal.wolf.model;

import java.util.List;

import cn.compal.wolf.model.http.IHttpHelper;
import cn.compal.wolf.model.http.api.BaseApi;
import cn.compal.wolf.model.http.response.ZqzhHttpResponse;
import cn.compal.wolf.model.prefs.IPreferencesHelper;
import cn.compal.wolf.test.InstructionBooksBean;
import io.reactivex.Flowable;

/**
 * Created by wolf on 2017/11/17.
 */

public class DataManager implements IHttpHelper, IPreferencesHelper
{
    IHttpHelper mHttpHelper;
    IPreferencesHelper mPreferencesHelper;

    public DataManager(IHttpHelper httpHelper, IPreferencesHelper preferencesHelper)
    {
        mHttpHelper = httpHelper;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public  Flowable<ZqzhHttpResponse<List<InstructionBooksBean>>> getZqzhHttpResponse(BaseApi api)
    {
        return mHttpHelper.getZqzhHttpResponse(api);
    }

    @Override
    public Flowable<Object> getZqzhHttpStringResponse(BaseApi api)
    {
        return mHttpHelper.getZqzhHttpStringResponse(api);
    }
}
