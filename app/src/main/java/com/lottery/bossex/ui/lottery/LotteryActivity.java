package com.lottery.bossex.ui.lottery;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.lottery.bossex.R;
import com.lottery.bossex.bean.Lottery;
import com.lottery.bossex.ui.BaseActivity;
import com.lottery.bossex.views.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LotteryActivity extends BaseActivity {
    @Bind(R.id.li_search)
    LinearLayout mLiSearch;
    @Bind(R.id.listview)
    MyListView mListview;
    private List<Lottery> list = new ArrayList<>();
    private LotteryAdapter mLotteryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery);
        ButterKnife.bind(this);
        obtainLottery();
    }

    private void obtainLottery() {
        Lottery l = new Lottery();
        list.add(l);
        list.add(l);
        list.add(l);
        list.add(l);
        list.add(l);
        list.add(l);
        list.add(l);
        list.add(l);
        list.add(l);
        list.add(l);
        list.add(l);
        mLotteryAdapter = new LotteryAdapter(ac, list);
        mListview.setAdapter(mLotteryAdapter);

    }
}
