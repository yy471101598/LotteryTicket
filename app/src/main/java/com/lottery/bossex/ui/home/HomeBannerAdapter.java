package com.lottery.bossex.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lottery.bossex.R;
import com.lottery.bossex.bean.HomeBannerMsg;
import com.lottery.bossex.http.VolleyResponse;

import java.util.List;

/**
 * Created by songxiaotao on 2017/12/29.
 */

public class HomeBannerAdapter extends RecyclerView.Adapter<HomeBannerAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<HomeBannerMsg> datas;
    private OnItemClickListener onItemClickListener;

    public HomeBannerAdapter(Context context, List<HomeBannerMsg> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_homebanner, parent, false);
        ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HomeBannerMsg sf = datas.get(position);

        VolleyResponse.instance().getInternetImg(context, sf.img, holder.img, R.mipmap.messge_nourl);
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(view, (Integer) view.getTag());
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView img;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (SimpleDraweeView) itemView.findViewById(R.id.img);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;

    }

    interface OnItemClickListener {
        void onItemClick(View view, int tag);
    }

}