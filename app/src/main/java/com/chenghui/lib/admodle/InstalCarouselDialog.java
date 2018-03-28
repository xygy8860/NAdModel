package com.chenghui.lib.admodle;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qq.e.ads.nativ.NativeExpressADView;

import java.util.List;
import java.util.Random;

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
    private DialogCarouselAdapter mAdapter;


    private int mRand = 5; // 点击几率
    private AdCarouselFragment.AdCountTimer timer;
    private List<NativeExpressADView> adList;

    public InstalCarouselDialog(Activity context, boolean isShowClosedBtn) {
        this(context, isShowClosedBtn, 5);
    }

    public InstalCarouselDialog(Activity context, boolean isShowClosedBtn, int mRand) {
        this.context = context;
        this.mRand = mRand;

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

        mAdapter = new DialogCarouselAdapter();
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changePostion(position, 2000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void changePostion(int position, int time) {
        Log.e("123", "position:" + position);

        if (position > 0) {
            mViewPager.setOffscreenPageLimit(2);
        }

        changeIndicator(position);

        if (timer == null) {
            timer = new AdCarouselFragment.AdCountTimer(time, time);
            timer.mViewPager = mViewPager;
            timer.size = mAdapter.getCount();
        } else {
            timer.cancel();
        }

        timer.position = position;
        timer.start();
    }

    public void onADExposure() {
        if (mViewPager.getCurrentItem() == 0) {
            changePostion(0, 1100);
        }
    }

    /**
     * 初始化指示器
     */
    private void initLayout() {
        for (int i = 0; i < mAdapter.getCount(); i++) {
            ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(R.layout.admodel_carousel_fragment_img, mLayout, false);
            if (i == 0) {
                imageView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            }
            mLayout.addView(imageView);
        }
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

    public void setAdList(List<NativeExpressADView> adList) {
        this.adList = adList;
        if (mAdapter == null) {
            mAdapter = new DialogCarouselAdapter();
        }

        mAdapter.setAdList(adList);

        initLayout();
        mViewPager.setCurrentItem(0);
    }

    public void setCloseListener() {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void dismiss() {
        if (mViewPager != null && mViewPager.getChildCount() > 0) {
            mViewPager.removeAllViews();
        }

        if (mAdapter != null) {
            mAdapter.ondestory();
        }

        if (timer != null) {
            timer.cancel();
        }

        dialog.dismiss();
    }

    public void show() {
        dialog.show();
    }


    public static class DialogCarouselAdapter extends PagerAdapter {

        List<NativeExpressADView> adList;

        public void setAdList(List<NativeExpressADView> adList) {
            if (this.adList != null) {
                for (NativeExpressADView adView : this.adList) {
                    adView.destroy();
                }
            }

            this.adList = adList;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return adList != null ? adList.size() : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(adList.get(position));
            adList.get(position).render();
            return adList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(adList.get(position));
        }

        public void ondestory() {
            if (adList != null) {
                for (NativeExpressADView adView : adList) {
                    if (adView != null) {
                        adView.destroy();
                    }
                }
            }
        }
    }

}
