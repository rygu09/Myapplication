package ustc.var.com.myapplication001.news;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import ustc.var.com.myapplication001.R;
import ustc.var.com.myapplication001.bean.NewsBean;
import ustc.var.com.myapplication001.news.presenter.NewsDetailPresenter;
import ustc.var.com.myapplication001.news.presenter.NewsDetailPresenterImpl;
import ustc.var.com.myapplication001.news.view.NewsDetailView;
import ustc.var.com.myapplication001.util.ImageLoaderUtils;
import ustc.var.com.myapplication001.util.ToolUtils;

/**
 *
 * Created by GRY on 2017/10/22.
 */

public class NewsDetailActivity extends SwipeBackActivity implements NewsDetailView{
    private NewsBean mNews;
    private Toolbar toolbar;
    private HtmlTextView mTVNewsContent;
    private TextView mTextView;
    private ProgressBar mProgressBar;
    private SwipeBackLayout mSwipeBackLayout;

    private NewsDetailPresenter mNewsDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mTVNewsContent = (HtmlTextView) findViewById(R.id.htNewsContent);
        mTextView = (TextView) findViewById(R.id.tvTitle_detail);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(ToolUtils.getWidthInPx(this));
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        mNews = (NewsBean) getIntent().getSerializableExtra("news");

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitleEnabled(false);
        mTextView.setText(mNews.getTitle());

        ImageLoaderUtils.display(getApplicationContext(), (ImageView) findViewById(R.id.ivImage), mNews.getImgsrc());

        mNewsDetailPresenter = new NewsDetailPresenterImpl(getApplication(), this);
        mNewsDetailPresenter.loadNewsDetail(mNews.getDocid());
    }


    public void showNewsDetialContent(String newsDetailContent) {
        mTVNewsContent.setHtmlFromString(newsDetailContent, new HtmlTextView.LocalImageGetter());
    }


    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }


    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}
