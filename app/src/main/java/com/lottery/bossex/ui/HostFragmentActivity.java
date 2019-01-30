package com.lottery.bossex.ui;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lottery.bossex.R;
import com.lottery.bossex.tools.ActivityStack;
import com.lottery.bossex.tools.ObtainSystemLanguage;
import com.lottery.bossex.tools.PreferenceHelper;

import java.util.Locale;


public class HostFragmentActivity extends BaseActivity implements OnClickListener {
    public static final String TAB_HOME = "tabHome";
    public static final String TAB_CENTER = "tabCenter";
    public static final String TAB_ME = "tabMe";
    private RelativeLayout li_tab_home, li_tab_me, li_tab_center;
    private ImageView img_home, img_me, img_center;
    private TextView tv_home, tv_me, tv_center;
    private Activity ac;
    private long firstTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        ac = this;
        initLocaleLanguage();
        setContentView(R.layout.activity_host);
//        StatusBarCompat.setStatusBarColor(ac, getResources().getColor(R.color.theme_color), 112);
        initView();
        ActivityStack.create().addActivity(HostFragmentActivity.this);
        initEvent();
        // 重置底部导航拦
        resetTab();
        // 默认选中第一个选项卡
        selectTab(1);
//		update();
    }


    @Override
    protected void onResume() {
        super.onResume();
        boolean isfirst = PreferenceHelper.readBoolean(ac, "carapp", "toFirst", false);
        if (isfirst) {
            selectTab(1);
            PreferenceHelper.write(ac, "carapp", "toFirst", false);
        }
    }

    private void initLocaleLanguage() {
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        //获取屏幕参数
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        String language = ObtainSystemLanguage.obainLanguage(getApplicationContext());
        //设置本地语言
        switch (language) {
            case "zh":
                configuration.locale = Locale.CHINA;
                PreferenceHelper.write(ac, "lottery", "lagavage", "zh");
                break;
            case "en":
                configuration.locale = Locale.ENGLISH;
                PreferenceHelper.write(ac, "lottery", "lagavage", "en");
                break;
            case "vi":
                configuration.locale = new Locale("vi");
                break;
        }
        resources.updateConfiguration(configuration, displayMetrics);
    }
