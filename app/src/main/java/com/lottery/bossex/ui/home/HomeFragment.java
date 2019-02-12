package com.lottery.bossex.ui.home;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lottery.bossex.R;
import com.lottery.bossex.bean.Home;
import com.lottery.bossex.bean.HomeBannerMsg;
import com.lottery.bossex.dialog.LoadingDialog;
import com.lottery.bossex.http.InterfaceBack;
import com.lottery.bossex.http.UrlTools;
import com.lottery.bossex.http.VolleyResponse;
import com.lottery.bossex.tools.GlideImageLoader;
import com.lottery.bossex.tools.LogUtils;
import com.lottery.bossex.tools.SignUtils;
import com.lottery.bossex.views.MyListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by songxiaotao on 2018/6/5.
 */

public class HomeFragment extends Fragment implements OnBannerListener {
    @Bind(R.id.li_search)
    LinearLayout mLiSearch;
    @Bind(R.id.mylistview)
    MyListView mMylistview;
    @Bind(R.id.banner)
    Banner banner;
    private List<Home> homelist = new ArrayList<>();
    private HomeAdapter mHomeAdapter;
    private List<String> images = new ArrayList<>();
    private Dialog dialog;
    private Activity ac;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, null);
        ButterKnife.bind(this, view);
        dialog = LoadingDialog.loadingDialog(getActivity(), 1);
        ac = getActivity();
        mHomeAdapter = new HomeAdapter(getActivity(), homelist);
        mMylistview.setAdapter(mHomeAdapter);
        obtainBanner();
        obtainHomeMsg();
        return view;
    }

    private void obtainHomeMsg() {
        dialog.show();
        Map<String, Object> params = new HashMap<>();
        VolleyResponse.instance().getVolleyResponse(ac, UrlTools.obtainUrl("api/home"), SignUtils.obtainAllMap(ac, params), new InterfaceBack() {
            @Override
            public void onResponse(Object response) {
                try {
                    dialog.dismiss();
                    JSONObject jso = new JSONObject(response.toString().replace("//", ""));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onErrorResponse(Object msg) {
                dialog.dismiss();
            }
        });

        Home home = new Home();
        homelist.add(home);
        homelist.add(home);
        homelist.add(home);
        homelist.add(home);
        homelist.add(home);
        homelist.add(home);
        mHomeAdapter.notifyDataSetChanged();

    }


    private void obtainBanner() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
//        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setOnBannerListener(this);

        dialog.show();
        Map<String, Object> params = new HashMap<>();
        VolleyResponse.instance().getVolleyResponse(ac, UrlTools.obtainUrl("api/banners"), SignUtils.obtainAllMap(ac, params), new InterfaceBack() {
            @Override
            public void onResponse(Object response) {
                try {
                    dialog.dismiss();
                    JSONObject jso = new JSONObject(response.toString().replace("//", ""));
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<HomeBannerMsg>>() {
                    }.getType();
                    List<HomeBannerMsg> sllist = gson.fromJson(jso.getString("data"), listType);
                    if (sllist.size() > 0) {
                        for (HomeBannerMsg msg : sllist) {
                            images.add(msg.img);
                        }
                        LogUtils.d("xx", images.toString());
                    }
                    //设置图片集合
                    banner.setImages(images);
                    //banner设置方法全部调用完毕时最后调用
                    banner.start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onErrorResponse(Object msg) {
                dialog.dismiss();
                //banner设置方法全部调用完毕时最后调用
                banner.start();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void OnBannerClick(int position) {

    }
}
