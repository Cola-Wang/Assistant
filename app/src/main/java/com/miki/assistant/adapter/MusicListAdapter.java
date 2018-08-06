package com.miki.assistant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miki.assistant.R;
import com.miki.assistant.bean.MusicListBean;
import com.miki.assistant.utils.GlideUtils;

import java.util.List;

/**
 * 包名:      com.miki.assistant.adapter
 * 文件名:     MusicListAdapter.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/6 10:21
 * 描述:      音乐列表的适配器
 */

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<MusicListBean> mList;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MusicListAdapter(Context context, List<MusicListBean> mList) {
        this.context = context;
        this.mList = mList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_music_list_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        MusicListBean bean = mList.get(position);
        GlideUtils.loadImageViewCenterCrop(context, bean.getUrl(), holder.iv_img);
        holder.tv_text.setText(bean.getTitle());

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

    //ViewHolder需要继承
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_img;
        private TextView tv_text;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tv_text = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}
