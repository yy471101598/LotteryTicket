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

public class TixianActivity extends BaseActivity {
    @Bind(R.id.img_left)
    ImageView mImgLeft;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.rl_right)
    RelativeLayout mRlRight;
    @Bind(R.id.img_bank)
    ImageView mImgBank;
    @Bind(R.id.tv_bank)
    TextView mTvBank;
    @Bind(R.id.tv_type)
    TextView mTvType;
    @Bind(R.id.tv_bankcard)
    TextView mTvBankcard;
    @Bind(R.id.img_tishi)
    ImageView mImgTishi;
    @Bind(R.id.et_txmoney)
    EditText mEtTxmoney;
    @Bind(R.id.tv_tixian)
    TextView mTvTixian;
    @Bind(R.id.rl_tixian)
    RelativeLayout mRlTixian;
    @Bind(R.id.rl_bank_bg)
    RelativeLayout mRlBankBg;
    @Bind(R.id.li_tixian_bg)
    LinearLayout mLiTixianBg;
    @Bind(R.id.tv_all)
    TextView mTvAll;
    @Bind(R.id.tv_moneyz)
    TextView mTvMoneyz;
    @Bind(R.id.tv_moneyf)
    TextView mTvMoneyf;
    private String allmoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixian);
        ButterKnife.bind(this);
        allmoney = getIntent().getStringExtra("allmoney");
        mTvAll.setText("可提现 " + allmoney + " BEX");
        ShadowUtils.setShadowBackgroud(ac, res, mLiTixianBg);
        ShadowUtils.setShadowBackgroud(ac, res, mRlBankBg);
        initEvent();
        if (PreferenceHelper.readBoolean(ac, "lottery", "txtishi", false)) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    PreferenceHelper.write(ac, "lottery", "txtishi", true);
                    TixianTishiDialog.tishiDialog(TixianActivity.this, 1);
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
               if(s.toString().equals("")){
                   mTvMoneyz.setText("00");
                   mTvMoneyf.setText(".00");
               }else{
                   if(s.toString().contains(".")){
                       String[] mo=s.toString().split(".");
                       mTvMoneyz.setText(mo[0]);
                       mTvMoneyf.setText("."+mo[1]);
                   }else{
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
                TixianTishiDialog.tishiDialog(TixianActivity.this, 1);
            }
        });
        mTvTixian.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                mEtTxmoney.setText(allmoney);
                if(allmoney.contains(".")){
                    String[] mo=allmoney.split(".");
                    mTvMoneyz.setText(mo[0]);
                    mTvMoneyf.setText("."+mo[1]);
                }else{
                    mTvMoneyz.setText(allmoney);
                    mTvMoneyf.setText(".00");
                }

            }
        });
        mRlTixian.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if(mEtTxmoney.getText().toString().contains("")){
                    ToastUtils.showToast(ac,"请输入提现金额");
                }else if(Double.parseDouble(mEtTxmoney.getText().toString())-Double.parseDouble(allmoney)>0){
                    ToastUtils.showToast(ac,"提现金额大于可提现金额");
                }else{

                }
            }
        });

    }
}
