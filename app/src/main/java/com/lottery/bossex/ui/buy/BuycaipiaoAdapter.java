package com.lottery.bossex.ui.buy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lottery.bossex.R;
import com.lottery.bossex.bean.BuyCaipiao;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BuycaipiaoAdapter extends BaseAdapter {
    private Context context;
    private List<BuyCaipiao> list;
    private LayoutInflater inflater;

    public BuycaipiaoAdapter(Context context, List<BuyCaipiao> list) {
        this.context = context;
        if (list == null) {
            this.list = new ArrayList<BuyCaipiao>();
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
            convertView = inflater.inflate(R.layout.item_buycaipiao, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final BuyCaipiao home = list.get(position);
        if (home.isChose) {
            vh.mImgIcon.setBackgroundResource(R.mipmap.buy_chose);
        } else {
            vh.mImgIcon.setBackgroundResource(R.mipmap.buy_no);
        }
//        ShadowUtils.setShadowBackgroud(context, context.getResources(), vh.mRlBg);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.img_icon)
        ImageView mImgIcon;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}