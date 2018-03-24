package com.chenghui.lib.admodle;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.qq.e.ads.cfg.VideoOption;
import com.qq.e.ads.nativ.ADSize;
import com.qq.e.ads.nativ.NativeExpressAD;
import com.qq.e.ads.nativ.NativeExpressADView;
import com.qq.e.comm.util.AdError;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cdsunqinwei on 2018/3/22.
 */

public class AdNativeUtils implements NativeExpressAD.NativeExpressADListener {

    private static final String TAG = "123";

    private NativeExpressAD nativeExpressAD;
    private NativeExpressADView nativeExpressADView;
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

        try {
            /**
             *  如果选择支持视频的模版样式，请使用{@link Constants#NativeExpressSupportVideoPosID}
             */
            nativeExpressAD = new NativeExpressAD(activity, new ADSize(ADSize.FULL_WIDTH, ADSize.AUTO_HEIGHT), AdModelUtils.APPID,
                    nativeId, this); // 这里的Context必须为Activity
            nativeExpressAD.setVideoOption(new VideoOption.Builder()
                    .setAutoPlayPolicy(VideoOption.AutoPlayPolicy.ALWAYS) // 设置什么网络环境下可以自动播放视频
                    .setAutoPlayMuted(true) // 设置自动播放视频时，是否静音
                    .build()); // setVideoOption是可选的，开发者可根据需要选择是否配置
            nativeExpressAD.loadAD(1);
        } catch (Exception e) {

        }
    }

    @Override
    public void onNoAD(AdError adError) {
        //Log.i(TAG, String.format("onNoAD, error code: %d, error msg: %s", adError.getErrorCode(), adError.getErrorMsg()));

        if (count < 2) {
            refreshAd(count + 1);
        }
    }

    @Override
    public void onADLoaded(List<NativeExpressADView> adList) {
        try {
            //Log.i(TAG, "onADLoaded: " + adList.size());
            // 释放前一个展示的NativeExpressADView的资源
            if (nativeExpressADView != null) {
                nativeExpressADView.destroy();
            }

            if (layout.getVisibility() != View.VISIBLE) {
                layout.setVisibility(View.VISIBLE);
            }

            if (layout.getChildCount() > 0) {
                layout.removeAllViews();
            }

            nativeExpressADView = adList.get(0);
            // 广告可见才会产生曝光，否则将无法产生收益。
            layout.addView(nativeExpressADView);
            nativeExpressADView.render();
        } catch (Exception e) {

        }
    }

    @Override
    public void onRenderFail(NativeExpressADView adView) {
        Log.i(TAG, "onRenderFail");
    }

    @Override
    public void onRenderSuccess(NativeExpressADView adView) {
        Log.i(TAG, "onRenderSuccess");
        if (listener != null) {
            listener.success();
        }
    }

    @Override
    public void onADExposure(NativeExpressADView adView) {
        Log.i(TAG, "onADExposure");
    }

    @Override
    public void onADClicked(NativeExpressADView adView) {
        Log.i(TAG, "onADClicked");
    }

    @Override
    public void onADClosed(NativeExpressADView adView) {
        //Log.i(TAG, "onADClosed");
        // 当广告模板中的关闭按钮被点击时，广告将不再展示。NativeExpressADView也会被Destroy，释放资源，不可以再用来展示。
        if (layout != null && layout.getChildCount() > 0) {
            layout.removeAllViews();
            layout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onADLeftApplication(NativeExpressADView adView) {
        //Log.i(TAG, "onADLeftApplication");
    }

    @Override
    public void onADOpenOverlay(NativeExpressADView adView) {
        //Log.i(TAG, "onADOpenOverlay");
    }

    @Override
    public void onADCloseOverlay(NativeExpressADView adView) {
        //Log.i(TAG, "onADCloseOverlay");
    }


    public void ondetory() {
        if (nativeExpressADView != null) {
            nativeExpressADView.destroy();
        }
    }

    public interface OnSuccessListener {
        void success();
    }

}
