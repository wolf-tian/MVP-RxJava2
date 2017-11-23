package cn.compal.wolf.model.http.exception;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

/**
 * retry条件
 * Created by wolf on 2016/10/17.
 */
public class RetryWhenNetworkException implements Function<Flowable<? extends Throwable>, Flowable<?>>
{
    /* retry次数*/
    private int count = 3;
    /*延迟*/
    private long delay = 3000;
    /*叠加延迟*/
    private long increaseDelay = 3000;

    public RetryWhenNetworkException()
    {

    }

    public RetryWhenNetworkException(int count, long delay)
    {
        this.count = count;
        this.delay = delay;
    }

    public RetryWhenNetworkException(int count, long delay, long increaseDelay)
    {
        this.count = count;
        this.delay = delay;
        this.increaseDelay = increaseDelay;
    }

    @Override
    public Flowable<?> apply(@NonNull Flowable<? extends Throwable> flowable) throws Exception
    {
        return flowable
                .zipWith(Flowable.range(1, count + 1), new BiFunction<Throwable, Integer, Wrapper>()
                {
                    @Override
                    public Wrapper apply(Throwable throwable, Integer integer)
                    {
                        return new Wrapper(throwable, integer);
                    }
                }).flatMap(new Function<Wrapper, Flowable<?>>()
                {
                    @Override
                    public Flowable<?> apply(Wrapper wrapper)
                    {
                        if ((wrapper.throwable instanceof ConnectException
                                || wrapper.throwable instanceof SocketTimeoutException
                                || wrapper.throwable instanceof TimeoutException)
                                && wrapper.index < count + 1)
                        { //如果超出重试次数也抛出错误，否则默认是会进入onCompleted
                            return Flowable.timer(delay + (wrapper.index - 1) * increaseDelay, TimeUnit.MILLISECONDS);
                        }
                        return Flowable.error(wrapper.throwable);
                    }
                });
    }


    private class Wrapper
    {
        private int index;
        private Throwable throwable;

        public Wrapper(Throwable throwable, int index)
        {
            this.index = index;
            this.throwable = throwable;
        }
    }

}