//	private void update() {
//		// TODO Auto-generated method stub
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("clientType", "android");
//		// TODO Auto-generated method stub
//		VolleyResponse.instance().getVolleyResponse(
//				getApplicationContext(),
//				CommonUtils.obtainUrl(getApplicationContext(), map,
//						"queryNewVersion"), map, new InterfaceVolleyResponse() {
//
//					@Override
//					public void onResponse(int code, Object response) {
//						// {
//						// "extend3": "1.更新aaa2.更新bbb3.更新cccc",
//						// "statusCode": "notsupport_version",
//						// "message": "请更新新版本，当前最低支持版本为：1.1",
//						// "extend2": "http:\/\/www.baidu.com",
//						// "result": null,
//						// "extend1": "http:\/\/www.baidu.com"
//						// }{
//						// "message": "查询最新版本成功",
//						// "statusCode": "success",
//						// "totalCount": 0,
//						// "result": {
//						// "updateMsg": "1.更新aaa2.更新bbb3.更新cccc",
//						// "downloadUrl": "http:\/\/www.baidu.com",
//						// "lastestVersion": "http:\/\/www.baidu.com"
//						// }
//						// }
//
//						try {
//
//							JSONObject jso = new JSONObject(response.toString());
//							if (jso.getString("statusCode").equals("success")) {
//							} else if (jso.getString("statusCode").equals(
//									"notsupport_version")) {
//								Gson gson = new Gson();
//								final Uptate update = gson.fromJson(
//										response.toString(), Uptate.class);
//								DialogUtil.updateRecommendDialog(
//										HostActivity.this, 1, update,
//										new InterfaceNologin() {
//
//											@Override
//											public void onResponse(
//													Object response) {
//												// TODO Auto-generated method
//												// stub
//												// 1.更新aaa||2.更新bbb||3.更新cccc;
//
//												Intent updateIntent = new Intent(
//														getApplicationContext(),
//														UpdateService.class);
//												updateIntent.putExtra(
//														"titleId",
//														R.string.app_name);
//												updateIntent.putExtra("url",
//														update.extend1);
//												startService(updateIntent);
//											}
//
//											@Override
//											public void onErrorResponse(
//													Object msg) {
//												// TODO Auto-generated method
//												// stub
//											}
//										});
//							} else {
//							}
//						} catch (Exception e) {
//
//						}
//					}
//
//					@Override
//					public void onErrorResponse(int code, Object msg) {
//						// TODO Auto-generated method stub
//					}
//				});
//	}

    /**
     * 重置底部导航栏，由于每次点击效果都要改变，先重置成默认的，然后把点击的特殊处理
     */
    private void resetTab() {
        // TODO Auto-generated method stub
        img_home.setBackgroundResource(R.mipmap.home_false);
        img_center.setBackgroundResource(R.mipmap.center_false);
        img_me.setBackgroundResource(R.mipmap.me_false);

        tv_home.setTextColor(getResources().getColor(R.color.texte5));
        tv_center.setTextColor(getResources().getColor(R.color.texte5));
        tv_me.setTextColor(getResources().getColor(R.color.texte5));

    }

    private void initEvent() {
        // TODO Auto-generated method stub
        li_tab_home.setOnClickListener(this);
        li_tab_me.setOnClickListener(this);
        li_tab_center.setOnClickListener(this);
    }

    private void initView() {
        // TODO Auto-generated method stub
        li_tab_home = (RelativeLayout) findViewById(R.id.tab_home);
        li_tab_me = (RelativeLayout) findViewById(R.id.tab_me);
        li_tab_center = (RelativeLayout) findViewById(R.id.tab_center);

        img_home = (ImageView) findViewById(R.id.iv_home);
        img_me = (ImageView) findViewById(R.id.iv_me);
        img_center = (ImageView) findViewById(R.id.iv_center);
        tv_home = (TextView) findViewById(R.id.tv_home);
        tv_me = (TextView) findViewById(R.id.tv_me);
        tv_center = (TextView) findViewById(R.id.tv_center);
    }

    /**
     * 选项卡的选择，同时把选中的选项卡对应的导航栏做特殊处理
     */
    private void selectTab(int i) {
        switch (i) {
            case 1:
                resetTab();
                img_home.setBackgroundResource(R.mipmap.home_true);
                tv_home.setTextColor(getResources().getColor(R.color.theme_color));
                break;
            case 2:
                resetTab();
                img_center.setBackgroundResource(R.mipmap.me_true);
                tv_center.setTextColor(getResources().getColor(R.color.theme_color));
                break;
            case 3:
                resetTab();
                img_me.setBackgroundResource(R.mipmap.me_true);
                tv_me.setTextColor(getResources().getColor(R.color.theme_color));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tab_home:
                selectTab(1);
                break;
            case R.id.tab_center:
                selectTab(2);
                break;
            case R.id.tab_me:
                selectTab(3);
                break;
        }
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return exitApplication();
        }
        return super.dispatchKeyEvent(event);
    }

    private long firstBackKeyDown;

    private boolean exitApplication() {
        if (firstBackKeyDown == 0 ? true : false) {
            firstBackKeyDown = System.currentTimeMillis();
            Toast.makeText(HostFragmentActivity.this, getResources().getString(R.string.aginout), Toast.LENGTH_LONG)
                    .show();
            return true;
        } else {
            if (System.currentTimeMillis() - firstBackKeyDown <= 2000 ? true : false) {
                ActivityStack.create().AppExit(ac);
                return true;
            } else {
                firstBackKeyDown = 0;
                exitApplication();
                return true;
            }
        }
    }

    //        @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        Log.d("xxx", "homeback");
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            long secndTime = System.currentTimeMillis();
            Log.d("xxx", secndTime + ";" + firstTime);
            if (secndTime - firstTime > 3000) {
                firstTime = secndTime;
                Toast.makeText(HostFragmentActivity.this, getResources().getString(R.string.aginout), Toast.LENGTH_LONG)
                        .show();
            } else {
                ActivityStack.create().AppExit(ac);
            }
            return true;
        }
        return false;
    }
}
