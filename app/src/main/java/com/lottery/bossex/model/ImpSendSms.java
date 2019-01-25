package com.lottery.bossex.model;

import android.app.Activity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import com.lottery.bossex.http.InterfaceBack;
import com.lottery.bossex.http.UrlTools;
import com.lottery.bossex.tools.LogUtils;
import com.lottery.bossex.tools.ToastUtils;

/**
 * Created by songxiaotao on 2018/6/19.
 */

public class ImpSendSms {
    public void sendSms(final Activity ac, String phone, int type,
                        final InterfaceBack back) {
        // TODO 自动生成的方法存根
//        open_id	String	必填	第三方openid
//        name	string	选填	昵称
//        head	string	选填	头像
//        sex	byte	选填	性别 0：男 1：女(默认)
//        birth	long	选填	生日(毫秒)
//        type	String	必填	三方类型（全小写）：wx,qq,wb,alipay,facebook,instagram,google

        AsyncHttpClient asy = new AsyncHttpClient();
        String url = UrlTools.obtainUrl("api/user/phoneCode") + "?phone=" + phone + "&used_to="+type;
        LogUtils.d("url", url);
        asy.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                // TODO 自动生成的方法存根
                String result = new String(responseBody);
                Log.d("xxS", result);
                // {"result":true,"errorCode":1,"errorMessage":"查询成功","data":[]}
                try {
                    LogUtils.d("xxmsg", new String(responseBody, "UTF-8"));
                    JSONObject jso = new JSONObject(new String(responseBody, "UTF-8"));
                    if (jso.getInt("status") == 0) {
                        back.onResponse(jso.getString("data"));
                    } else {
                        ToastUtils.showToast(ac, jso.getString("resmsg"));
                        back.onErrorResponse("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                // TODO 自动生成的方法存根
                back.onErrorResponse("");
            }
        });
    }
}
