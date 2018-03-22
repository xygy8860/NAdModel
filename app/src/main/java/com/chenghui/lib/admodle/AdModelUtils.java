package com.chenghui.lib.admodle;

/**
 * Created by cdsunqinwei on 2018/3/21.
 */

public class AdModelUtils {
    // 测试id
    /*public static String APPID = "1106414865";
    public static String SplashID = "4050220679022649";
    public static String NativeId_Img = "5080737128844271"; // 纯图片 竖图
    public static String NativeId_txt_img = "2040938139636078"; // 上文下图
    public static String NativeId_img_txt = "9050034189232009"; // 上图下文  1080037139439140
    public static String NativeId_leftImg_rightTxt = "9050034189232009"; // 左图右文*/

    public static String APPID = "";
    public static String SplashID = "";
    public static String NativeId_Img = ""; // 纯图片 竖图
    public static String NativeId_txt_img = ""; // 上文下图
    public static String NativeId_img_txt = ""; // 上图下文  1080037139439140
    public static String NativeId_leftImg_rightTxt = ""; // 左图右文

    public static String BannerPosID = "";
    public static String InstalPosID = "";

    public static int mRand = 95; // 控制点击几率
    public static boolean isSplashFirst = true; // true:开屏优先  false:原生优先

    //    public static String SDK_KEY = "SDK20170014120225l4da2ffsad5b8vv";
    public static String SDK_KEY = "";

    public static void init(String appid, String splashID, String nativeId, String bannerPosID, String instalPosID, String adviewKey) {
        APPID = appid;
        SplashID = splashID;
        NativeId_Img = nativeId;
        BannerPosID = bannerPosID;
        InstalPosID = instalPosID;
        SDK_KEY = adviewKey;
    }

}
