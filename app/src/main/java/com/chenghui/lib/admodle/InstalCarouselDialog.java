package com.chenghui.lib.admodle;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author xygy
 * @version 2016-3-17 下午8:31:43
 *          类说明:插屏对话框
 */
public class InstalCarouselDialog {

    private Activity context;
    private AlertDialog dialog;

    private LinearLayout mLayout;
    private ImageView close;
    private ViewPager mViewPager;

    private int mRand = 0; // 点击几率
    private AdCarouselFragment.AdCountTimer timer;
    private AdInstalUtils.OnLoadAdListener listener;

    /*public InstalCarouselDialog(Activity context, boolean isShowClosedBtn) {
        this(context, isShowClosedBtn, 0);
    }

    public InstalCarouselDialog(Activity context, boolean isShowClosedBtn, int mRand) {
        this(context, isShowClosedBtn, mRand, null);
    }*/

    public InstalCarouselDialog(Activity context, boolean isShowClosedBtn, int mRand, AdInstalUtils.OnLoadAdListener listener) {
        /*this.context = context;
        this.mRand = mRand;
        this.listener = listener;

        dialog = new AlertDialog.Builder(context, R.style.admodel_dialog).create();
        dialog.show();
        dialog.getWindow().setContentView(R.layout.admodel_carousel_instal);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        close = (ImageView) dialog.findViewById(R.id.admodel_carousel_instal_close);

        mViewPager = (ViewPager) dialog.findViewById(R.id.admodel_carousel_instal_viewpager);
        mLayout = (LinearLayout) dialog.findViewById(R.id.admodel_carousel_instal_layout);

        if (isShowClosedBtn) {
            close.setVisibility(View.VISIBLE);
            if (new Random().nextInt(100) > mRand) {
                setCloseListener();
            }
        }

        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changePostion(position, 3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/
    }

    // 设置定时器 刷新
    private void changePostion(int position, int time) {
        if (position > 0) {
            mViewPager.setOffscreenPageLimit(2);
        }

        changeIndicator(position);

        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        timer = new AdCarouselFragment.AdCountTimer(time, time);
        timer.mViewPager = mViewPager;

        timer.position = position;
        timer.start();
    }


    private void changeIndicator(int position) {
        for (int i = 0; i < mLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) mLayout.getChildAt(i);

            if (position == i) {
                imageView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            } else {
                imageView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            }
        }
    }

    // 设置监听
    public void setCloseListener() {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    // 销毁相关数据
    public void dismiss() {
        if (mViewPager != null && mViewPager.getChildCount() > 0) {
            mViewPager.removeAllViews();
        }

        if (timer != null) {
            timer.cancel();
        }

        if (listener != null) {
            listener.closed();
        }

        dialog.dismiss();
    }

    public void show() {
        dialog.show();
    }


}
