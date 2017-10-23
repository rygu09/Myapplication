package ustc.var.com.myapplication001.news.presenter;

import android.content.Context;

import ustc.var.com.myapplication001.bean.NewsDetailBean;
import ustc.var.com.myapplication001.news.model.NewsModel;
import ustc.var.com.myapplication001.news.model.NewsModelImpl;
import ustc.var.com.myapplication001.news.model.OnLoadNewsDetailListener;
import ustc.var.com.myapplication001.news.view.NewsDetailView;


public class NewsDetailPresenterImpl implements NewsDetailPresenter, OnLoadNewsDetailListener {

    private Context mContent;
    private NewsDetailView mNewsDetailView;
    private NewsModel mNewsModel;

    public NewsDetailPresenterImpl(Context mContent, NewsDetailView mNewsDetailView) {
        this.mContent = mContent;
        this.mNewsDetailView = mNewsDetailView;
        mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNewsDetail(final String docId) {
        mNewsDetailView.showProgress();
        mNewsModel.loadNewsDetail(docId, this);
    }

    @Override
    public void onSuccess(NewsDetailBean newsDetailBean) {
        if (newsDetailBean != null) {
            mNewsDetailView.showNewsDetialContent(newsDetailBean.getBody());
        }
        mNewsDetailView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsDetailView.hideProgress();
    }
}
