package com.lottery.bossex.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lottery.bossex.R;
import com.lottery.bossex.tools.NoDoubleClickListener;
import com.lottery.bossex.tools.ShadowUtils;
import com.lottery.bossex.ui.BaseActivity;
import com.lottery.bossex.views.SlipButton;

import butterknife.Bind;
import butterknife.ButterKnife;

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
    @Bind(R.id.view_line)
    View mViewLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        ShadowUtils.setShadowBackgroud(ac, res, mViewLine);
        mSlipbutton.SetOnChangedListener(new SlipButton.OnChangedListener() {
            @Override
            public void OnChanged(boolean CheckState) {
            }
        });
        initEvent();
    }

    private void initEvent() {
        mRlLeft.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                finish();
            }
        });
        mRlPassword.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent=new Intent(ac,ChangePwdActivity.class);
                startActivity(intent);
            }
        });
        mLanguage.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent=new Intent(ac,LanguageActivity.class);
                startActivity(intent);
            }
        });
    }
}
