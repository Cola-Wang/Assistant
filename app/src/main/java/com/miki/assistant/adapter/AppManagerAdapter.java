package com.miki.assistant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miki.assistant.R;
import com.miki.assistant.model.AppModel;

import java.util.List;

/**
 * 包名:      com.miki.assistant.adapter
 * 文件名:     AppManagerAdapter.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/7 16:20
 * 描述:      应用适配器
 */

public class AppManagerAdapter extends RecyclerView.Adapter<AppManagerAdapter.ViewHolder> {

    private Context context;
    private List<AppModel> mList;
    private LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public AppManagerAdapter(Context context, List mList) {
        this.context = context;
        this.mList = mList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_app_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        AppModel model = mList.get(position);
        holder.iv_app_icon.setBackground(model.getIcon());
        holder.tv_app_name.setText(model.getAppName());
        holder.tv_app_package_name.setText(model.getPackageName());
        holder.tv_app_size.setText(model.getAppSize());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_app_icon;
        private TextView tv_app_name;
        private TextView tv_app_package_name;
        private TextView tv_app_size;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_app_icon = (ImageView) itemView.findViewById(R.id.iv_app_icon);
            tv_app_name = (TextView) itemView.findViewById(R.id.tv_app_name);
            tv_app_package_name = (TextView) itemView.findViewById(R.id.tv_app_package_name);
            tv_app_size = (TextView) itemView.findViewById(R.id.tv_app_size);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}
