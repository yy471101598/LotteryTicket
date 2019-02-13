package com.lottery.bossex.ui.me;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lottery.bossex.R;
import com.lottery.bossex.http.InterfaceBack;
import com.lottery.bossex.http.UrlTools;
import com.lottery.bossex.http.VolleyResponse;
import com.lottery.bossex.tools.NoDoubleClickListener;
import com.lottery.bossex.tools.PreferenceHelper;
import com.lottery.bossex.tools.SignUtils;
import com.lottery.bossex.tools.ToastUtils;
import com.lottery.bossex.ui.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HelpActivity extends BaseActivity {
    @Bind(R.id.img_left)
    ImageView mImgLeft;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.rl_right)
    RelativeLayout mRlRight;
    @Bind(R.id.et_problem)
    EditText mEtProblem;
    @Bind(R.id.rl_submit)
    RelativeLayout mRlSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
//        ShadowUtils.setShadowBackgroud(ac, res, mLiBg);
        initEvent();
    }

    private void submitProblem() {
        dialog.show();
//        "userId":"1000001",
//                "message":"111"
        Map<String, Object> params = new HashMap<>();
        params.put("message", mEtProblem.getText().toString());
        params.put("userId", PreferenceHelper.readString(ac, "lottery", "userid", ""));
        VolleyResponse.instance().getVolleyResponse(ac, UrlTools.obtainUrl("api/feedback"), SignUtils.obtainAllMap(ac, params), new InterfaceBack() {
            @Override
            public void onResponse(Object response) {
                try {
                    dialog.dismiss();
                    JSONObject jso = new JSONObject(response.toString().replace("//", ""));
                    JSONObject js=jso.getJSONObject("data");
                    ToastUtils.showToast(ac, js.getString("result"));
                    finish();
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
        mRlSubmit.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (mEtProblem.getText().toString().equals("")) {
                    ToastUtils.showToast(ac, res.getString(R.string.inputproble));
                } else {
                    submitProblem();
                }
            }
        });
    }
}
