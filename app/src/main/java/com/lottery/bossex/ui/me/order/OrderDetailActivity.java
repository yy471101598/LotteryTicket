package com.lottery.bossex.ui.me.order;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lottery.bossex.R;
import com.lottery.bossex.tools.NoDoubleClickListener;
import com.lottery.bossex.tools.ViewPagerAdapter;
import com.lottery.bossex.ui.BaseActivity;
import com.lottery.bossex.views.tablayout.XTabLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrderDetailActivity extends BaseActivity {

    @Bind(R.id.img_left)
    ImageView mImgLeft;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.rl_right)
    RelativeLayout mRlRight;
    @Bind(R.id.tablayout)
    XTabLayout mTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        initEvent();
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mTabLayout.setxTabDisplayNum(3);
        viewPagerAdapter.addItem(new AllOrderFragment(), res.getString(R.string.allorder));
        viewPagerAdapter.addItem(new WinningOrderFragment(), res.getString(R.string.winningorder));
        viewPagerAdapter.addItem(new WaitOrderFragment(), res.getString(R.string.waitorder));
        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(0);
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
