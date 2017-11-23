package cn.compal.wolf.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.util.HashSet;
import java.util.Set;

import cn.compal.wolf.di.component.AppComponent;
import cn.compal.wolf.di.component.DaggerAppComponent;
import cn.compal.wolf.di.module.AppModule;

/**
 * Created by wolf on 2017/11/14.
 */

public class App extends Application
{
    private static App instance;

    private Set<Activity> allActivities;

    public static AppComponent appComponent;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    /**
     * APP版本号
     */
    public static String version;

    public static synchronized App getInstance()
    {
        return instance;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;

        //初始化屏幕宽高
        getScreenSize();
        PackageManager pm = getPackageManager();
        version = getVersionName(pm);

    }

    public void addActivity(Activity act)
    {
        if (allActivities == null)
        {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act)
    {
        if (allActivities != null)
        {
            allActivities.remove(act);
        }
    }

    public void exitApp()
    {
        if (allActivities != null)
        {
            synchronized (allActivities)
            {
                for (Activity act : allActivities)
                {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public void getScreenSize()
    {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT)
        {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    public static AppComponent getAppComponent()
    {
        if (appComponent == null)
        {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .build();
        }
        return appComponent;
    }

    public String getVersionName(PackageManager pm)
    {
        PackageInfo packageInfo;
        String versionName = null;
        try
        {
            packageInfo = pm.getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return versionName;

    }

}
