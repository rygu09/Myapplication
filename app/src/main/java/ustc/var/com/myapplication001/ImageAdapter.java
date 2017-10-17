package ustc.var.com.myapplication001;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 *
 * Created by GRY on 2017/10/17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ItemViewHolder> {

    List<ImageBean> mData;
    private Context mContext;

    public ImageAdapter(List<ImageBean> data, Context context) {
        this.mData = data;
        this.mContext = context;
    }

    //    public void setmDate(List<ImageBean> data) {
//        this.mData = data;
//        this.notifyDataSetChanged();
//    }

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
        Glide.with(mContext).load(imageBean.getThumburl()).into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        if(mData == null) {
            return 0;
        }
        return mData.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public ImageView mImage;
        public ItemViewHolder(View itemView) {
            super(itemView);
            mTitle=itemView.findViewById(R.id.tvTitle);
            mImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
