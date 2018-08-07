package com.miki.assistant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miki.assistant.R;
import com.miki.assistant.bean.LifeStyleBean;

import java.util.List;

/**
 * 包名:      com.miki.assistant.adapter
 * 文件名:     LifeStyleAdapter.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/7 10:45
 * 描述:      生活指数适配器
 */

public class LifeStyleAdapter extends RecyclerView.Adapter<LifeStyleAdapter.ViewHolder> {

    private Context context;
    private List<LifeStyleBean> mList;
    private LayoutInflater inflater;

    public LifeStyleAdapter(Context context, List mList) {
        this.context = context;
        this.mList = mList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_lifestyle_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LifeStyleBean bean = mList.get(position);
        holder.iv_icon.setBackgroundResource(R.mipmap.ic_launcher);
        holder.tv_brf.setText(bean.getBrf());
        holder.tv_txt.setText(bean.getTxt());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_icon;
        private TextView tv_brf;
        private TextView tv_txt;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv_brf = (TextView) itemView.findViewById(R.id.tv_brf);
            tv_txt = (TextView) itemView.findViewById(R.id.tv_txt);
        }
    }
}
