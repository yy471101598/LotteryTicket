package com.lottery.bossex.ui.me;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lottery.bossex.R;
import com.lottery.bossex.tools.NoDoubleClickListener;
import com.lottery.bossex.ui.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AbountActivity extends BaseActivity {

    @Bind(R.id.img_left)
    ImageView mImgLeft;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.rl_right)
    RelativeLayout mRlRight;
    @Bind(R.id.tv_code)
    TextView mTvCode;
    @Bind(R.id.tv_msg)
    TextView mTvMsg;
    @Bind(R.id.tv_guanwang)
    TextView mTvGuanwang;
    @Bind(R.id.tv_weibo)
    TextView mTvWeibo;
    @Bind(R.id.tv_bottom)
    TextView mTvBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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
    }
}
