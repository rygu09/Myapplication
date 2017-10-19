package ustc.var.com.myapplication001.news;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import ustc.var.com.myapplication001.bean.NewsBean;
import ustc.var.com.myapplication001.common.Urls;


public class NewsFragment extends Fragment  {

    private static final String Tag = "NewsFragment";
    public String URL;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private NewsAdapter mAdapter;
    private List<NewsBean> mData;

    private Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_news,null);
        setOkHttp();
        mHandler=new Handler(Looper.getMainLooper());
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_widget_news);
//        mSwipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }


    private void setOkHttp() {
        URL=getUrl(0);
        OkHttpClient mOkHttpClient=new OkHttpClient();
        final Request request=new Request.Builder().url(URL).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String str = response.body().string();
                mData = NewsJsonUtils.readJsonNewsBeans(str,Urls.TOP_ID);
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
        mRecyclerView = getActivity().findViewById(R.id.recycle_view_news);
        mAdapter=new NewsAdapter(mData,getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mAdapter);
    }



    private String getUrl(int pageIndex) {
        StringBuffer sb = new StringBuffer();
        sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
        sb.append("/").append(pageIndex).append(Urls.END_URL);
        return sb.toString();
    }
}
