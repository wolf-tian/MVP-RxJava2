package cn.compal.wolf.model.http.api;


import java.util.Map;

import cn.compal.wolf.app.Constants;
import cn.compal.wolf.util.StringUtil;
import io.reactivex.Flowable;
import retrofit2.Retrofit;

/**
 * 请求数据统一封装类
 * Created by wolf on 2017/3/7.
 */

public class BaseApi
{
    /*基础url*/
    private String baseUrl = Constants.baseUrl;
    /*是否需要缓存处理*/
    private boolean cache = false;
    /*方法名*/
    private String mothed;
    /*超时时间-默认6秒*/
    private int connectionTime = 6;
    /*有网情况下的本地缓存时间默认60秒*/
    private int cookieNetWorkTime = 60;
    /*无网络的情况下本地缓存时间默认30天*/
    private int cookieNoNetWorkTime = 24 * 60 * 60 * 30;

    /*是否强制刷新处理*/
    private boolean forceRefresh = true;

    private Map<String, String> parameters;

    /*是否显示加载框*/
    private boolean showProgress = true;

    /*方法模块*/
    private String module;

    public int getCookieNoNetWorkTime()
    {
        return cookieNoNetWorkTime;
    }

    public void setCookieNoNetWorkTime(int cookieNoNetWorkTime)
    {
        this.cookieNoNetWorkTime = cookieNoNetWorkTime;
    }

    public int getCookieNetWorkTime()
    {
        return cookieNetWorkTime;
    }

    public void setCookieNetWorkTime(int cookieNetWorkTime)
    {
        this.cookieNetWorkTime = cookieNetWorkTime;
    }

    public String getMothed()
    {
        return mothed;
    }

    public int getConnectionTime()
    {
        return connectionTime;
    }

    public void setConnectionTime(int connectionTime)
    {
        this.connectionTime = connectionTime;
    }

    public void setMothed(String mothed)
    {
        this.mothed = mothed;
    }

    public String getBaseUrl()
    {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl)
    {
        this.baseUrl = baseUrl;
    }

    public String getUrl()
    {
        if (StringUtil.isEmpty(mothed))
        {
            return baseUrl;
        } else
        {
            if (null == getParameters())
            {
                return baseUrl + mothed;
            } else
            {
                return baseUrl + mothed + StringUtil.transMapToString(getParameters());
            }
        }

    }

    public boolean isCache()
    {
        return cache;
    }

    public void setCache(boolean cache)
    {
        this.cache = cache;
    }

    public boolean isForceRefresh()
    {
        return forceRefresh;
    }

    public void setForceRefresh(boolean forceRefresh)
    {
        this.forceRefresh = forceRefresh;
    }

    public Map<String, String> getParameters()
    {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters)
    {
        this.parameters = parameters;
    }

    public boolean isShowProgress()
    {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress)
    {
        this.showProgress = showProgress;
    }

    public String getModule()
    {
        return module;
    }

    public void setModule(String module)
    {
        this.module = module;
    }
}
