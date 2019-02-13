package com.lottery.bossex.ui.me.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lottery.bossex.R;
import com.lottery.bossex.bean.OrderMsg;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private List<OrderMsg> list;
    private LayoutInflater inflater;

    public OrderAdapter(Context context, List<OrderMsg> list) {
        this.context = context;
        if (list == null) {
            this.list = new ArrayList<OrderMsg>();
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
            convertView = inflater.inflate(R.layout.item_orderdetail, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final OrderMsg home = list.get(position);
        for (int i = 0; i < 6; i++) {
            View view = inflater.inflate(R.layout.item_ball, null);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);//与父容器的左侧对齐
            lp.rightMargin = dip2px(context, 10) + i * dip2px(context, 24);
            view.setLayoutParams(lp);//设置布局参数
            vh.mRlBall.addView(view);
        }
        return convertView;
    }

    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    static class ViewHolder {
        @Bind(R.id.tv_ordercode)
        TextView mTvOrdercode;
        @Bind(R.id.tv_money)
        TextView mTvMoney;
        @Bind(R.id.li_zhong)
        LinearLayout mLiZhong;
        @Bind(R.id.tv_state)
        TextView mTvState;
        @Bind(R.id.rl_notzhong)
        RelativeLayout mRlNotzhong;
        @Bind(R.id.rl_ball)
        RelativeLayout mRlBall;
        @Bind(R.id.tv_zjmoney)
        TextView mTvZjmoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
