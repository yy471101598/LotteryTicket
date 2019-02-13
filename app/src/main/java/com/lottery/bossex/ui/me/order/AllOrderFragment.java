package com.lottery.bossex.ui.me.order;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lottery.bossex.R;
import com.lottery.bossex.bean.OrderMsg;
import com.lottery.bossex.dialog.LoadingDialog;
import com.lottery.bossex.http.InterfaceBack;
import com.lottery.bossex.http.UrlTools;
import com.lottery.bossex.http.VolleyResponse;
import com.lottery.bossex.tools.PreferenceHelper;
import com.lottery.bossex.tools.SignUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AllOrderFragment extends Fragment {
    @Bind(R.id.listview)
    ListView mListview;
    private Dialog dialog;
    private Activity ac;
    private OrderAdapter mOrderAdapter;
    private List<OrderMsg> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orderdetail, null);
        ButterKnife.bind(view);
        dialog = LoadingDialog.loadingDialog(getActivity(), 1);
        ac = getActivity();
        list = new ArrayList<>();
        mOrderAdapter = new OrderAdapter(ac, list);
        mListview.setAdapter(mOrderAdapter);
        obtainOrder();
        return view;
    }

    private void obtainOrder() {
        dialog.show();
//        "type":"1"//1 全部 2中奖 3未中奖 4待开奖
//        "userId":"1000001"
        Map<String, Object> params = new HashMap<>();
        params.put("type", "1");
        params.put("userId", PreferenceHelper.readString(ac, "lottery", "userid", ""));
        VolleyResponse.instance().getVolleyResponse(ac, UrlTools.obtainUrl("order/list"), SignUtils.obtainAllMap(ac, params), new InterfaceBack() {
            @Override
            public void onResponse(Object response) {
                try {
                    dialog.dismiss();
                    JSONObject jso = new JSONObject(response.toString().replace("//", ""));
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<OrderMsg>>() {
                    }.getType();
                    List<OrderMsg> sllist = gson.fromJson(jso.getString("data"), listType);
                    list.addAll(sllist);
                    mOrderAdapter.notifyDataSetChanged();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
