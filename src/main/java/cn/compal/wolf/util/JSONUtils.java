package cn.compal.wolf.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.compal.wolf.model.http.response.ZqzhHttpResponse;

/**
 * Created by wolf on 2017/11/20.
 */

public class JSONUtils<T>
{
    /**
     * 把JSON对象解析成需要的Bean的list
     * @param object 待解析的JSON对象
     * @param classOfList 解析后的Bean对象
     * @param <T>
     * @return Bean的list
     */
    public static <T> List parserObject2BeanList(Object object, Class<T> classOfList)
    {
        Gson gson = new Gson();
        String myJson = gson.toJson(object);
        ZqzhHttpResponse<List<T>> bean = gson.fromJson(myJson, ZqzhHttpResponse.class);

        List<T> list = new ArrayList<T>();
        String listString = gson.toJson(bean.getData());
        JsonArray array = new JsonParser().parse(listString).getAsJsonArray();
        for (final JsonElement elem : array)
        {
            list.add(new Gson().fromJson(elem, classOfList));
        }
        return list;
    }


    /**
     * 把JSON对象解析成List<Map>
     * @param object 待解析的JSON对象
     * @return 解析后的List<Map>对象
     */
    public static List<Map<String, String>> parserObject2MapList(Object object)
    {
        Gson gson = new Gson();
        String myJson = gson.toJson(object);
        ZqzhHttpResponse<Map<String, String>> bean = gson.fromJson(myJson, ZqzhHttpResponse.class);
        return bean.getData();
    }
}
