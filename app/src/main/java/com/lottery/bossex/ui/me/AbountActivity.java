package com.lottery.bossex.ui.me;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lottery.bossex.R;
import com.lottery.bossex.http.InterfaceBack;
import com.lottery.bossex.http.UrlTools;
import com.lottery.bossex.http.VolleyResponse;
import com.lottery.bossex.tools.NoDoubleClickListener;
import com.lottery.bossex.tools.ShadowUtils;
import com.lottery.bossex.tools.SignUtils;
import com.lottery.bossex.ui.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AbountActivity extends BaseActivity {

    @Bind(R.id.img_left)
    ImageView mImgLeft;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.rl_right)
    RelativeLayout mRlRight;
    @Bind(R.id.tv_code)
    TextView mTvCode;
    @Bind(R.id.tv_msg)
    TextView mTvMsg;
    @Bind(R.id.tv_guanwang)
    TextView mTvGuanwang;
    @Bind(R.id.tv_weibo)
    TextView mTvWeibo;
    @Bind(R.id.tv_bottom)
    TextView mTvBottom;
    @Bind(R.id.li_bg)
    View mLiBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        ShadowUtils.setShadowBackgroud(ac, res, mLiBg);
        obtainAbout();
        initEvent();
    }

    private void obtainAbout() {
        dialog.show();
        Map<String, Object> params = new HashMap<>();
        VolleyResponse.instance().getVolleyResponse(ac, UrlTools.obtainUrl("api/about"), SignUtils.obtainAllMap(ac, params), new InterfaceBack() {
            @Override
            public void onResponse(Object response) {
                try {
                    dialog.dismiss();
                    JSONObject jso = new JSONObject(response.toString().replace("//", ""));
                    JSONObject js=jso.getJSONObject("data");
                    mTvMsg.setText(js.getString("text"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onErrorResponse(Object msg) {
                dialog.dismiss();
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
    }
}
