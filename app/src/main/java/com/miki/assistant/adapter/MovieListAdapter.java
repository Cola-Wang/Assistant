package com.miki.assistant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miki.assistant.R;
import com.miki.assistant.bean.MovieListBean;
import com.miki.assistant.utils.GlideUtils;

import java.util.List;

/**
 * 包名:      com.miki.assistant.adapter
 * 文件名:     MovieListAdapter.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/6 11:36
 * 描述:      电影列表适配器
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private Context context;
    private List<MovieListBean> mList;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MovieListAdapter(Context context, List<MovieListBean> mList) {
        this.context = context;
        this.mList = mList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_movie_list_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        MovieListBean bean = mList.get(position);
        holder.tv_title.setText(bean.getTitle());
        holder.tv_sub_text.setText(bean.getSubTitle());
        GlideUtils.loadImageViewCenterCrop(context, bean.getImg_url(), holder.iv_movie_bg);

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

        private TextView tv_sub_text;
        private TextView tv_title;
        private ImageView iv_movie_bg;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_sub_text = (TextView) itemView.findViewById(R.id.tv_sub_text);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv_movie_bg = (ImageView) itemView.findViewById(R.id.iv_movie_bg);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}
