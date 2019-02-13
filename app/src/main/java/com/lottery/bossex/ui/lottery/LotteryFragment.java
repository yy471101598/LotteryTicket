package com.lottery.bossex.ui.lottery;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lottery.bossex.R;
import com.lottery.bossex.bean.HomeBannerMsg;
import com.lottery.bossex.bean.Lottery;
import com.lottery.bossex.dialog.LoadingDialog;
import com.lottery.bossex.http.InterfaceBack;
import com.lottery.bossex.http.UrlTools;
import com.lottery.bossex.http.VolleyResponse;
import com.lottery.bossex.tools.SignUtils;
import com.lottery.bossex.ui.buy.JincaiDajiangActivity;
import com.lottery.bossex.views.MyListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LotteryFragment extends Fragment {
    @Bind(R.id.li_search)
    LinearLayout mLiSearch;
    @Bind(R.id.listview)
    MyListView mListview;
    @Bind(R.id.tv_stickname)
    TextView mTvStickname;
    @Bind(R.id.tv_stickname_sticky)
    TextView mTvSticknameSticky;
    @Bind(R.id.li_search_sticky)
    LinearLayout mLiSearchSticky;
    @Bind(R.id.sartRefresh)
    SmartRefreshLayout mSartRefresh;
    private List<Lottery> list = new ArrayList<>();
    private LotteryAdapter mLotteryAdapter;
    private int num = 10;
    private int mindex = 1;
    private Dialog dialog;
    private Activity ac;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_lottery, null);
        ButterKnife.bind(this, view);
        dialog = LoadingDialog.loadingDialog(getActivity(), 1);
        ac = getActivity();
        mLotteryAdapter = new LotteryAdapter(getActivity(), list);
        mListview.setAdapter(mLotteryAdapter);
        obtainLottery(mindex);
        mSartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                obtainLottery(mindex);
            }
        });
        mSartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mSartRefresh.finishLoadMore();
            }
        });
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), JincaiDajiangActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    /**
     * 彩票开奖记录
     */
    private void obtainLottery(int index) {
        dialog.show();
//        "page":"1",
//                "size":"20",
//                "lotteryType":"11"      //彩票的种类
        Map<String, Object> params = new HashMap<>();
        params.put("page", index);
        params.put("size", num);
        params.put("lotteryType", "11");
        VolleyResponse.instance().getVolleyResponse(ac, UrlTools.obtainUrl("api/historylottery"), SignUtils.obtainAllMap(ac, params), new InterfaceBack() {
            @Override
            public void onResponse(Object response) {
                try {
                    dialog.dismiss();
                    mindex=mindex+1;
                    JSONObject jso = new JSONObject(response.toString().replace("//", ""));
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<HomeBannerMsg>>() {
                    }.getType();
                    List<HomeBannerMsg> sllist = gson.fromJson(jso.getString("data"), listType);
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
