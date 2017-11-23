package cn.compal.wolf.di.module;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import cn.compal.wolf.BuildConfig;
import cn.compal.wolf.app.Constants;
import cn.compal.wolf.di.qualifier.ZqzhUrl;
import cn.compal.wolf.model.http.interceptor.CommonInterceptor;
import cn.compal.wolf.model.http.interceptor.LogInterceptor;
import cn.compal.wolf.model.http.service.ZqzhHttpService;
import cn.compal.wolf.util.NetWorkUtils;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Flowable;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by codeest on 2017/2/26.
 */

@Module
public class HttpModule
{

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder()
    {
        return new Retrofit.Builder();
    }


    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder()
    {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    @ZqzhUrl
    Retrofit provideZhihuRetrofit(Retrofit.Builder builder, OkHttpClient client)
    {
        return createRetrofit(builder, client, ZqzhHttpService.HOST);
    }


    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder)
    {
        /**
         *  公共参数拦截器
         */
        CommonInterceptor interceptor = new CommonInterceptor();
        builder.addInterceptor(interceptor);
        if (BuildConfig.DEBUG)
        {
            /**
             *  日志拦截器
             */
            builder.addInterceptor(new LogInterceptor());
        }
        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor()
        {
            @Override
            public Response intercept(Chain chain) throws IOException
            {
                Request request = chain.request();
                if (!NetWorkUtils.isNetworkConnected())
                {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetWorkUtils.isNetworkConnected())
                {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else
                {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };

        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor)
                .addInterceptor(cacheInterceptor)
                .cache(cache)
                //设置超时
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                //错误重连
                .retryOnConnectionFailure(true);

        return builder.build();
    }

    @Singleton
    @Provides
    ZqzhHttpService provideZhihuService(@ZqzhUrl Retrofit retrofit)
    {
        return retrofit.create(ZqzhHttpService.class);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url)
    {
        /*创建retrofit对象*/
        return builder
                .baseUrl(url)
                .client(client)
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
