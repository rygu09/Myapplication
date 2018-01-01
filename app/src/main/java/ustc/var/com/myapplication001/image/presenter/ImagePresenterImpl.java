package ustc.var.com.myapplication001.image.presenter;


import java.util.List;

import ustc.var.com.myapplication001.bean.ImageBean;
import ustc.var.com.myapplication001.image.model.ImageModel;
import ustc.var.com.myapplication001.image.model.ImageModelImpl;
import ustc.var.com.myapplication001.image.view.ImageView;

public class ImagePresenterImpl implements ImagePresenter, ImageModelImpl.OnLoadImageListListener {

    private ImageModel mImageModel;
    private ImageView mImageView;

    public ImagePresenterImpl(ImageView imageView) {
        this.mImageModel = new ImageModelImpl();
        this.mImageView = imageView;
    }

    @Override
    public void loadImageList() {
        mImageView.showProgress();
        mImageModel.loadImageList(this);
    }

    @Override
    public void onSuccess(List<ImageBean> list) {
        mImageView.addImages(list);
        mImageView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mImageView.hideProgress();
        mImageView.showLoadFailMsg();
    }
}
