package ustc.var.com.myapplication001;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String Tag = "MainActivity";
    public static final String URL="http://api.laifudao.com/open/tupian.json";

    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private ImageAdapter mAdapter;
    private List<ImageBean> mData;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOkHttp();
        mHandler=new Handler(getMainLooper());
//        Log.d(Tag,"主线程"+Thread.currentThread().getId());
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
                String str=response.body().string();
                mData = ImageJsonUtils.readJsonImageBeans(str);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        setRecyclerView();
                    }
                });

//                Log.d(Tag,"当前线程"+Thread.currentThread().getId());

            }
        });
    }

    private void setRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mStaggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mAdapter=new ImageAdapter(mData,MainActivity.this);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
