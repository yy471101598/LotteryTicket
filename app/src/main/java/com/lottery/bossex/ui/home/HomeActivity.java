package com.lottery.bossex.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lottery.bossex.R;
import com.lottery.bossex.bean.Home;
import com.lottery.bossex.bean.HomeBannerMsg;
import com.lottery.bossex.ui.BaseActivity;
import com.lottery.bossex.views.PagingScrollHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by songxiaotao on 2018/6/5.
 */

public class HomeActivity extends BaseActivity implements PagingScrollHelper.onPageChangeListener {
    @Bind(R.id.li_search)
    LinearLayout mLiSearch;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @Bind(R.id.listview)
    ListView mListview;
    private Gson gson;
    private AtomicInteger what = new AtomicInteger(0);
    /**
     * 是否自动轮播，手指滑动时，设置为false
     */
    private boolean isContinue = true;
    private int Time = 3000;// 广告轮播时间
    private final Handler viewHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }

    };
    // 底部小点图片
    private ImageView[] dots;
    // 记录当前选中位置
    private int currentIndex;
    private HomeBannerAdapter recyAdapter;
    //    PagingScrollHelper scrollHelper = new PagingScrollHelper();
    private List<HomeBannerMsg> datas = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private List<Home> homelist = new ArrayList<>();
    private HomeAdapter mHomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery);
        ac = this;
        ButterKnife.bind(this);
        gson = new Gson();
        recyAdapter = new HomeBannerAdapter(ac, datas);
        layoutManager = new LinearLayoutManager(ac);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerview.setLayoutManager(layoutManager);
        mRecyclerview.setAdapter(recyAdapter);
        mHomeAdapter = new HomeAdapter(ac, homelist);
        mListview.setAdapter(mHomeAdapter);
//        scrollHelper.setUpRecycleView(mRecyclerview);
//        scrollHelper.setOnPageChangeListener(this);
//        mRecyclerview.setHorizontalScrollBarEnabled(true);
        recyAdapter.setOnItemClickListener(new HomeBannerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int tag) {
            }
        });
        obtainBanner();
        obtainHomeMsg();
    }

    private void obtainHomeMsg() {
        Home home = new Home();
        homelist.add(home);
        homelist.add(home);
        homelist.add(home);
        homelist.add(home);
        homelist.add(home);
        homelist.add(home);
        mHomeAdapter.notifyDataSetChanged();

    }


    /**
     * 设置当前指示点
     *
     * @param position
     */
    private void setCurDot(int position) {
        if (position < 0 || position > datas.size() || currentIndex == position) {
            return;
        }
        dots[position].setEnabled(true);
        dots[currentIndex].setEnabled(false);
        currentIndex = position;
    }

    private void initDots() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        dots = new ImageView[datas.size()];

        // 循环取得小点图片
        for (int i = 0; i < datas.size(); i++) {
            // 得到一个LinearLayout下面的每一个子元素
            dots[i] = new ImageView(ac);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(20, 0, 0, 0);
            dots[i].setLayoutParams(params);  //设置图片宽高
            dots[i].setEnabled(false);// 都设为灰色
            dots[i].setImageResource(R.drawable.dot_selector);
            dots[i].setTag(i);// 设置位置tag，方便取出与当前位置对应
            ll.addView(dots[i]);
        }

        currentIndex = 0;
        dots[currentIndex].setEnabled(true); // 设置为白色，即选中状态

    }

    private void obtainBanner() {
        HomeBannerMsg h = new HomeBannerMsg();
        h.image = "";
        datas.add(h);
        datas.add(h);
        datas.add(h);
        initDots();
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                while (true) {
//                    if (isContinue) {
//                        viewHandler.sendEmptyMessage(what.get());
//                        whatOption();
//                    }
//                }
//            }
//
//        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        mTvGonggao.stopAutoScroll();
    }


    private void whatOption() {

        what.incrementAndGet();//自增，然后赋值给当前值

        //如果循环超出设置的个数，则设置为开始
        if (what.get() > datas.size() - 1) {
            what.getAndAdd(-datas.size());
        }
        try {
            Thread.sleep(Time);//3秒滚动一次
        } catch (InterruptedException e) {

        }
    }

    @Override
    public void onPageChange(int index) {

    }
}
