package com.chenghui.lib.admodle;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by cdsunqinwei on 2018/3/20.
 */

public abstract class SplashBaseActivity extends Activity {

    private static final String TAG = "123";
    protected boolean canJump = false;

    protected ViewGroup splashLayout; // 必须在子类赋值
    protected TextView mJumpBtn; // 必须在子类赋值

    private Handler handler;
    private TimeRunnable timeRunnable;
    private TimeOutRunnable timeOutRunnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * 初始化参数
     */
    protected void initAdParams() {
        splash();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (canJump) {
            openMainActivity();
        }
        canJump = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        canJump = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (splashLayout != null) {
            splashLayout.removeAllViews();
            splashLayout = null;
        }

        if (handler != null) {
            if (timeRunnable != null) {
                handler.removeCallbacks(timeRunnable);
            }
            if (timeOutRunnable != null) {
                handler.removeCallbacks(timeOutRunnable);
            }
            handler = null;
            timeRunnable = null;
        }
    }

    /**
     * 开屏页一定要禁止用户对返回按钮的控制，否则将可能导致用户手动退出了App而广告无法正常曝光和计费
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    protected void splash() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                openMainActivity();
            }
        }).start();
    }

    protected void next() {
        if (canJump) {
            openMainActivity();
        } else {
            canJump = true;
        }
    }

    /**
     * 跳转到主页面
     */
    protected abstract void openMainActivity();


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    class TimeRunnable implements Runnable {
        private long t = 5000;

        @Override
        public void run() {
            if (t <= 0) {
                next();
            } else {
                mJumpBtn.setText(" " + Math.round(t / 1000) + "跳过 ");
                t = t - 100;
                if (t > 100) {
                    handler.postDelayed(timeRunnable, 100);
                } else {
                    handler.postDelayed(timeRunnable, 10);
                }
            }
        }
    }

    class TimeOutRunnable implements Runnable {

        @Override
        public void run() {
            next();
        }
    }
}
