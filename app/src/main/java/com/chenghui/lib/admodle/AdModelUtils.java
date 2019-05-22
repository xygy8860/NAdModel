package com.chenghui.lib.admodle;

import android.content.Context;

/**
 * Created by cdsunqinwei on 2018/3/21.
 */

public class AdModelUtils {

    public static void initTTAd(Context context) {

    }

    // 横幅广告控制几率 默认百度100%
    public static int BD_Banner_rate = 100;
    public static int TT_Banner_rate = 0;
    public static int GDT_Banner_rate = 0;

    // 开屏广告控制几率 默认头条100%
    public static int BD_Splash_rate = 0;
    public static int TT_Splash_rate = 0;
    public static int GDT_Splash_rate = 100;

    public static String APPID = "";
    public static String SplashID = "";
    public static String NativeId_Img = ""; // 纯图片 竖图
    public static String NativeId_txt_img = ""; // 上文下图
    public static String NativeId_img_txt = ""; // 上图下文  1080037139439140
    public static String NativeId_leftImg_rightTxt = ""; // 左图右文
    public static String NativeId_Horizontal_Img = ""; // 纯图片 横图

    public static String BannerPosID = "";
    public static String InstalPosID = "";

    public static int mRand = 95; // 控制点击几率
    public static boolean isSplashFirst = true; // true:开屏优先  false:原生优先

    //    public static String SDK_KEY = "SDK20170014120225l4da2ffsad5b8vv";
    public static String SDK_KEY = "";


    // 以下百度广告配置
    public static String BD_Appid = "";
    public static String BD_Banner_id = "";
    public static String BD_Splash_id = "";

    // 以下头条广告配置
    public static String TT_Appid = "";
    public static String TT_Splash_id = ""; // 开屏广告id
    public static String TT_Banner_id = "";
    public static String TT_Name = "";

}
