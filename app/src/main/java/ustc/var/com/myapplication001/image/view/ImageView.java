package ustc.var.com.myapplication001.image.view;


import java.util.List;

import ustc.var.com.myapplication001.bean.ImageBean;

public interface ImageView {
    void addImages(List<ImageBean> list);
    void showProgress();
    void hideProgress();
    void showLoadFailMsg();
}
