package cn.compal.wolf.app;

import java.io.File;

/**
 * Created by wolf on 2017/11/14.
 */

public class Constants
{
    /*基础url*/
    public static String baseUrl = "http://css.telecomjs.com/";


    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
}
