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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * Created by xxx on 2018/3/24.
 */

public abstract class AdCarouselFragment extends Fragment {

    private View view;
    private ViewPager mViewPager;
    private AdmodelFragmentAdapter mAdapter;
    protected ArrayList<Fragment> data;
    private AdCountTimer timer;

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
        mAdapter = new AdmodelFragmentAdapter(getChildFragmentManager());
        mAdapter.setData(initData());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (timer == null) {
                    timer = new AdCountTimer(3000, 3000);
                    timer.mViewPager = mViewPager;
                } else {
                    timer.cancel();
                }

                timer.position = position;
                timer.start();
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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

    }

    public static class AdCountTimer extends CountDownTimer {

        public int position;
        public ViewPager mViewPager;

        public AdCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            if (position == 0) {
                mViewPager.setCurrentItem(1);
            } else {
                mViewPager.setCurrentItem(0);
            }
        }
    }

    protected int getWidth() {
        WindowManager wm = (WindowManager) getContext().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }
}
