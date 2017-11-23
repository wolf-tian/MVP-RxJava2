package cn.compal.wolf.util;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by wolf on 2017/3/8.
 */

public class StringUtil
{
    public static boolean isEmpty(String str)
    {
        return str == null || "".equals(str.trim());
    }

    /**
     * Check whether a String is NotEmpty.
     *
     * @param str the String to be checked.
     * @return boolean if it is empty(null or empty sstring) then reurn true.
     */
    public static boolean isNotEmpty(String str)
    {
        return str != null && (!"".equals(str.trim()));
    }

    public static String trim(String str)
    {
        return str == null ? "" : str.trim();
    }

    public static boolean isNotNullOrEmpty(String str)
    {
        return str != null && (!"".equals(str.trim())) && (!"null".equals(str.trim()));
    }

    public static boolean isNullOrEmpty(String str)
    {
        return str == null || "".equals(str.trim()) || "null".equals(str.trim());
    }

    /**
     * 方法名称:transMapToString
     * 传入参数:map
     * 返回值:String 形如 username:chenziwen;password:1234
     */
    public static String transMapToString(Map map){
        Map.Entry entry;
        StringBuffer sb = new StringBuffer();
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            entry = (Map.Entry)iterator.next();
            sb.append(entry.getKey().toString()).append( ":" ).append(null==entry.getValue()?"":
                    entry.getValue().toString()).append (iterator.hasNext() ? ";" : "");
        }
        return sb.toString();
    }
}
