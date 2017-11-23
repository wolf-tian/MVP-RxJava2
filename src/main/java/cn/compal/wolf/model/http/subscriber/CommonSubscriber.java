package cn.compal.wolf.model.http.subscriber;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import cn.compal.wolf.base.BaseView;
import cn.compal.wolf.model.http.exception.HttpTimeException;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * Created by wolf on 2017/2/23.
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T>
{
    private BaseView mView;
    private String mErrorMsg;
    private boolean isShowErrorState = true;

    private static final String RedirectException_MSG = "服务器重定向错误";
    private static final String NetWorkUnknownException_MSG = "网络未知类型错误";
    private static final String ServerUnknownException_MSG = "服务器未知类型错误";
    private static final String HttpException_MSG = "Http请求4xx错误";
    private static final String ConnectException_MSG = "Http链接失败";
    private static final String JSONException_MSG = "Json解析失败";
    private static final String UnknownHostException_MSG = "无法解析该域名";

    protected CommonSubscriber(BaseView view)
    {
        this.mView = view;
    }

    protected CommonSubscriber(BaseView view, String errorMsg)
    {
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected CommonSubscriber(BaseView view, boolean isShowErrorState)
    {
        this.mView = view;
        this.isShowErrorState = isShowErrorState;
    }

    protected CommonSubscriber(BaseView view, String errorMsg, boolean isShowErrorState)
    {
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowErrorState = isShowErrorState;
    }

    @Override
    public void onComplete()
    {

    }

    @Override
    public void onError(Throwable e)
    {
        if (mView == null)
        {
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg))
        {
            mView.showErrorMsg(mErrorMsg);
        } else if (e instanceof HttpException)
        {
            /*网络异常*/
            int code = ((HttpException) e).code();
            if (300 <= code && code < 400)
            {
                mView.showErrorMsg(RedirectException_MSG);
            } else if (400 <= code && code < 500)
            {
                mView.showErrorMsg(HttpException_MSG);
            } else if (500 <= code && code < 600)
            {

                mView.showErrorMsg(ServerUnknownException_MSG);
            } else
            {
                mView.showErrorMsg(NetWorkUnknownException_MSG);
            }
        } else if (e instanceof HttpTimeException)
        {
             /*自定义运行时异常*/
            mView.showErrorMsg(e.getMessage());
        } else if (e instanceof ConnectException || e instanceof SocketTimeoutException)
        {
             /*链接异常*/
            mView.showErrorMsg(ConnectException_MSG);
        } else if (e instanceof JSONException || e instanceof ParseException)
        {
             /*json解析异常*/
            mView.showErrorMsg(JSONException_MSG);
        } else if (e instanceof UnknownHostException)
        {
            /*无法解析该域名异常*/
            mView.showErrorMsg(UnknownHostException_MSG);
        } else
        {
            mView.showErrorMsg("未知错误ヽ(≧Д≦)ノ");
            Log.d("--->", e.toString());
        }
        if (isShowErrorState)
        {
            mView.stateError();
        }
    }
}
