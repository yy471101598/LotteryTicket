package com.lottery.bossex.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lottery.bossex.R;
import com.lottery.bossex.bean.Home;
import com.lottery.bossex.tools.ShadowUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

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
            convertView = inflater.inflate(R.layout.item_home, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final Home home = list.get(position);
        ShadowUtils.setShadowBackgroud(context, context.getResources(), vh.mRlBg);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_logo)
        ImageView mIvLogo;
        @Bind(R.id.tv_ordertime)
        TextView mTvOrdertime;
        @Bind(R.id.rl_buy)
        RelativeLayout mRlBuy;
        @Bind(R.id.rl_bg)
        RelativeLayout mRlBg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
