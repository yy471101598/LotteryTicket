package com.lottery.bossex.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lottery.bossex.R;
import com.lottery.bossex.tools.ActivityStack;
import com.lottery.bossex.tools.NoDoubleClickListener;
import com.lottery.bossex.ui.HostActivity;
import com.lottery.bossex.ui.LoginActivity;
import com.lottery.bossex.ui.me.order.OrderDetailActivity;
import com.lottery.bossex.views.RoundImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MeFragment extends Fragment {
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_me, null);
        ButterKnife.bind(this, view);
        initEvent();
        return view;
    }


    private void initEvent() {
        mRlPersonal.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(getActivity(), PersonalActivity.class);
                startActivity(intent);
            }
        });
        mRlRecharge.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(getActivity(), RechargeActivity.class);
                startActivity(intent);
            }
        });
        mRlTixian.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(getActivity(), TixianActivity.class);
                startActivity(intent);
            }
        });
        mRlRenzheng.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(getActivity(), ShimingRenzhengActivity.class);
                startActivity(intent);
            }
        });
        mRlOrderdetail.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                startActivity(intent);
            }
        });
        mRlFrend.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(getActivity(), FrendActivity.class);
                startActivity(intent);
            }
        });
        mRlSetting.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
        mRlHelp.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
            }
        });
        mRlObout.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });

        mRlOutlogin.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                ActivityStack.create().finishActivity(HostActivity.class);
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
