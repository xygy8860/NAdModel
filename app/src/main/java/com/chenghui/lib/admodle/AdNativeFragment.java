package com.chenghui.lib.admodle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by xxx on 2018/3/24.
 */

public class AdNativeFragment extends Fragment {

    public static final String TAG_POSITION = "position";

    public static AdNativeFragment initAdNativeFragment(int position) {
        AdNativeFragment fragment = new AdNativeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TAG_POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    private View view;
    private AdNativeUtils adNativeUtils;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.admodel_native_fragment, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (view instanceof ViewGroup) {
            if (adNativeUtils != null) {
                adNativeUtils.ondetory();
            }
            adNativeUtils = new AdNativeUtils(getActivity(), (ViewGroup) view, new AdNativeUtils.OnSuccessListener() {
                @Override
                public void success() {

                    int position = 1;
                    if (getArguments() != null) {
                        position = getArguments().getInt("position", 1);
                    }

                    EventBus.getDefault().post(new AdCarouselFragment.EntityPager(position));
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        if (adNativeUtils != null) {
            adNativeUtils.ondetory();
        }

        super.onDestroyView();
    }
}
