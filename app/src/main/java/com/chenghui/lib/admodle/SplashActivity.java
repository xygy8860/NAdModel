package com.chenghui.lib.admodle;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by cdsunqinwei on 2018/3/20.
 */

public class SplashActivity extends SplashBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.admodel_layout_splash);
    }

    @Override
    protected void initAdParams() {
        splashLayout = (ViewGroup) findViewById(R.id.splashview);
        mJumpBtn = (TextView) findViewById(R.id.jump_btn);
    }

    @Override
    protected void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

