package com.lottery.bossex.ui.lottery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lottery.bossex.R;
import com.lottery.bossex.bean.Lottery;
import com.lottery.bossex.tools.ShadowUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LotteryAdapter extends BaseAdapter {
    private Context context;
    private List<Lottery> list;
    private LayoutInflater inflater;

    public LotteryAdapter(Context context, List<Lottery> list) {
        this.context = context;
        if (list == null) {
            this.list = new ArrayList<Lottery>();
        } else {
            this.list = list;
        }
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_lottery, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final Lottery home = list.get(position);
        ShadowUtils.setShadowBackgroud(context, context.getResources(), vh.mRlBg);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_logo)
        ImageView mIvLogo;
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
        @Bind(R.id.tv_five)
        TextView mTvFive;
        @Bind(R.id.rl_five)
        RelativeLayout mRlFive;
        @Bind(R.id.tv_six)
        TextView mTvSix;
        @Bind(R.id.rl_six)
        RelativeLayout mRlSix;
        @Bind(R.id.tv_ordercode)
        TextView mTvOrdercode;
        @Bind(R.id.li_look)
        LinearLayout mLiLook;
        @Bind(R.id.rl_bg)
        RelativeLayout mRlBg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
