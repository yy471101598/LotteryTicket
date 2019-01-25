package com.lottery.bossex.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lottery.bossex.R;
import com.lottery.bossex.http.InterfaceBack;
import com.lottery.bossex.model.ImpSendSms;
import com.lottery.bossex.tools.MyTimer;
import com.lottery.bossex.tools.NoDoubleClickListener;
import com.lottery.bossex.tools.PreferenceHelper;
import com.lottery.bossex.tools.ShadowUtils;
import com.lottery.bossex.tools.ToastUtils;
import com.lottery.bossex.views.ClearEditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.rl_right)
    RelativeLayout mRlRight;
    @Bind(R.id.et_login_account)
    ClearEditText mEtLoginAccount;
    @Bind(R.id.et_login_password)
    ClearEditText mEtLoginPassword;
    @Bind(R.id.et_login_code)
    ClearEditText mEtLoginCode;
    @Bind(R.id.tv_code)
    TextView mTvCode;
    @Bind(R.id.rl_obtaincode)
    RelativeLayout mRlObtaincode;
    @Bind(R.id.img_xieyi)
    ImageView mImgXieyi;
    @Bind(R.id.li_xieyi)
    LinearLayout mLiXieyi;
    @Bind(R.id.rl_register)
    RelativeLayout mRlRegister;
    @Bind(R.id.li_bg)
    LinearLayout mLiBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initEvent();
        PreferenceHelper.write(ac, "lottery", "readxy", true);
        ShadowUtils.setShadowBackgroud(ac, res, mLiBg);
    }

    private void initEvent() {
        mRlLeft.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                finish();
            }
        });

        mRlObtaincode.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (mEtLoginAccount.getText().toString() == null || mEtLoginAccount.getText().toString().equals("")) {
                    ToastUtils.showToast(ac, res.getString(R.string.inputyp));
                } else {
                    sendSms();
                }
            }
        });

        mLiXieyi.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (PreferenceHelper.readBoolean(ac, "lottery", "readxy", true)) {
                    PreferenceHelper.write(ac, "lottery", "readxy", false);
                    mImgXieyi.setBackgroundResource(R.mipmap.round_false);
                } else {
                    PreferenceHelper.write(ac, "lottery", "readxy", true);
                    mImgXieyi.setBackgroundResource(R.mipmap.round_true);
                }

            }
        });

        mRlRegister.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (mEtLoginAccount.getText().toString().equals("")) {
                    ToastUtils.showToast(ac, res.getString(R.string.inputyp));
                } else if (mEtLoginPassword.getText().toString().equals("")) {
                    ToastUtils.showToast(ac, res.getString(R.string.input_password));
                } else if (mEtLoginCode.getText().toString().equals("")) {
                    ToastUtils.showToast(ac, res.getString(R.string.inputcode));
                } else if (!PreferenceHelper.readBoolean(ac, "lottery", "readxy", true)) {
                    ToastUtils.showToast(ac, res.getString(R.string.pleaseread));
                } else {

                }
            }
        });
    }

    private void sendSms() {
        dialog.show();
//        1注册 2登陆 3重置密码 4绑定手机号
        ImpSendSms sendSms = new ImpSendSms();
        sendSms.sendSms(RegisterActivity.this, mEtLoginAccount.getText().toString(), 1, new InterfaceBack() {
            @Override
            public void onResponse(Object response) {
                MyTimer myTimer = new MyTimer(RegisterActivity.this, 90000,
                        1000, mTvCode, mRlObtaincode);
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
