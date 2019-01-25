package com.lottery.bossex.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.lottery.bossex.R;
import com.lottery.bossex.bean.Home;

public class HomeAdapter extends BaseAdapter {
    private Context context;
    private List<Home> list;
    private LayoutInflater inflater;

    public HomeAdapter(Context context, List<Home> list) {
        this.context = context;
        if (list == null) {
            this.list = new ArrayList<Home>();
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
        final Home home = list.get(position);
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.iv_logo)
        ImageView mIvLogo;
        @Bind(R.id.tv_symbol)
        TextView mTvSymbol;
        @Bind(R.id.tv_money)
        TextView mTvMoney;
        @Bind(R.id.tv_unit)
        TextView mTvUnit;
        @Bind(R.id.rl_buy)
        RelativeLayout mRlBuy;
        @Bind(R.id.tv_time)
        TextView mTvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
