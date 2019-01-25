package com.lottery.bossex.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lottery.bossex.R;
import com.lottery.bossex.tools.NoDoubleClickListener;
import com.lottery.bossex.tools.ShadowUtils;
import com.lottery.bossex.tools.ToastUtils;
import com.lottery.bossex.views.ClearEditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.et_login_account)
    ClearEditText mEtLoginAccount;
    @Bind(R.id.et_login_password)
    ClearEditText mEtLoginPassword;
    @Bind(R.id.rl_login)
    RelativeLayout mRlLogin;
    @Bind(R.id.li_register)
    LinearLayout mLiRegister;
    @Bind(R.id.li_bg)
    LinearLayout mLiBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initEvent();
        ShadowUtils.setShadowBackgroud(ac, res, mLiBg);
    }

    private void initEvent() {
        mRlLeft.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                finish();
            }
        });

        mLiRegister.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(ac, RegisterActivity.class);
                startActivity(intent);

            }
        });

        mRlLogin.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (mEtLoginAccount.getText().toString().equals("")) {
                    ToastUtils.showToast(ac, res.getString(R.string.input_account));
                } else if (mEtLoginPassword.getText().toString().equals("")) {
                    ToastUtils.showToast(ac, res.getString(R.string.input_password));
                } else {
                    Intent intent = new Intent(ac, HostActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
