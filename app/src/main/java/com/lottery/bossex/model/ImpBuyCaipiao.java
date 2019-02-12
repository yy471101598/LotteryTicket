package com.lottery.bossex.model;

import android.app.Activity;

import com.lottery.bossex.http.InterfaceBack;
import com.lottery.bossex.http.UrlTools;
import com.lottery.bossex.http.VolleyResponse;
import com.lottery.bossex.tools.PreferenceHelper;
import com.lottery.bossex.tools.SignUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by songxiaotao on 2018/6/19.
 */

public class ImpBuyCaipiao {
    public void buyCaipiao(final Activity ac, String lotteryId, String total, String aheadLotteryNo, String behindLotterNo,
                           final InterfaceBack back) {
        // TODO 自动生成的方法存根
//        "userId":"1000001",
//                "lotteryId":"2",
//                "total":"3",    //多少注
//                "aheadLotteryNo":"21,32,32,12"   //选择的前面号码
//        "behindLotterNo":"22,12"   //选择的后面的号码
        Map<String, Object> params = new HashMap<>();
        params.put("userId", PreferenceHelper.readString(ac, "lottery", "userid", ""));
        params.put("lotteryId", lotteryId);
        params.put("total", total);
        params.put("aheadLotteryNo", aheadLotteryNo);
        params.put("behindLotterNo", behindLotterNo);
        VolleyResponse.instance().getVolleyResponse(ac, UrlTools.obtainUrl("order/buylottery"), SignUtils.obtainAllMap(ac, params), new InterfaceBack() {
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
