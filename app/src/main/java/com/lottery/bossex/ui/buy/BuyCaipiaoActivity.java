package com.lottery.bossex.ui.buy;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lottery.bossex.R;
import com.lottery.bossex.bean.BuyCaipiao;
import com.lottery.bossex.dialog.ShowCaipiaoNumberDialog;
import com.lottery.bossex.tools.NoDoubleClickListener;
import com.lottery.bossex.tools.ToastUtils;
import com.lottery.bossex.ui.BaseActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BuyCaipiaoActivity extends BaseActivity {
    @Bind(R.id.img_left)
    ImageView mImgLeft;
    @Bind(R.id.rl_left)
    RelativeLayout mRlLeft;
    @Bind(R.id.viewpager_img)
    SimpleDraweeView mViewpagerImg;
    @Bind(R.id.tv_one)
    TextView mTvOne;
    @Bind(R.id.rl_one)
    RelativeLayout mRlOne;
    @Bind(R.id.tv_two)
    TextView mTvTwo;
    @Bind(R.id.rl_two)
    RelativeLayout mRlTwo;
    @Bind(R.id.tv_three)
    TextView mTvThree;
    @Bind(R.id.rl_three)
    RelativeLayout mRlThree;
    @Bind(R.id.tv_four)
    TextView mTvFour;
    @Bind(R.id.rl_four)
    RelativeLayout mRlFour;
    @Bind(R.id.et_num)
    EditText mEtNum;
    @Bind(R.id.rl_five)
    RelativeLayout mRlFive;
    @Bind(R.id.gridview)
    GridView mGridview;
    @Bind(R.id.rl_bg)
    RelativeLayout mRlBg;
    @Bind(R.id.tv_lookchosenum)
    TextView mTvLookchosenum;
    @Bind(R.id.rl_buy)
    RelativeLayout mRlBuy;
    @Bind(R.id.rl_buttom)
    RelativeLayout mRlButtom;
    private BuycaipiaoAdapter mBuycaipiaoAdapter;
    private List<BuyCaipiao> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buycaipiao);
        ButterKnife.bind(this);
        initEvent();
        obtainCaipiao();
        mGridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BuyCaipiao caipiao = (BuyCaipiao) parent.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", (Serializable) list);
                bundle.putString("number", (position + 1) + "");
                Intent intent = new Intent(ac, ChoseNumberActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 111);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<BuyCaipiao> result = (List<BuyCaipiao>) data.getExtras().getSerializable("list");//得到新Activity 关闭后返回的数据
    }

    private void obtainCaipiao() {
        for (int i = 0; i < 5; i++) {
            BuyCaipiao caipiao = new BuyCaipiao();
            caipiao.isChose = false;
            list.add(caipiao);
        }
        mBuycaipiaoAdapter = new BuycaipiaoAdapter(ac, list);
        mGridview.setAdapter(mBuycaipiaoAdapter);
    }

    private void initEvent() {
        mTvLookchosenum.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                //处理数据，只剩下选择号码的list

                ShowCaipiaoNumberDialog.tishiDialog(BuyCaipiaoActivity.this, 2, list);
            }
        });


        mRlBuy.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (list.size() == 0) {
                    ToastUtils.showToast(ac, "请选择彩票注数");
                } else {
                    for (BuyCaipiao caipiao : list) {
                        if (caipiao.isChose = false) {
                            ToastUtils.showToast(ac, "请选择彩票号码");
                            return;
                        }
                        //购买
                    }
                }
            }
        });

        mRlLeft.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                finish();
            }
        });
        mRlOne.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                resetButtom("one");
                handleCaipiao(5);
            }
        });
        mRlTwo.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                resetButtom("two");
                handleCaipiao(10);
            }
        });
        mRlThree.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                resetButtom("three");
                handleCaipiao(15);
            }
        });
        mRlFour.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                resetButtom("four");
                handleCaipiao(20);
            }
        });
        mEtNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    resetButtom("five");
                    handleCaipiao(0);
                } else {
                    resetButtom("five");
                    handleCaipiao(Integer.parseInt(s.toString()));
                }
            }
        });

    }

    private void resetButtom(String type) {
        mRlOne.setBackgroundResource(R.drawable.shap_zhunot);
        mTvOne.setTextColor(res.getColor(R.color.text98));
        mRlTwo.setBackgroundResource(R.drawable.shap_zhunot);
        mTvTwo.setTextColor(res.getColor(R.color.text98));
        mRlThree.setBackgroundResource(R.drawable.shap_zhunot);
        mTvThree.setTextColor(res.getColor(R.color.text98));
        mRlFour.setBackgroundResource(R.drawable.shap_zhunot);
        mTvFour.setTextColor(res.getColor(R.color.text98));
        mRlFive.setBackgroundResource(R.drawable.shap_zhunot);
        mEtNum.setTextColor(res.getColor(R.color.text98));
        switch (type) {
            case "one":
                mRlOne.setBackgroundResource(R.drawable.shap_login);
                mTvOne.setTextColor(res.getColor(R.color.white));
                break;
            case "two":
                mRlTwo.setBackgroundResource(R.drawable.shap_login);
                mTvTwo.setTextColor(res.getColor(R.color.white));
                break;
            case "three":
                mRlThree.setBackgroundResource(R.drawable.shap_login);
                mTvThree.setTextColor(res.getColor(R.color.white));
                break;
            case "four":
                mRlFour.setBackgroundResource(R.drawable.shap_login);
                mTvFour.setTextColor(res.getColor(R.color.white));
                break;
            case "five":
                mRlFive.setBackgroundResource(R.drawable.shap_login);
                mEtNum.setTextColor(res.getColor(R.color.white));
                break;
        }
    }

    private void handleCaipiao(int num) {
        List<BuyCaipiao> choselist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChose) {
                choselist.add(list.get(i));
            }
        }
        if (num < choselist.size()) {
            choselist.clear();
        }
        for (int j = 0; j < num - choselist.size(); j++) {
            BuyCaipiao caipiao = new BuyCaipiao();
            caipiao.isChose = false;
            choselist.add(caipiao);
        }
        list.clear();
        list.addAll(choselist);
        mBuycaipiaoAdapter.notifyDataSetChanged();
    }
}
