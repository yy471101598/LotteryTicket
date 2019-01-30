package com.lottery.bossex.ui.me;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lottery.bossex.R;
import com.lottery.bossex.tools.NoDoubleClickListener;
import com.lottery.bossex.ui.BaseActivity;
import com.lottery.bossex.views.RoundImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PersonalActivity extends BaseActivity {
    @Bind(R.id.img_left)
    ImageView mImgLeft;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.rl_right)
    RelativeLayout mRlRight;
    @Bind(R.id.img_icon)
    RoundImageView mImgIcon;
    @Bind(R.id.et_nicheng)
    EditText mEtNicheng;
    @Bind(R.id.et_account)
    EditText mEtAccount;
    @Bind(R.id.rl_submit)
    RelativeLayout mRlSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinfo);
        ButterKnife.bind(this);
        initEvent();
    }

    private void initEvent() {
        mRlLeft.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                finish();
            }
        });

        mRlSubmit.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {

            }
        });
    }
}
