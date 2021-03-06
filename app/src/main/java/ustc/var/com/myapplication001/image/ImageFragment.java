package ustc.var.com.myapplication001.image;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import ustc.var.com.myapplication001.R;
import ustc.var.com.myapplication001.bean.ImageBean;


public class ImageFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String Tag = "ImageFragment";
    public static final String URL="http://api.laifudao.com/open/tupian.json";

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private ImageAdapter mAdapter;
    private List<ImageBean> mData;

    private Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_image,null);
        setOkHttp();
        mHandler=new Handler(Looper.getMainLooper());
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_refresh_widget_image);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }


    private void setOkHttp() {
        OkHttpClient mOkHttpClient=new OkHttpClient();
        final Request request=new Request.Builder().url(URL).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String str = response.body().string();
                mData = ImageJsonUtils.readJsonImageBeans(str);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        setRecyclerView();
                    }
                });
            }
        });

    }

    private void setRecyclerView() {
        mRecyclerView = getActivity().findViewById(R.id.recycle_view_image);
        mStaggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mAdapter=new ImageAdapter(mData,getContext());
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onRefresh() {

    }
}
