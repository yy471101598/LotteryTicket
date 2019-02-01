package com.lottery.bossex.ui.lottery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.lottery.bossex.R;
import com.lottery.bossex.bean.Lottery;
import com.lottery.bossex.ui.buy.JincaiDajiangActivity;
import com.lottery.bossex.views.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LotteryFragment extends Fragment {
    @Bind(R.id.li_search)
    LinearLayout mLiSearch;
    @Bind(R.id.listview)
    MyListView mListview;
    private List<Lottery> list = new ArrayList<>();
    private LotteryAdapter mLotteryAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_lottery, null);
        ButterKnife.bind(this,view);
        obtainLottery();
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),JincaiDajiangActivity.class);
                startActivity(intent);
            }
        });
        return view;
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
        mLotteryAdapter = new LotteryAdapter(getActivity(), list);
        mListview.setAdapter(mLotteryAdapter);

    }
}
