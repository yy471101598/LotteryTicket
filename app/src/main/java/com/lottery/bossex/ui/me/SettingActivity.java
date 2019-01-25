package com.lottery.bossex.ui.me;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.lottery.bossex.R;
import com.lottery.bossex.ui.BaseActivity;
import com.lottery.bossex.views.SlipButton;

public class SettingActivity extends BaseActivity {
    @Bind(R.id.img_left)
    ImageView mImgLeft;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.rl_right)
    RelativeLayout mRlRight;
    @Bind(R.id.rl_password)
    RelativeLayout mRlPassword;
    @Bind(R.id.language)
    RelativeLayout mLanguage;
    @Bind(R.id.slipbutton)
    SlipButton mSlipbutton;
    @Bind(R.id.rl_safety)
    RelativeLayout mRlSafety;
    @Bind(R.id.rl_newversion)
    RelativeLayout mRlNewversion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        mSlipbutton.SetOnChangedListener(new SlipButton.OnChangedListener() {
            @Override
            public void OnChanged(boolean CheckState) {
            }
        });
    }

    @OnClick({R.id.rl_left, R.id.rl_password, R.id.language, R.id.rl_newversion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_left:
                finish();
                break;
            case R.id.rl_password:
                break;
            case R.id.language:
                break;
            case R.id.rl_newversion:
                break;
        }
    }
}
