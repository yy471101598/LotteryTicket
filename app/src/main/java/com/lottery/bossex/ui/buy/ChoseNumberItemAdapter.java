package com.lottery.bossex.ui.buy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lottery.bossex.R;
import com.lottery.bossex.bean.CaipiaoNumber;
import com.lottery.bossex.tools.ShadowUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChoseNumberItemAdapter extends BaseAdapter {
    private Context context;
    private List<CaipiaoNumber> list;
    private LayoutInflater inflater;

    public ChoseNumberItemAdapter(Context context, List<CaipiaoNumber> list) {
        this.context = context;
        if (list == null) {
            this.list = new ArrayList<CaipiaoNumber>();
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
            convertView = inflater.inflate(R.layout.item_chosecaipiao_ball, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final CaipiaoNumber home = list.get(position);
        if (home.isTop) {
            if (home.isChoseNumber) {
                vh.mRlBg.setBackgroundResource(R.drawable.shap_ball_top);
            } else {
                vh.mRlBg.setBackgroundResource(R.drawable.shap_ball_no);
            }
        } else {
            if (home.isChoseNumber) {
                vh.mRlBg.setBackgroundResource(R.drawable.shap_ball_down);
            } else {
                vh.mRlBg.setBackgroundResource(R.drawable.shap_ball_no);
            }
        }
        ShadowUtils.setShadowBackgroud(context, context.getResources(), vh.mRlBg);
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tv_num)
        TextView mTvNum;
        @Bind(R.id.rl_bg)
        RelativeLayout mRlBg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}