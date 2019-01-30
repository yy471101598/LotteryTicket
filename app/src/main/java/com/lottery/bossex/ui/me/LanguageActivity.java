package com.lottery.bossex.ui.me;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lottery.bossex.R;
import com.lottery.bossex.tools.NoDoubleClickListener;
import com.lottery.bossex.tools.PreferenceHelper;
import com.lottery.bossex.ui.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LanguageActivity extends BaseActivity {
    @Bind(R.id.img_left)
    ImageView mImgLeft;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.rl_right)
    RelativeLayout mRlRight;
    @Bind(R.id.tv_zh)
    TextView mTvZh;
    @Bind(R.id.img_zhchose)
    ImageView mImgZhchose;
    @Bind(R.id.line_error)
    View mLineError;
    @Bind(R.id.tv_en)
    TextView mTvEn;
    @Bind(R.id.img_enchose)
    ImageView mImgEnchose;
    @Bind(R.id.li_zh)
    RelativeLayout mLiZh;
    @Bind(R.id.rl_en)
    RelativeLayout mRlEn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changelanguage);
        mTvTitle.setText("语言切换");
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
        mRlEn.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                mTvEn.setTextColor(res.getColor(R.color.origef0));
                mImgEnchose.setVisibility(View.VISIBLE);
                mTvZh.setTextColor(res.getColor(R.color.text66));
                mImgZhchose.setVisibility(View.GONE);
                PreferenceHelper.write(ac, "lottery", "lagavage", "zh");
            }
        });
        mLiZh.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                mTvEn.setTextColor(res.getColor(R.color.text66));
                mImgEnchose.setVisibility(View.GONE);
                mTvZh.setTextColor(res.getColor(R.color.origef0));
                mImgZhchose.setVisibility(View.VISIBLE);
                PreferenceHelper.write(ac, "lottery", "lagavage", "en");
            }
        });
    }
}
