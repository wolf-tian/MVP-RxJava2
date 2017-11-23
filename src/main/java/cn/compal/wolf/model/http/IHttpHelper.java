package cn.compal.wolf.model.http;

import java.util.List;

import cn.compal.wolf.model.http.api.BaseApi;
import cn.compal.wolf.model.http.response.ZqzhHttpResponse;
import cn.compal.wolf.test.InstructionBooksBean;
import io.reactivex.Flowable;

/**
 * Created by wolf on 2017/11/16.
 */

public interface IHttpHelper
{
    Flowable<ZqzhHttpResponse<List<InstructionBooksBean>>>  getZqzhHttpResponse(BaseApi api);

    Flowable<Object>  getZqzhHttpStringResponse(BaseApi api);

}
