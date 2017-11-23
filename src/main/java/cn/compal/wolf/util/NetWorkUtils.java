package cn.compal.wolf.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.text.DecimalFormat;

import cn.compal.wolf.app.App;

/**
 * 网络工具类
 * Created by wolf on 2016/12/27.
 */

public class NetWorkUtils
{
    /**
     * 判断某一个Activity页面是否有网络连接
     *
     * @param context 判断的Activity
     * @return
     */
    public static boolean isNetworkConnected(Context context)
    {
        try
        {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null)
            {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected())
                {
                    if (info.getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return false;
    }


    /**
     * 判断是否有网络连接
     *
     * @return
     */
    public static boolean isNetworkConnected()
    {
        try
        {
            ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null)
            {
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();
                if (info != null && info.isConnected())
                {
                    if (info.getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return false;
    }


    /**
     * 判断WIFI网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context)
    {
        if (context != null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null)
            {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断MOBILE网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context)
    {
        if (context != null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null)
            {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 获取当前网络连接的类型信息
     *
     * @param context
     * @return 没有网络-1 移动网络 0 wifi 1
     */
    public static int getConnectedType(Context context)
    {
        if (context != null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable())
            {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }


    private static final int NETWORK_TYPE_UNAVAILABLE = -1;
    // private static final int NETWORK_TYPE_MOBILE = -100;
    private static final int NETWORK_TYPE_WIFI = -101;

    private static final int NETWORK_CLASS_WIFI = -101;
    private static final int NETWORK_CLASS_UNAVAILABLE = -1;
    /**
     * Unknown network class.
     */
    private static final int NETWORK_CLASS_UNKNOWN = 0;
    /**
     * Class of broadly defined "2G" networks.
     */
    private static final int NETWORK_CLASS_2_G = 1;
    /**
     * Class of broadly defined "3G" networks.
     */
    private static final int NETWORK_CLASS_3_G = 2;
    /**
     * Class of broadly defined "4G" networks.
     */
    private static final int NETWORK_CLASS_4_G = 3;

    private static DecimalFormat df = new DecimalFormat("#.##");

    // 适配低版本手机
    /**
     * Network type is unknown
     */
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    /**
     * Current network is GPRS
     */
    public static final int NETWORK_TYPE_GPRS = 1;
    /**
     * Current network is EDGE
     */
    public static final int NETWORK_TYPE_EDGE = 2;
    /**
     * Current network is UMTS
     */
    public static final int NETWORK_TYPE_UMTS = 3;
    /**
     * Current network is CDMA: Either IS95A or IS95B
     */
    public static final int NETWORK_TYPE_CDMA = 4;
    /**
     * Current network is EVDO revision 0
     */
    public static final int NETWORK_TYPE_EVDO_0 = 5;
    /**
     * Current network is EVDO revision A
     */
    public static final int NETWORK_TYPE_EVDO_A = 6;
    /**
     * Current network is 1xRTT
     */
    public static final int NETWORK_TYPE_1xRTT = 7;
    /**
     * Current network is HSDPA
     */
    public static final int NETWORK_TYPE_HSDPA = 8;
    /**
     * Current network is HSUPA
     */
    public static final int NETWORK_TYPE_HSUPA = 9;
    /**
     * Current network is HSPA
     */
    public static final int NETWORK_TYPE_HSPA = 10;
    /**
     * Current network is iDen
     */
    public static final int NETWORK_TYPE_IDEN = 11;
    /**
     * Current network is EVDO revision B
     */
    public static final int NETWORK_TYPE_EVDO_B = 12;
    /**
     * Current network is LTE
     */
    public static final int NETWORK_TYPE_LTE = 13;
    /**
     * Current network is eHRPD
     */
    public static final int NETWORK_TYPE_EHRPD = 14;
    /**
     * Current network is HSPA+
     */
    public static final int NETWORK_TYPE_HSPAP = 15;

    /**
     * 格式化大小
     *
     * @param size
     * @return
     */
    public static String formatSize(long size)
    {
        String unit = "B";
        float len = size;
        if (len > 900)
        {
            len /= 1024f;
            unit = "KB";
        }
        if (len > 900)
        {
            len /= 1024f;
            unit = "MB";
        }
        if (len > 900)
        {
            len /= 1024f;
            unit = "GB";
        }
        if (len > 900)
        {
            len /= 1024f;
            unit = "TB";
        }
        return df.format(len) + unit;
    }

    public static String formatSizeBySecond(long size)
    {
        String unit = "B";
        float len = size;
        if (len > 900)
        {
            len /= 1024f;
            unit = "KB";
        }
        if (len > 900)
        {
            len /= 1024f;
            unit = "MB";
        }
        if (len > 900)
        {
            len /= 1024f;
            unit = "GB";
        }
        if (len > 900)
        {
            len /= 1024f;
            unit = "TB";
        }
        return df.format(len) + unit + "/s";
    }

    public static String format(long size)
    {
        String unit = "B";
        float len = size;
        if (len > 1000)
        {
            len /= 1024f;
            unit = "KB";
            if (len > 1000)
            {
                len /= 1024f;
                unit = "MB";
                if (len > 1000)
                {
                    len /= 1024f;
                    unit = "GB";
                }
            }
        }
        return df.format(len) + "\n" + unit + "/s";
    }


    /**
     * 获取运营商
     *
     * @return
     */
    public static String getProvider(Context context)
    {
        String provider = "未知";
        try
        {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String IMSI = telephonyManager.getSubscriberId();
            Log.v("tag", "getProvider.IMSI:" + IMSI);
            if (IMSI == null)
            {
                if (TelephonyManager.SIM_STATE_READY == telephonyManager
                        .getSimState())
                {
                    String operator = telephonyManager.getSimOperator();
                    Log.v("tag", "getProvider.operator:" + operator);
                    if (operator != null)
                    {
                        if (operator.equals("46000")
                                || operator.equals("46002")
                                || operator.equals("46007"))
                        {
                            provider = "中国移动";
                        } else if (operator.equals("46001"))
                        {
                            provider = "中国联通";
                        } else if (operator.equals("46003"))
                        {
                            provider = "中国电信";
                        }
                    }
                }
            } else
            {

                if (IMSI.startsWith("46000") || IMSI.startsWith("46002")
                        || IMSI.startsWith("46007"))
                {
                    provider = "中国移动";
                } else if (IMSI.startsWith("46001"))
                {
                    provider = "中国联通";
                } else if (IMSI.startsWith("46003"))
                {
                    provider = "中国电信";
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return provider;
    }

    /**
     * 获取当前的网络类型 ：没有网络-1 2G网络2 3G网络3 4G网络4 WIFI网络6
     *
     * @param context
     * @return
     */
    public static int getCurrentNetworkType(Context context)
    {
        int networkClass = getNetworkClass(context);
        int type = -1;
        switch (networkClass)
        {
            case NETWORK_CLASS_UNAVAILABLE:
                type = -1;
                break;
            case NETWORK_CLASS_WIFI:
                type = 6;
                break;
            case NETWORK_CLASS_2_G:
                type = 2;
                break;
            case NETWORK_CLASS_3_G:
                type = 3;
                break;
            case NETWORK_CLASS_4_G:
                type = 4;
                break;
            case NETWORK_CLASS_UNKNOWN:
                type = -1;
                break;
        }
        return type;
    }

    /**
     * 获取当前的网络状态
     *
     * @param context
     * @return
     */

    public static String getCurrentNetworkStatus(Context context)
    {
        int type = getCurrentNetworkType(context);
        String status = "未知网络";
        //没有网络-1 2G网络2 3G网络3 4G网络4 WIFI网络6
        switch (type)
        {
            case -1:
                status = "没有网络";
                break;
            case 2:
                status = "2G网络";
                break;
            case 3:
                status = "3G网络";
                break;
            case 4:
                status = "4G网络";
                break;
            case 6:
                status = "wifi 网络";
                break;
            default:
                status = "未知网络";
                break;
        }
        return status;

    }

    private static int getNetworkClassByType(int networkType)
    {
        switch (networkType)
        {
            case NETWORK_TYPE_UNAVAILABLE:
                return NETWORK_CLASS_UNAVAILABLE;
            case NETWORK_TYPE_WIFI:
                return NETWORK_CLASS_WIFI;
            case NETWORK_TYPE_GPRS:
            case NETWORK_TYPE_EDGE:
            case NETWORK_TYPE_CDMA:
            case NETWORK_TYPE_1xRTT:
            case NETWORK_TYPE_IDEN:
                return NETWORK_CLASS_2_G;
            case NETWORK_TYPE_UMTS:
            case NETWORK_TYPE_EVDO_0:
            case NETWORK_TYPE_EVDO_A:
            case NETWORK_TYPE_HSDPA:
            case NETWORK_TYPE_HSUPA:
            case NETWORK_TYPE_HSPA:
            case NETWORK_TYPE_EVDO_B:
            case NETWORK_TYPE_EHRPD:
            case NETWORK_TYPE_HSPAP:
                return NETWORK_CLASS_3_G;
            case NETWORK_TYPE_LTE:
                return NETWORK_CLASS_4_G;
            default:
                return NETWORK_CLASS_UNKNOWN;
        }
    }

    private static int getNetworkClass(Context context)
    {
        int networkType = NETWORK_TYPE_UNKNOWN;
        try
        {
            final NetworkInfo network = ((ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE))
                    .getActiveNetworkInfo();
            if (network != null && network.isAvailable()
                    && network.isConnected())
            {
                int type = network.getType();
                if (type == ConnectivityManager.TYPE_WIFI)
                {
                    networkType = NETWORK_TYPE_WIFI;
                } else if (type == ConnectivityManager.TYPE_MOBILE)
                {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(
                            Context.TELEPHONY_SERVICE);
                    networkType = telephonyManager.getNetworkType();
                }
            } else
            {
                networkType = NETWORK_TYPE_UNAVAILABLE;
            }

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return getNetworkClassByType(networkType);

    }
}
