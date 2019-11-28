package com.chenghui.lib.admodle;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by cdsunqinwei on 2018/3/21.
 */

public class AdModelUtils {

    /**
     * 需要进行检测的权限数组
     */
    protected static String[] needPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    public static void initTTAd(Context context) {

    }

    // 判断是否有权限，没有权限直接TT
    public static boolean isHavePermissions(Activity activity) {
        try {
            for (String perm : needPermissions) {
                // 如果用没有授权
                if (ContextCompat.checkSelfPermission(activity, perm) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }

            return true;
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return false;
    }


    // 横幅广告控制几率
    public static int TT_Banner_rate = 0;
    public static int GDT_Banner_rate = 100;

    // 开屏广告控制几率 默认头条100%
    public static int TT_Splash_rate = 0;
    public static int GDT_Splash_rate = 100;

    // 原生广告头条比例 默认为0  序列1 分配广点通和头条
    public static int TT_Native_rate = 0;
    public static int TT_Native_model_rate = 100; // 序列2：头条原生中个性化模板混合的比例  其余为视频

    public static String APPID = "";
    public static String SplashID = "";
    public static String NativeId_Img = ""; // 纯图片 竖图
    public static String NativeId_txt_img = ""; // 上文下图
    public static String NativeId_img_txt = ""; // 上图下文  1080037139439140
    public static String NativeId_leftImg_rightTxt = ""; // 左图右文
    public static String NativeId_Horizontal_Img = ""; // 纯图片 横图
    public static String BannerPosID_2 = ""; // 2.0banner
    public static String InstalPosID = "";

    public static int mRand = 95; // 控制点击几率

    // 以下头条广告配置
    public static String TT_Appid = "";
    public static String TT_Splash_id = ""; // 开屏广告id
    public static String TT_Banner_id = "";
    public static String TT_Name = "";
    public static String TT_native_video_id = ""; // 原生 个性化模板 视频
    public static String TT_native_id = ""; // 原生 个性化模板 混合

}
