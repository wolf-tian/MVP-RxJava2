package cn.compal.wolf.model.http.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wolf on 2017/11/16.
 */

public class ZqzhHttpResponse<T>
{
    @SerializedName("retCode")
    public String retCode;
    @SerializedName("retMessage")
    public String retMessage;
    @SerializedName("data")
    public List<T> data;
    @SerializedName("total")
    public int total;

    public String getRetCode()
    {
        return retCode;
    }

    public void setRetCode(String retCode)
    {
        this.retCode = retCode;
    }

    public String getRetMessage()
    {
        return retMessage;
    }

    public void setRetMessage(String retMessage)
    {
        this.retMessage = retMessage;
    }

    public List<T> getData()
    {
        return data;
    }

    public void setData(List<T> data)
    {
        this.data = data;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }
}
