package ustc.var.com.myapplication001.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ustc.var.com.myapplication001.R;
import ustc.var.com.myapplication001.bean.NewsBean;

/**
 *
 * Created by GRY on 2017/10/19.
 */

class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ItemViewHolder>{
    private List<NewsBean> mData;
    private Context mContext;

//    private OnItemClickListener mOnItemClickListener;


    NewsAdapter(List<NewsBean> data, Context context) {
        mData = data;
        mContext = context;
    }

    @Override
    public NewsAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ItemViewHolder holder, int position) {
        NewsBean newsBean=mData.get(position);
        holder.mTitle.setText(newsBean.getTitle());
        holder.mDesc.setText(newsBean.getDigest());
        Glide.with(mContext).load(newsBean.getImgsrc()).into(holder.mNewsImg);
    }

    @Override
    public int getItemCount() {
        if(mData == null) {
            return 0;
        }
        return mData.size();
    }

    public NewsBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mDesc;
        ImageView mNewsImg;

        ItemViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tvTitle);
            mDesc = itemView.findViewById(R.id.tvDesc);
            mNewsImg =  itemView.findViewById(R.id.ivNews);
//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View v) {
//            if(mOnItemClickListener != null) {
//                mOnItemClickListener.onItemClick(v, this.getPosition());
//            }
//        }
    }
}
