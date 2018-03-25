package com.chenghui.admodel.test;

import android.support.v4.app.Fragment;

import com.chenghui.lib.admodle.AdCarouselFragment;
import com.chenghui.lib.admodle.AdNativeFragment;

import java.util.ArrayList;

/**
 * Created by xxx on 2018/3/24.
 */

public class AdFragment extends AdCarouselFragment {
    @Override
    public ArrayList<Fragment> initData() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new TestFragment());
        list.add(AdNativeFragment.initAdNativeFragment(1));
        list.add(AdNativeFragment.initAdNativeFragment(2));
        return list;
    }
}
