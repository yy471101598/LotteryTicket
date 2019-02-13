package com.lottery.bossex.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lottery.bossex.R;
import com.lottery.bossex.tools.ActivityStack;
import com.lottery.bossex.tools.NoDoubleClickListener;
import com.lottery.bossex.ui.BaseActivity;
import com.lottery.bossex.ui.HostActivity;
import com.lottery.bossex.ui.LoginActivity;
import com.lottery.bossex.ui.me.order.OrderDetailActivity;
import com.lottery.bossex.views.RoundImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MeActivity extends BaseActivity {
    @Bind(R.id.img_icon)
    RoundImageView mImgIcon;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_yue)
    TextView mTvYue;
    @Bind(R.id.rl_personal)
    RelativeLayout mRlPersonal;
    @Bind(R.id.rl_recharge)
    RelativeLayout mRlRecharge;
    @Bind(R.id.rl_tixian)
    RelativeLayout mRlTixian;
    @Bind(R.id.rl_renzheng)
    RelativeLayout mRlRenzheng;
    @Bind(R.id.rl_orderdetail)
    RelativeLayout mRlOrderdetail;
    @Bind(R.id.rl_frend)
    RelativeLayout mRlFrend;
    @Bind(R.id.rl_setting)
    RelativeLayout mRlSetting;
    @Bind(R.id.rl_help)
    RelativeLayout mRlHelp;
    @Bind(R.id.rl_obout)
    RelativeLayout mRlObout;
    @Bind(R.id.rl_outlogin)
    RelativeLayout mRlOutlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        ButterKnife.bind(this);
        initEvent();
    }

    private void initEvent() {
        mRlPersonal.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(ac, PersonalActivity.class);
                startActivity(intent);
            }
        });
        mRlRecharge.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(ac, RechargeActivity.class);
                startActivity(intent);
            }
        });
        mRlTixian.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(ac, TixianActivity.class);
                startActivity(intent);
            }
        });
        mRlRenzheng.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(ac, ShimingRenzhengActivity.class);
                startActivity(intent);
            }
        });
        mRlOrderdetail.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(ac, OrderDetailActivity.class);
                startActivity(intent);
            }
        });
        mRlFrend.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(ac, FrendActivity.class);
                startActivity(intent);
            }
        });
        mRlSetting.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(ac, SettingActivity.class);
                startActivity(intent);
            }
        });
        mRlHelp.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(ac, HelpActivity.class);
                startActivity(intent);
            }
        });
        mRlObout.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(ac, SettingActivity.class);
                startActivity(intent);
            }
        });

        mRlOutlogin.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                ActivityStack.create().finishActivity(HostActivity.class);
                Intent intent = new Intent(ac, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
