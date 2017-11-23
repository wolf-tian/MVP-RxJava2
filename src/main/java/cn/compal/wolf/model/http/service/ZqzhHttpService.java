package cn.compal.wolf.model.http.service;

import java.util.List;
import java.util.Map;

import cn.compal.wolf.model.http.response.ZqzhHttpResponse;
import cn.compal.wolf.test.InstructionBooksBean;
import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by wolf on 2017/11/17.
 */

public interface ZqzhHttpService
{
    String HOST = "http://css.telecomjs.com/mcssAppService-web/";

    /**
     * 上传图片文件单个
     */
    @Multipart
    @POST("{qModle}")
    Flowable<String> uploadImageFile(@Path("qModle") String qModle, @Part MultipartBody.Part file);

    /**
     * 上传图片文件数组
     */
    @Multipart
    @POST("{qModle}")
    Flowable<String> uploadImageFiles(@Path("qModle") String qModle, @Part() List<MultipartBody.Part> parts);


    /**
     * 根据方法名和方法模块获取服务器数据
     **/
    @FormUrlEncoded
    @POST("{qModle}/{qType}")
    <T> Flowable<ZqzhHttpResponse<List<T>>> getRepoDataByModeAndQtype(@Path("qModle") String qModle, @Path("qType") String qType, @FieldMap Map<String, String> options);


    /**
     * 根据方法名和方法模块获取服务器数据
     **/
    @POST("{qModle}/{qType}")
    Flowable<ZqzhHttpResponse<List<InstructionBooksBean>>> getRepoDataByModeAndQtype(@Path("qModle") String qModle, @Path("qType") String qType);

    /**
     * 根据方法名和方法模块获取服务器数据
     **/
    @POST("{qModle}/{qType}")
    Flowable<Object> getStringRepoDataByModeAndQtype(@Path("qModle") String qModle, @Path("qType") String qType);

    /**
     * 根据方法名和方法模块获取服务器数据
     **/
    @POST("{qModle}/{qType}")
    Flowable<Object> getStringRepoDataByModeAndQtype(@Path("qModle") String qModle, @Path("qType") String qType, @FieldMap Map<String, String> options);
}
