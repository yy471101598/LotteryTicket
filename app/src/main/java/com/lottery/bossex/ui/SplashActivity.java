package com.lottery.bossex.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.lottery.bossex.R;
import com.lottery.bossex.bean.event.EventMsg;
import com.lottery.bossex.tools.CleanMSG;
import com.lottery.bossex.tools.EventBusUtils;
import com.lottery.bossex.tools.PreferenceHelper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.lottery.bossex.tools.LogUtils.tag;


public class SplashActivity extends Activity {

    private static final int GO_MAIN = 1000;
    private static final int GO_HOME = 2000;
    private static final int GO_CHOSE = 3000;

    /**
     * Handler:跳转到不同界面
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_MAIN:
                    goMain();
                    break;
                case GO_HOME:
                    goHome();

                    break;
                case GO_CHOSE:
                    goChose();

                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO 自动生成的方法存根
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
//        PermissionUtil.checkPermission(this);
    }


    private boolean IsLogin() {
        boolean isLogin = PreferenceHelper.readBoolean(getApplicationContext(), "lottery", "isLogin", false);
        if (isLogin) {
            return true;
        } else {
            return false;
        }
    }


    private void init() {
        boolean isFirst = PreferenceHelper.readBoolean(getApplicationContext(), "lottery", "isFirst", true);
        if (isFirst) {
            Message msg = mHandler.obtainMessage();
            msg.what = GO_CHOSE;
            mHandler.sendMessageDelayed(msg, 500);
        } else {
            if (IsLogin()) {
                Message msg = mHandler.obtainMessage();
                msg.what = GO_HOME;
                mHandler.sendMessageDelayed(msg, 500);
            } else {
                CleanMSG.cleanSharedPreference(getApplicationContext());
                Message msg = mHandler.obtainMessage();
                msg.what = GO_MAIN;
                mHandler.sendMessageDelayed(msg, 500);
            }
        }
    }

    private void goMain() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        SplashActivity.this.startActivity(intent);
        SplashActivity.this.finish();
    }

    private void goHome() {
        Intent intent = new Intent(SplashActivity.this, HostActivity.class);
        startActivity(intent);
        finish();
    }

    private void goChose() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBusUtils.register(this);
        EventBusUtils.post(new EventMsg());
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 1)
    public void onMessageEventMain(EventMsg event) {
        Log.v(tag, " MAIN id = " + Thread.currentThread().getId());
    }
}
