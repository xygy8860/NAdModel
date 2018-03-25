package com.chenghui.admodel.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.chenghui.lib.admodle.AdCarouselFragment;
import com.chenghui.lib.admodle.AdNativeFragment;

import java.util.ArrayList;

/**
 * Created by xxx on 2018/3/24.
 */

public class AdFragment extends AdCarouselFragment {

    public static AdFragment getAdFragment(boolean isCarousel) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(TAG_IS_CAROUSEL, isCarousel);
        AdFragment fragment = new AdFragment();
        return fragment;
    }

    @Override
    public ArrayList<Fragment> initData() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new TestFragment());
        list.add(AdNativeFragment.initAdNativeFragment(1));
        list.add(AdNativeFragment.initAdNativeFragment(2));
        return list;
    }
}
