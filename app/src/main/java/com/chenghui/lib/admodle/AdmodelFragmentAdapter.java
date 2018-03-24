package com.chenghui.lib.admodle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by 孙勤伟 on 2016/10/22.
 */
public class AdmodelFragmentAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> list;

    public AdmodelFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * 添加fragment数据 和标题数据
     * @param list
     */
    public void setData(ArrayList<Fragment> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return list != null ? list.get(position) : null;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }
}
