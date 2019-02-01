package com.lottery.bossex.ui.buy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lottery.bossex.R;
import com.lottery.bossex.bean.BuyCaipiao;
import com.lottery.bossex.tools.NoDoubleClickListener;
import com.lottery.bossex.ui.BaseActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChoseNumberActivity extends BaseActivity {
    @Bind(R.id.img_left)
    ImageView mImgLeft;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.rl_right)
    RelativeLayout mRlRight;
    @Bind(R.id.cur_num)
    TextView mCurNum;
    @Bind(R.id.all_num)
    TextView mAllNum;
    @Bind(R.id.li_c)
    LinearLayout mLiC;
    @Bind(R.id.rl_jichose)
    RelativeLayout mRlJichose;
    @Bind(R.id.img_delete)
    ImageView mImgDelete;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private Activity ac;
    private ChoseNumberAdapter recyAdapter;
    private LinearLayoutManager layoutManager;
    private List<BuyCaipiao> datas;
    private String curnum = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosecaipiao);
        ButterKnife.bind(this);
        ac = this;
        initEvent();
        Bundle bundle = getIntent().getExtras();
        datas = (List<BuyCaipiao>) bundle.getSerializable("list");
        curnum = bundle.getString("number");
        recyAdapter = new ChoseNumberAdapter(ac, datas);
        layoutManager = new LinearLayoutManager(ac);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerview.setLayoutManager(layoutManager);
        mRecyclerview.setAdapter(recyAdapter);
    }

    private void initEvent() {
        mRlLeft.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                //数据是使用Intent返回
                Bundle bundle=new Bundle();
                bundle.putSerializable("list", (Serializable) datas);
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtras(bundle);
                //设置返回数据
                setResult(RESULT_OK, intent);
                //关闭Activity
                finish();
            }
        });
    }
}
