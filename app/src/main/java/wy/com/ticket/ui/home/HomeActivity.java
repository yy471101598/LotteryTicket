package wy.com.ticket.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.Bind;
import butterknife.ButterKnife;
import wy.com.ticket.R;
import wy.com.ticket.adapter.HomeAdapter;
import wy.com.ticket.bean.Home;
import wy.com.ticket.tools.ActivityStack;
import wy.com.ticket.tools.LogUtils;
import wy.com.ticket.ui.BaseActivity;
import wy.com.ticket.views.MyGridView;

/**
 * Created by songxiaotao on 2018/6/5.
 */

public class HomeActivity extends BaseActivity {
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.sartRefresh)
    SmartRefreshLayout mSartRefresh;
    @Bind(R.id.zz_gridview)
    MyGridView mZzGridview;
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
            mViewPager.setCurrentItem(msg.what);
            super.handleMessage(msg);
        }

    };
    private List<String> lunboList;
    private List<Home> list;
    private HomeAdapter adapter;
    // 底部小点图片
    private ImageView[] dots;
    // 记录当前选中位置
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ac = this;
        ActivityStack.create().addActivity(ac);
        ButterKnife.bind(this);
        gson = new Gson();
        obtainBanner();
        obtainHome();
        adapter = new HomeAdapter(ac, list);
        mZzGridview.setAdapter(adapter);
        mSartRefresh.setRefreshHeader(new ClassicsHeader(this));
        mSartRefresh.setRefreshFooter(new ClassicsFooter(this));
        mSartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                LogUtils.d("xxre", "resh");
                obtainBanner();
                mSartRefresh.finishRefresh();
            }
        });
    }

    /**
     * 设置当前指示点
     *
     * @param position
     */
    private void setCurDot(int position) {
        if (position < 0 || position > lunboList.size() || currentIndex == position) {
            return;
        }
        dots[position].setEnabled(true);
        dots[currentIndex].setEnabled(false);
        currentIndex = position;
    }

    private void initDots(View view) {
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll);
        dots = new ImageView[lunboList.size()];

        // 循环取得小点图片
        for (int i = 0; i < lunboList.size(); i++) {
            // 得到一个LinearLayout下面的每一个子元素
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(false);// 都设为灰色
            dots[i].setTag(i);// 设置位置tag，方便取出与当前位置对应
        }

        currentIndex = 0;
        dots[currentIndex].setEnabled(true); // 设置为白色，即选中状态

    }

    private void obtainBanner() {
        List<View> viewlist = new ArrayList<View>();
        lunboList = new ArrayList<String>();
        lunboList.add("");
        lunboList.add("");
        lunboList.add("");
        for (int i = 0; i < lunboList.size(); i++) {
            View view = LayoutInflater.from(ac).inflate(R.layout.activity_home_viewpager, null);
            initDots(view);
            viewlist.add(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
        MyPagerAdapter adapter = new MyPagerAdapter(viewlist);
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new GuidePageChangeListener());
        mViewPager.setCurrentItem(0);
        mViewPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isContinue = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        isContinue = true;
                        break;
                    default:
                        isContinue = true;
                        break;
                }
                return false;
            }
        });
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (isContinue) {
                        viewHandler.sendEmptyMessage(what.get());
                        whatOption();
                    }
                }
            }

        }).start();
    }

    private void obtainHome() {
        list = new ArrayList<Home>();
        Home h1 = new Home();
        h1.name = "智考通";
        h1.iconId = R.mipmap.zhikaotong;
        list.add(h1);
        Home h2 = new Home();
        h2.name = "HR考证";
        h2.iconId = R.mipmap.kaozheng;
        list.add(h2);
        Home h22 = new Home();
        h22.name = "HR突破";
        h22.iconId = R.mipmap.tupo;
        list.add(h22);
        Home h3 = new Home();
        h3.name = "MBA突破";
        h3.iconId = R.mipmap.mbatuo;
        list.add(h3);
        Home h7 = new Home();
        h7.name = "学历教育";
        h7.iconId = R.mipmap.xuelijiaoyu;
        list.add(h7);
        Home h9 = new Home();
        h9.name = "职业测评";
        h9.iconId = R.mipmap.zhiyecepin;
        list.add(h9);
        Home h4 = new Home();
        h4.name = "招聘信息";
        h4.iconId = R.mipmap.zhaopinxinxi;
        list.add(h4);
        Home h8 = new Home();
        h8.name = "求职信息";
        h8.iconId = R.mipmap.qiuzhixinxi;
        list.add(h8);
        Home h81 = new Home();
        h81.name = "课程超市";
        h81.iconId = R.mipmap.kechengchaoshi;
        list.add(h81);
        Home h62 = new Home();
        h62.name = "法律援助";
        h62.iconId = R.mipmap.falvyuanzhu;
        list.add(h62);
        Home h5 = new Home();
        h5.name = "内训咨询";
        h5.iconId = R.mipmap.neixunzixun;
        list.add(h5);
        Home h63 = new Home();
        h63.name = "资料下载";
        h63.iconId = R.mipmap.kechengchaoshi;
        list.add(h63);
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
        if (what.get() > lunboList.size() - 1) {
            what.getAndAdd(-lunboList.size());
        }
        try {
            Thread.sleep(Time);//3秒滚动一次
        } catch (InterruptedException e) {

        }
    }

    public class MyPagerAdapter extends PagerAdapter {
        private List<View> viewLsit;

        public MyPagerAdapter(List<View> viewLsit) {
            this.viewLsit = viewLsit;
        }

        /**
         * 返回页卡的数量
         */
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return viewLsit.size();
        }

        /**
         * View是否来自于对象
         */
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            //官方做法
            return arg0 == arg1;
        }

        /**
         * 实例化一个页卡
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            container.addView(viewLsit.get(position));
            return viewLsit.get(position);
        }

        /**
         * 销毁一个页卡
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
            container.removeView(viewLsit.get(position));
        }

    }


    private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int arg0) {

            setCurDot(arg0);
            what.getAndSet(arg0);
        }

    }
}
