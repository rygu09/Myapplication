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

class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private List<NewsBean> mData;
    private Context mContext;
    private boolean mShowFooter = true;


    private OnItemClickListener mOnItemClickListener;


    NewsAdapter(Context context) {
        mContext = context;
    }

    public void setmDate(List<NewsBean> data) {
        this.mData = data;
//        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if(!mShowFooter) {
            return TYPE_ITEM;
        }
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.footer, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            NewsBean newsBean=mData.get(position);
            ((ItemViewHolder)holder).mTitle.setText(newsBean.getTitle());
            ((ItemViewHolder)holder).mDesc.setText(newsBean.getDigest());
            Glide.with(mContext).load(newsBean.getImgsrc()).into(((ItemViewHolder)holder).mNewsImg);
            }
    }


    @Override
    public int getItemCount() {
        int begin = mShowFooter?1:0;

        if(mData == null) {
            return begin;
        }
        return mData.size();
    }

    public NewsBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public void isShowFooter(boolean showFooter) {
        this.mShowFooter = showFooter;
    }

    public boolean isShowFooter() {
        return this.mShowFooter;
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
