package ustc.var.com.myapplication001;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class ImageFragment extends Fragment {

    private static final String Tag = "ImageFragment";
    public static final String URL="http://api.laifudao.com/open/tupian.json";

    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private ImageAdapter mAdapter;
    private List<ImageBean> mData;

    private Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_image);
//        setOkHttp();
//        mHandler=new Handler(getMainLooper());
////        Log.d(Tag,"主线程"+Thread.currentThread().getId());
//    }
//
//
//    private void setOkHttp() {
//        OkHttpClient mOkHttpClient=new OkHttpClient();
//        final Request request=new Request.Builder().url(URL).build();
//        mOkHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                String str=response.body().string();
//                mData = ImageJsonUtils.readJsonImageBeans(str);
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        setRecyclerView();
//                    }
//                });
//
////                Log.d(Tag,"当前线程"+Thread.currentThread().getId());
//
//            }
//        });
//    }
//
//    private void setRecyclerView() {
//        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
//        mStaggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//        mAdapter=new ImageAdapter(mData,ImageFragment.this);
//        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);
//    }
}
