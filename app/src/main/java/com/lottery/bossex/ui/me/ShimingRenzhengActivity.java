package com.lottery.bossex.ui.me;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lottery.bossex.R;
import com.lottery.bossex.http.InterfaceBack;
import com.lottery.bossex.model.ImpSendSms;
import com.lottery.bossex.tools.MyTimer;
import com.lottery.bossex.tools.NoDoubleClickListener;
import com.lottery.bossex.tools.ShadowUtils;
import com.lottery.bossex.tools.ToastUtils;
import com.lottery.bossex.ui.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShimingRenzhengActivity extends BaseActivity {
    @Bind(R.id.img_left)
    ImageView mImgLeft;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.rl_right)
    RelativeLayout mRlRight;
    @Bind(R.id.line_bg)
    View mLineBg;
    @Bind(R.id.et_name)
    EditText mEtName;
    @Bind(R.id.et_sfz)
    EditText mEtSfz;
    @Bind(R.id.et_bankcard)
    EditText mEtBankcard;
    @Bind(R.id.et_skbank)
    EditText mEtSkbank;
    @Bind(R.id.et_account)
    EditText mEtAccount;
    @Bind(R.id.tv_code)
    TextView mTvCode;
    @Bind(R.id.rl_code)
    RelativeLayout mRlCode;
    @Bind(R.id.line_error)
    View mLineError;
    @Bind(R.id.et_code)
    EditText mEtCode;
    @Bind(R.id.rl_change)
    RelativeLayout mRlChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shimingrenzheng);
        ButterKnife.bind(this);
        ShadowUtils.setShadowBackgroud(ac, res, mLineBg);
        initEvent();
    }

    private void initEvent() {
        mRlLeft.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                finish();
            }
        });

        mRlCode.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (mEtAccount.getText().toString() == null || mEtAccount.getText().toString().equals("")) {
                    ToastUtils.showToast(ac, res.getString(R.string.inputyp));
                } else {
                    sendSms();
                }
            }
        });
        mRlChange.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (mEtName.getText().toString() == null || mEtName.getText().toString().equals("")) {
                    ToastUtils.showToast(ac, "请输入真实姓名");
                } else if (mEtSfz.getText().toString() == null || mEtSfz.getText().toString().equals("")) {
                    ToastUtils.showToast(ac, "请输入身份证号");
                } else if (mEtBankcard.getText().toString() == null || mEtBankcard.getText().toString().equals("")) {
                    ToastUtils.showToast(ac, "请输入银行卡号");
                } else if (mEtSkbank.getText().toString() == null || mEtSkbank.getText().toString().equals("")) {
                    ToastUtils.showToast(ac, "请输入收款银行名称");
                } else if (mEtCode.getText().toString() == null || mEtCode.getText().toString().equals("")) {
                    ToastUtils.showToast(ac, "请输入验证码");
                } else if (mEtAccount.getText().toString() == null || mEtAccount.getText().toString().equals("")) {
                    ToastUtils.showToast(ac, res.getString(R.string.inputyp));
                } else {

                }
            }
        });

    }


    private void sendSms() {
        dialog.show();
//        1注册 2登陆 3重置密码 4绑定手机号
        ImpSendSms sendSms = new ImpSendSms();
        sendSms.sendSms(ShimingRenzhengActivity.this, mEtAccount.getText().toString(), new InterfaceBack() {
            @Override
            public void onResponse(Object response) {
                MyTimer myTimer = new MyTimer(ShimingRenzhengActivity.this, 90000,
                        1000, mTvCode, mRlCode);
                myTimer.start();
                dialog.dismiss();
            }

            @Override
            public void onErrorResponse(Object msg) {
                dialog.dismiss();
            }
        });
    }
}
