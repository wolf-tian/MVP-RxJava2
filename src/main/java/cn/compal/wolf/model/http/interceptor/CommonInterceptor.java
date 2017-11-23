package cn.compal.wolf.model.http.interceptor;

import java.io.IOException;

import cn.compal.wolf.app.App;
import cn.compal.wolf.util.PhonePackageUtil;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wolf on 2016/12/13.
 */

public class CommonInterceptor  implements Interceptor
{
    public Response intercept(Chain chain) throws IOException
    {
        Request oldRequest = chain.request();

        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host())
                .addQueryParameter("tType", "android")
                .addQueryParameter("sType", "Android")
                .addQueryParameter("sysVersion", App.version)
                .addQueryParameter("terminalType", "android")
                .addQueryParameter("mType", PhonePackageUtil.getMmtyp())
                .addQueryParameter("terminalType", "android");

        // 新的请求
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build();

        return chain.proceed(newRequest);
    }
}
