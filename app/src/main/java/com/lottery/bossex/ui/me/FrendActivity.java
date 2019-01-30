package com.lottery.bossex.ui.me;

import android.os.Bundle;

import com.lottery.bossex.ui.BaseActivity;

import butterknife.ButterKnife;

public class FrendActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initEvent();
    }

    private void initEvent() {
    }
}
