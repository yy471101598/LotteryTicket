package com.lottery.bossex.ui.home;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.lottery.bossex.R;
import com.lottery.bossex.bean.Home;
import com.lottery.bossex.http.InterfaceBack;
import com.lottery.bossex.http.UrlTools;
import com.lottery.bossex.http.VolleyResponse;
import com.lottery.bossex.tools.GlideImageLoader;
import com.lottery.bossex.tools.SignUtils;
import com.lottery.bossex.ui.BaseActivity;
import com.lottery.bossex.views.MyListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by songxiaotao on 2018/6/5.
 */

public class HomeActivity extends BaseActivity implements OnBannerListener {
    @Bind(R.id.li_search)
    LinearLayout mLiSearch;
    @Bind(R.id.mylistview)
    MyListView mMylistview;
    @Bind(R.id.banner)
    Banner banner;
    private List<Home> homelist = new ArrayList<>();
    private HomeAdapter mHomeAdapter;
    private List<String> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ac = this;
        ButterKnife.bind(this);
        mHomeAdapter = new HomeAdapter(ac, homelist);
        mMylistview.setAdapter(mHomeAdapter);
        obtainBanner();
        obtainHomeMsg();
    }

    private void obtainHomeMsg() {
        dialog.show();
        Map<String, Object> params = new HashMap<>();
        VolleyResponse.instance().getVolleyResponse(ac, UrlTools.obtainUrl("api/home"), SignUtils.obtainAllMap(ac, params), new InterfaceBack() {
            @Override
            public void onResponse(Object response) {
                try {
                    dialog.dismiss();
                    JSONObject jso = new JSONObject(response.toString());

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
        images.add("https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/wh%3D600%2C800/sign=e9873bfca944ad342eea8f81e09220cc/a8ec8a13632762d08fa73daea8ec08fa513dc602.jpg");
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
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
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        dialog.show();
        Map<String, Object> params = new HashMap<>();
        VolleyResponse.instance().getVolleyResponse(ac, UrlTools.obtainUrl("api/banners"), SignUtils.obtainAllMap(ac, params), new InterfaceBack() {
            @Override
            public void onResponse(Object response) {
                try {
                    dialog.dismiss();
                    JSONObject jso = new JSONObject(response.toString());

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

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void OnBannerClick(int position) {

    }
}
