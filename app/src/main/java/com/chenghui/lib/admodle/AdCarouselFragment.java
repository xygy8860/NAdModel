package com.chenghui.lib.admodle;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * Created by xxx on 2018/3/24.
 */

public abstract class AdCarouselFragment extends Fragment {

    protected static final String TAG_IS_CAROUSEL = "isCarousel";

    private View view;
    private ViewPager mViewPager;
    private AdmodelFragmentAdapter mAdapter;
    protected ArrayList<Fragment> data;
    private AdCountTimer timer;
    private LinearLayout mLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.admodel_carousel_fragment, container, false);
            view.getLayoutParams().height = getWidth() * 720 / 1280;
        }
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = (ViewPager) view.findViewById(R.id.admodel_carousel_fragment_viewpager);
        mLayout = (LinearLayout) view.findViewById(R.id.admodel_carousel_fragment_layout);

        mAdapter = new AdmodelFragmentAdapter(getChildFragmentManager());
        data = initData();
        mAdapter.setData(data);
        mViewPager.setAdapter(mAdapter);
        initLayout();
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position > 0) {
                    mViewPager.setOffscreenPageLimit(2);
                }

                changeIndicator(position);

                boolean isCarousel = false;
                if (getArguments() != null) {
                    isCarousel = getArguments().getBoolean(TAG_IS_CAROUSEL, false);
                }

                if (isCarousel) {
                    if (timer == null) {
                        timer = new AdCountTimer(3000, 3000);
                        timer.mViewPager = mViewPager;
                        timer.size = data.size();
                    } else {
                        timer.cancel();
                    }

                    timer.position = position;
                    timer.start();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化指示器
     */
    private void initLayout() {
        for (int i = 0; i < data.size(); i++) {
            ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.admodel_carousel_fragment_img, mLayout, false);
            if (i == 0) {
                imageView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
            mLayout.addView(imageView);
        }
    }

    private void changeIndicator(int position) {
        for (int i = 0; i < mLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) mLayout.getChildAt(i);

            if (position == i) {
                imageView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            } else {
                imageView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setPager(EntityPager pager) {
        mViewPager.setCurrentItem(1);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        if (timer != null) {
            timer.cancel();
        }
        super.onDestroyView();
    }

    public abstract ArrayList<Fragment> initData();

    public static class EntityPager {
        public int position;

        public EntityPager(int position) {
            this.position = position;
        }
    }

    public static class AdCountTimer extends CountDownTimer {

        public int position;
        public ViewPager mViewPager;
        public int size; // 总图片数

        public AdCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            if (position == size - 1) {
                mViewPager.setCurrentItem(0);
            } else {
                mViewPager.setCurrentItem(position + 1);
            }
        }
    }

    protected int getWidth() {
        WindowManager wm = (WindowManager) getContext().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }
}
