package ustc.var.com.myapplication001.image;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ustc.var.com.myapplication001.R;
import ustc.var.com.myapplication001.bean.ImageBean;
import ustc.var.com.myapplication001.util.ToolUtils;

/**
 *
 * Created by GRY on 2017/10/17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ItemViewHolder> {

    List<ImageBean> mData;
    private Context mContext;
    private int mMaxWidth;

    private OnItemClickListener mOnItemClickListener;

    public ImageAdapter(List<ImageBean> data, Context context) {
        this.mData = data;
        this.mContext = context;
        mMaxWidth = ToolUtils.getWidthInPx(mContext) /2 -10;
    }

        public void setmDate(List<ImageBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image,parent,false);
        ItemViewHolder itemViewHolder=new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ImageBean imageBean = mData.get(position);
        holder.mTitle.setText(imageBean.getTitle());
        float scale=imageBean.getWidth()/mMaxWidth;
        int height= (int) (imageBean.getHeight()/scale);
        holder.mImage.setLayoutParams(new LinearLayout.LayoutParams(mMaxWidth, height));
        Glide.with(mContext).load(imageBean.getThumburl()).into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        if(mData == null) {
            return 0;
        }
        return mData.size();
    }

    public ImageBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mTitle;
        public ImageView mImage;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTitle=itemView.findViewById(R.id.tvTitle);
            mImage = itemView.findViewById(R.id.ivImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, this.getPosition());
            }
        }
    }
}
