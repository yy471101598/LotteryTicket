package com.lottery.bossex.model;

import android.app.Activity;

import com.lottery.bossex.http.InterfaceBack;
import com.lottery.bossex.http.UrlTools;
import com.lottery.bossex.http.VolleyResponse;
import com.lottery.bossex.tools.SignUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by songxiaotao on 2018/6/19.
 */

public class ImpSendSms {
    public void sendSms(final Activity ac, String phone,
                        final InterfaceBack back) {
        // TODO 自动生成的方法存根
//        "email":"343848758@qq.com", 邮箱
//        "mobile":"" //手机号  二者必须一个有值一个为空字符串
        Map<String, Object> params = new HashMap<>();
        params.put("email", phone.contains("@") ? phone : "");
        params.put("mobile", phone.contains("@") ? "" : phone);
        VolleyResponse.instance().getVolleyResponse(ac, UrlTools.obtainUrl("user/code"), SignUtils.obtainAllMap(ac,params), new InterfaceBack() {
            @Override
            public void onResponse(Object response) {
                back.onResponse("");
            }

            @Override
            public void onErrorResponse(Object msg) {
                back.onErrorResponse("");
            }
        });
    }
}
