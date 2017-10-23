package ustc.var.com.myapplication001.news.view;

import java.util.List;

import ustc.var.com.myapplication001.bean.NewsBean;


public interface NewsView {

    void showProgress();

    void addNews(List<NewsBean> newsList);

    void hideProgress();

    void showLoadFailMsg();
}
