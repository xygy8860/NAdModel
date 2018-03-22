package com.chenghui.lib.admodle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private AdInstalUtils adInstalUtils;
    private AdNativeUtils adNativeUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admodel_activity_main);

        findViewById(R.id.hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });
    }

    private void click() {
        if (adInstalUtils == null) {
            adInstalUtils = new AdInstalUtils(this);
        }
        adInstalUtils.refreshAd(0);

        adNativeUtils = new AdNativeUtils(this, (ViewGroup) findViewById(R.id.layout));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (adInstalUtils != null) {
            adInstalUtils.ondetory();
        }

        if (adNativeUtils != null) {
            adNativeUtils.ondetory();
        }
    }
}
