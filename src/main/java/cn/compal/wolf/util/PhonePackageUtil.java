package cn.compal.wolf.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author wolf
 * @version 1.0
 * @Description 安卓手机或应用信息工具类
 * @date 2016-8-11 上午10:23:39
 */
public class PhonePackageUtil
{

    /**
     * 获取手机信息-手机品牌
     */
    public static String getMtyb()
    {
        return android.os.Build.BRAND;// 手机品牌
    }

    /**
     * 获取手机信息-手机型号
     */
    public static String getMmtyp()
    {

        return android.os.Build.MODEL; // 手机型号
    }

    /**
     * 获取手机信息-手机Imei
     */
    public static String getImei(Activity activity)
    {
        TelephonyManager tm = (TelephonyManager) activity
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 获取手机信息-手机Imsi
     */
    public static String getImsi(Activity activity)
    {
        TelephonyManager tm = (TelephonyManager) activity
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSubscriberId();
    }

    /**
     * 获取手机信息-手机号码
     */
    public static String getNumer(Activity activity)
    {
        TelephonyManager tm = (TelephonyManager) activity
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getLine1Number();
    }

    /**
     * 获取手机信息-手机运营商
     */
    public static String getServiceName(Activity activity)
    {
        TelephonyManager tm = (TelephonyManager) activity
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimOperatorName();
    }

    /**
     * 获取手机信息
     */
    public static void getPhoneInfo()
    {
        // tvPhoneInfo.setText("品牌: " + mtyb + "\n" + "型号: " + mtype + "\n" +
        // "版本: Android " + android.os.Build.VERSION.RELEASE + "\n" + "IMEI: " +
        // imei
        // + "\n" + "IMSI: " + imsi + "\n" + "手机号码: " + numer + "\n" + "运营�? " +
        // serviceName + "\n");
    }

    /**
     * 获取手机内存大小
     *
     * @return
     */
    private String getTotalMemory(Activity activity)
    {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;
        try
        {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(
                    localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大�?

            arrayOfString = str2.split("\\s+");
            for (String num : arrayOfString)
            {
                Log.i(str2, num + "\t");
            }

            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘�?024转换为Byte
            localBufferedReader.close();

        } catch (IOException e)
        {
        }
        return Formatter.formatFileSize(activity.getBaseContext(),
                initial_memory);// Byte转换为KB或�?MB，内存大小规格化
    }

    /**
     * 获取当前可用内存大小
     *
     * @return
     */
    public static String getAvailMemory(Activity activity)
    {
        ActivityManager am = (ActivityManager) activity
                .getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);
        return Formatter.formatFileSize(activity.getBaseContext(), mi.availMem);
    }

    /**
     * 获取手机CPU信息
     *
     * @return
     */
    public static String[] getCpuInfo()
    {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = {"", ""};
        String[] arrayOfString;
        try
        {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++)
            {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e)
        {
        }
        // tvHardwareInfo.append("CPU型号 " + cpuInfo[0] + "\n" + "CPU频率: "+
        // cpuInfo[1] + "\n");
        return cpuInfo;
    }

    /**
     * 获取CPU核数
     *
     * @return
     */
    private int getNumCores()
    {
        // Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter
        {
            @Override
            public boolean accept(File pathname)
            {
                // Check if filename is "cpu", followed by a single digit number
                if (Pattern.matches("cpu[0-9]", pathname.getName()))
                {
                    return true;
                }
                return false;
            }
        }

        try
        {
            // Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            // Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            // Return the number of cores (virtual CPU devices)
            return files.length;
        } catch (Exception e)
        {
            e.printStackTrace();
            // Default to return 1 core
            return 1;
        }
    }

    /**
     * 获取当前安装应用列表
     *
     * @return
     */
    public List<PackageInfo> getPackageInfo(Activity activity)
    {
        PackageManager pm = activity.getPackageManager();
        List<PackageInfo> packageInfos = pm.getInstalledPackages(0);
        /*
         * for(PackageInfo pi:packageInfos) {
		 * if("com.gis".equals(pi.packageName)) { flag = true;
		 * System.out.println("@:"+flag); } }
		 */
        return packageInfos;
    }

    /**
     * 获取当前应用版本信息
     *
     * @return
     */
    public static String getVersionName(Activity activity)
    {
        PackageManager pm = activity.getPackageManager();
        PackageInfo packageInfo;
        try
        {
            packageInfo = pm.getPackageInfo(activity.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (NameNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 检查sim卡状态
     * @param context
     * @return
     */
    public static boolean checkSimState(Context context)
    {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (tm.getSimState() == TelephonyManager.SIM_STATE_ABSENT
                || tm.getSimState() == TelephonyManager.SIM_STATE_UNKNOWN)
        {
            return false;
        }

        return true;
    }

}
