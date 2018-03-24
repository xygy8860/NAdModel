package com.chenghui.admodel.test;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.chenghui.lib.admodle.AdModelUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AdModelUtils.APPID = "1105735729";

        AdModelUtils.NativeId_Img = "1070134109570553"; // 纯图片 竖图  7010232119579486
        AdModelUtils.NativeId_Horizontal_Img = "6020535200535013";

        FragmentManager ft = getSupportFragmentManager();
        FragmentTransaction tr = ft.beginTransaction();
        tr.replace(R.id.fragment, new AdFragment());
        tr.commitAllowingStateLoss();
        ft.executePendingTransactions();

    }
}
