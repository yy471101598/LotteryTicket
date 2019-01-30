package com.lottery.bossex.ui.me;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lottery.bossex.R;
import com.lottery.bossex.dialog.TixianTishiDialog;
import com.lottery.bossex.tools.NoDoubleClickListener;
import com.lottery.bossex.tools.PreferenceHelper;
import com.lottery.bossex.tools.ShadowUtils;
import com.lottery.bossex.tools.ToastUtils;
import com.lottery.bossex.ui.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RechargeActivity extends BaseActivity {

    @Bind(R.id.img_left)
    ImageView mImgLeft;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.rl_right)
    RelativeLayout mRlRight;
    @Bind(R.id.tv_moneyz)
    TextView mTvMoneyz;
    @Bind(R.id.tv_moneyf)
    TextView mTvMoneyf;
    @Bind(R.id.img_tishi)
    ImageView mImgTishi;
    @Bind(R.id.et_txmoney)
    EditText mEtTxmoney;
    @Bind(R.id.iv_w)
    ImageView mIvW;
    @Bind(R.id.iv_wx)
    ImageView mIvWx;
    @Bind(R.id.rl_wx)
    RelativeLayout mRlWx;
    @Bind(R.id.iv_a)
    ImageView mIvA;
    @Bind(R.id.iv_ali)
    ImageView mIvAli;
    @Bind(R.id.rl_ali)
    RelativeLayout mRlAli;
    @Bind(R.id.rl_next)
    RelativeLayout mRlNext;
    @Bind(R.id.li_bg)
    LinearLayout mLiBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
        ShadowUtils.setShadowBackgroud(ac, res, mLiBg);
        PreferenceHelper.write(ac, "lottery", "iswxpay", true);
        initEvent();
        if (PreferenceHelper.readBoolean(ac, "lottery", "txtishi", false)) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    PreferenceHelper.write(ac, "lottery", "txtishi", true);
                    TixianTishiDialog.tishiDialog(RechargeActivity.this, 1);
                }
            }, 500);
        }

        mEtTxmoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    mTvMoneyz.setText("00");
                    mTvMoneyf.setText(".00");
                } else {
                    if (s.toString().contains(".")) {
                        String[] mo = s.toString().split(".");
                        mTvMoneyz.setText(mo[0]);
                        mTvMoneyf.setText("." + mo[1]);
                    } else {
                        mTvMoneyz.setText(s.toString());
                        mTvMoneyf.setText(".00");
                    }

                }
            }
        });

    }

    private void initEvent() {
        mRlLeft.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                finish();
            }
        });
        mImgTishi.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                TixianTishiDialog.tishiDialog(RechargeActivity.this, 1);
            }
        });
        mRlNext.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (mEtTxmoney.getText().toString().contains("")) {
                    ToastUtils.showToast(ac, "请输入充值金额");
                } else {

                }
            }
        });

        mRlWx.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (!PreferenceHelper.readBoolean(ac, "lottery", "iswxpay", false)) {
                    PreferenceHelper.write(ac, "lottery", "iswxpay", true);
                    mIvWx.setBackgroundResource(R.mipmap.round_true);
                    mIvAli.setBackgroundResource(R.mipmap.round_false);
                }
            }
        });
        mRlAli.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (PreferenceHelper.readBoolean(ac, "lottery", "iswxpay", false)) {
                    PreferenceHelper.write(ac, "lottery", "iswxpay", false);
                    mIvAli.setBackgroundResource(R.mipmap.round_true);
                    mIvWx.setBackgroundResource(R.mipmap.round_false);
                }
            }
        });
    }
}
