package com.lottery.bossex.ui.buy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.lottery.bossex.R;
import com.lottery.bossex.bean.BuyCaipiao;
import com.lottery.bossex.bean.CaipiaoNumber;
import com.lottery.bossex.tools.NullUtils;
import com.lottery.bossex.tools.ToastUtils;
import com.lottery.bossex.views.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songxiaotao on 2017/12/29.
 */

public class ChoseNumberAdapter extends RecyclerView.Adapter<ChoseNumberAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<BuyCaipiao> datas;
    private OnItemClickListener onItemClickListener;
    private List<CaipiaoNumber> toplist = new ArrayList<>();
    private List<CaipiaoNumber> downlist = new ArrayList<>();
    private ChoseNumberItemAdapter topAdapter;
    private ChoseNumberItemAdapter downAdapter;

    public ChoseNumberAdapter(Context context, List<BuyCaipiao> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chosecaipiao, parent, false);
        ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BuyCaipiao sf = datas.get(position);
        holder.itemView.setTag(position);
//        VolleyResponse.instance().getInternetImg(context, sf.image, holder.img, R.mipmap.icon_good);
        for (int i = 1; i < 65; i++) {
            CaipiaoNumber top = new CaipiaoNumber();
            top.isTop = true;
            top.number = i + "";
            top.isChoseNumber = false;
            top.chosenumber = 0;
            toplist.add(top);
        }
        topAdapter = new ChoseNumberItemAdapter(context, toplist);
        holder.gridview_top.setAdapter(topAdapter);
        for (int i = 1; i < 29; i++) {
            CaipiaoNumber top = new CaipiaoNumber();
            top.isTop = false;
            top.number = i + "";
            top.chosenumber = 0;
            top.isChoseNumber = false;
            downlist.add(top);
        }
        downAdapter = new ChoseNumberItemAdapter(context, downlist);
        holder.gridview_down.setAdapter(downAdapter);

        holder.gridview_top.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CaipiaoNumber cp = (CaipiaoNumber) parent.getItemAtPosition(position);
                if (cp.isChoseNumber) {
                    cp.chosenumber = cp.chosenumber - 1;
                    cp.isChoseNumber = false;
                    topAdapter.notifyDataSetChanged();
                } else {
                    if (Integer.parseInt(NullUtils.noNullHandle(cp.chosenumber).toString()) == 5) {
                        ToastUtils.showToast(context, "只能选择5个常规号码");
                    } else {
                        if (cp.isChoseNumber) {
                            cp.chosenumber = cp.chosenumber - 1;
                            cp.isChoseNumber = false;
                            topAdapter.notifyDataSetChanged();
                        } else {
                            cp.chosenumber = cp.chosenumber + 1;
                            cp.isChoseNumber = true;
                            topAdapter.notifyDataSetChanged();
                        }

                    }
                }
            }
        });

        holder.gridview_down.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CaipiaoNumber cp = (CaipiaoNumber) parent.getItemAtPosition(position);
                if (cp.isChoseNumber) {
                    cp.chosenumber = cp.chosenumber - 1;
                    cp.isChoseNumber = false;
                    topAdapter.notifyDataSetChanged();
                } else {
                    if (Integer.parseInt(NullUtils.noNullHandle(cp.chosenumber).toString()) == 1) {
                        ToastUtils.showToast(context, "只能选择1个特殊号码");
                    } else {
                        if (cp.isChoseNumber) {
                            cp.chosenumber = cp.chosenumber - 1;
                            cp.isChoseNumber = false;
                            topAdapter.notifyDataSetChanged();
                        } else {
                            cp.chosenumber = cp.chosenumber + 1;
                            cp.isChoseNumber = true;
                            topAdapter.notifyDataSetChanged();
                        }

                    }
                }
            }
        });
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
        MyGridView gridview_top, gridview_down;

        public ViewHolder(View itemView) {
            super(itemView);
            gridview_top = (MyGridView) itemView.findViewById(R.id.gridview_top);
            gridview_down = (MyGridView) itemView.findViewById(R.id.gridview_down);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;

    }

    interface OnItemClickListener {
        void onItemClick(View view, int tag);
    }

}