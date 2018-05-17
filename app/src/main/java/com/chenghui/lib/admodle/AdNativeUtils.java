package com.chenghui.lib.admodle;

import android.app.Activity;
import android.text.TextUtils;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by cdsunqinwei on 2018/3/22.
 */

public class AdNativeUtils {

    private static final String TAG = "123";
    private int count;
    private Activity activity;
    private String nativeId;
    private ViewGroup layout;

    private OnSuccessListener listener;

    public AdNativeUtils(Activity activity, ViewGroup layout) {
        this.activity = activity;
        this.layout = layout;
        ArrayList<String> list = new ArrayList<>();
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
        refreshAd(0);
    }

    public AdNativeUtils(Activity activity, ViewGroup layout, OnSuccessListener listener) {
        this.activity = activity;
        this.layout = layout;
        this.listener = listener;

        nativeId = AdModelUtils.NativeId_Horizontal_Img;
        refreshAd(0);
    }

    private void refreshAd(int count) {
        this.count = count;
    }


    public void ondetory() {
    }

    public interface OnSuccessListener {
        void success();
    }

}
