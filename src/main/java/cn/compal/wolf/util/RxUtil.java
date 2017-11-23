package cn.compal.wolf.util;

import cn.compal.wolf.model.http.exception.ApiException;
import cn.compal.wolf.model.http.response.ZqzhHttpResponse;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wolf on 2016/11/2.
 */

public class RxUtil
{
    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper()
    {    //compose简化线程
        return new FlowableTransformer<T, T>()
        {
            @Override
            public Flowable<T> apply(Flowable<T> observable)
            {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

   /**
     * 生成Flowable
     *
     * @param
     * @return
     */
    public static Flowable<Object> createData(final Object t)
    {
        /*return Flowable.create(new FlowableOnSubscribe<List<T>>()
        {
            @Override
            public void subscribe(FlowableEmitter<List<T>> emitter) throws Exception
            {
                try
                {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e)
                {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);*/
        return  Flowable.create(new FlowableOnSubscribe<Object>()
        {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Object> emitter) throws Exception
            {
                try
                {
                    emitter.onNext(t);
                    emitter.onComplete();
                }
                catch (Exception e)
                {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }


    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<ZqzhHttpResponse<T>, T> handleResult()
    {   //compose判断结果
        return new FlowableTransformer<ZqzhHttpResponse<T>, T>()
        {
            @Override
            public Flowable<T> apply(Flowable<ZqzhHttpResponse<T>> httpResponseFlowable)
            {
                return httpResponseFlowable.flatMap(new Function<ZqzhHttpResponse<T>, Flowable<T>>()
                {
                    @Override
                    public Flowable<T> apply(ZqzhHttpResponse<T> response)
                    {
                        if ("0".equals(response.getRetCode()))
                        {
                            //return createData(response.getData());
                            return Flowable.error(new ApiException("服务器返回"));
                        } else
                        {
                            return Flowable.error(new ApiException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }


    /**
     * 统一返回结果处理
     *
     * @return
     */
    public static FlowableTransformer<Object, Object> handleStringResult()
    {   //compose判断结果
        return new FlowableTransformer<Object, Object>()
        {
            @Override
            public Flowable<Object> apply(@NonNull Flowable<Object> httpResponseFlowable)
            {
                return httpResponseFlowable.flatMap(new Function<Object, Flowable<Object>>()
                {
                    @Override
                    public Flowable<Object> apply(@NonNull Object s) throws Exception
                    {
                        return createData(s);
                    }
                });
            }
            /*@Override
            public Flowable<String> apply(@NonNull Flowable<T> httpResponseFlowable)
            {
                return httpResponseFlowable.flatMap(new Function<T, Flowable<T>>()
                {
                    @Override
                    public Flowable<T> apply(@NonNull T s) throws Exception
                    {
                        return createData(s);
                    }
                });
            }*/

        };
    }

}
