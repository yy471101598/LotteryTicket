package com.lottery.bossex.ui.me;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lottery.bossex.R;
import com.lottery.bossex.tools.NoDoubleClickListener;
import com.lottery.bossex.tools.ShadowUtils;
import com.lottery.bossex.tools.ToastUtils;
import com.lottery.bossex.ui.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChangePwdActivity extends BaseActivity {

    @Bind(R.id.img_left)
    ImageView mImgLeft;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.rl_right)
    RelativeLayout mRlRight;
    @Bind(R.id.view_line)
    View mViewLine;
    @Bind(R.id.tv_pwd)
    TextView mTvPwd;
    @Bind(R.id.et_password)
    EditText mEtPassword;
    @Bind(R.id.rl_error)
    RelativeLayout mRlError;
    @Bind(R.id.line_error)
    View mLineError;
    @Bind(R.id.tv_newpwd)
    TextView mTvNewpwd;
    @Bind(R.id.et_newpassword)
    EditText mEtNewpassword;
    @Bind(R.id.tv_apwd)
    TextView mTvApwd;
    @Bind(R.id.et_apassword)
    EditText mEtApassword;
    @Bind(R.id.rl_change)
    RelativeLayout mRlChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepwd);
        ButterKnife.bind(this);
        ShadowUtils.setShadowBackgroud(ac, res, mViewLine);
        initEvent();
    }

    private void initEvent() {
        mRlLeft.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                finish();
            }
        });
        mRlChange.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (mEtPassword.getText().toString().equals("")) {
                    ToastUtils.showToast(ac,"请输入原密码");
                } else if (mEtNewpassword.getText().toString().equals("")) {
                    ToastUtils.showToast(ac, "请输入新密码");
                } else if (mEtApassword.getText().toString().equals("")) {
                    ToastUtils.showToast(ac, "请再次输入新密码");
                } else if(mEtNewpassword.getText().toString().equals(mEtApassword.getText().toString())){
                    ToastUtils.showToast(ac,"新密码和确认密码不一样");
                }else {
                  
                }
            }
        });
    }

}
