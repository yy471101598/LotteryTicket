package com.lottery.bossex.ui.buy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lottery.bossex.R;
import com.lottery.bossex.tools.NoDoubleClickListener;
import com.lottery.bossex.ui.BaseActivity;
import com.lottery.bossex.views.MyListView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JincaiDajiangActivity extends BaseActivity {

    @Bind(R.id.img_left)
    ImageView mImgLeft;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.rl_right)
    RelativeLayout mRlRight;
    @Bind(R.id.li_xiaqi)
    LinearLayout mLiXiaqi;
    @Bind(R.id.tv_time)
    TextView mTvTime;
    @Bind(R.id.li_shangqi)
    LinearLayout mLiShangqi;
    @Bind(R.id.tv_qinum)
    TextView mTvQinum;
    @Bind(R.id.tv_one)
    TextView mTvOne;
    @Bind(R.id.rl_one)
    RelativeLayout mRlOne;
    @Bind(R.id.tv_two)
    TextView mTvTwo;
    @Bind(R.id.rl_two)
    RelativeLayout mRlTwo;
    @Bind(R.id.tv_three)
    TextView mTvThree;
    @Bind(R.id.rl_three)
    RelativeLayout mRlThree;
    @Bind(R.id.tv_four)
    TextView mTvFour;
    @Bind(R.id.rl_four)
    RelativeLayout mRlFour;
    @Bind(R.id.tv_five)
    TextView mTvFive;
    @Bind(R.id.rl_five)
    RelativeLayout mRlFive;
    @Bind(R.id.tv_six)
    TextView mTvSix;
    @Bind(R.id.rl_six)
    RelativeLayout mRlSix;
    @Bind(R.id.viewpager_img)
    SimpleDraweeView mViewpagerImg;
    @Bind(R.id.li_m)
    LinearLayout mLiM;
    @Bind(R.id.rl_buy)
    RelativeLayout mRlBuy;
    @Bind(R.id.mylistview)
    MyListView mMylistview;
    @Bind(R.id.li_bg)
    LinearLayout mLiBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jincaidajiang);
        ButterKnife.bind(this);
        mTvTitle.setText("竞彩大奖");
        initEvent();
    }

    private void initEvent() {
        mRlBuy.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent=new Intent(ac,BuyCaipiaoActivity.class);
                startActivity(intent);
            }
        });
    }
}
