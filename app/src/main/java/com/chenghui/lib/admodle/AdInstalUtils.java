package com.chenghui.lib.admodle;

import android.app.Activity;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by cdsunqinwei on 2018/3/21.
 */

public class AdInstalUtils {

    private static final String TAG = "123";

    private int count;
    private InstlDialog dialog;
    private Activity activity;

    private String nativeId;
    private boolean isShowClosedBtn;
    private int mRand = 0;

    private boolean isVertical;
    private InstalCarouselDialog mCarouselDialog;
    private OnLoadAdListener listener;

    // 横屏
    public AdInstalUtils(Activity activity, int mRand, OnLoadAdListener listener) {
        this(activity);
        this.mRand = mRand;
        this.listener = listener;
    }

    //竖屏
    public AdInstalUtils(Activity activity, String nativeId, int mRand, OnLoadAdListener listener) {
        this.activity = activity;
        this.nativeId = nativeId;
        this.listener = listener;
        this.mRand = mRand;
        isVertical = true;

        if (nativeId.equals(AdModelUtils.NativeId_Img) || nativeId.equals(AdModelUtils.NativeId_Horizontal_Img)) {
            isShowClosedBtn = true;
        }
    }

    private AdInstalUtils(Activity activity) {
        this.activity = activity;

        ArrayList<String> list = new ArrayList<>();
        /*if (!TextUtils.isEmpty(AdModelUtils.NativeId_Img) && !"1".equals(AdModelUtils.NativeId_Img)) {
            list.add(AdModelUtils.NativeId_Img);
        }*/
        if (!TextUtils.isEmpty(AdModelUtils.NativeId_img_txt) && !"1".equals(AdModelUtils.NativeId_img_txt)) {
            list.add(AdModelUtils.NativeId_img_txt);
        }
        if (!TextUtils.isEmpty(AdModelUtils.NativeId_txt_img) && !"1".equals(AdModelUtils.NativeId_txt_img)) {
            list.add(AdModelUtils.NativeId_txt_img);
        }
        if (!TextUtils.isEmpty(AdModelUtils.NativeId_Horizontal_Img) && !"1".equals(AdModelUtils.NativeId_Horizontal_Img)) {
            list.add(AdModelUtils.NativeId_Horizontal_Img);
        }
        int i = new Random().nextInt(list.size());
        nativeId = list.get(i);

        if (nativeId.equals(AdModelUtils.NativeId_Img) || nativeId.equals(AdModelUtils.NativeId_Horizontal_Img)) {
            isShowClosedBtn = true;
        }
    }

    public void refreshAd(int count) {
        this.count = count;
    }


    public void ondetory() {
        if (dialog != null) {
            dialog.dismiss();
        }

        if (mCarouselDialog != null) {
            mCarouselDialog.dismiss();
        }
    }

    public interface OnLoadAdListener {
        void successed();

        void failed();

        void closed();
    }

}
