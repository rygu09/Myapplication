package ustc.var.com.myapplication001.image;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import ustc.var.com.myapplication001.bean.ImageBean;

/**
 *
 * Created by GRY on 2017/10/17.
 */

public class ImageJsonUtils {

    public static List<ImageBean> readJsonImageBeans(String res){
        List<ImageBean> imageBeanList=new ArrayList<>();
        JsonParser jsonParser=new JsonParser();
        JsonArray jsonArray=jsonParser.parse(res).getAsJsonArray();
        for(int i=1;i<jsonArray.size();i++){
            JsonObject jsonObject=jsonArray.get(i).getAsJsonObject();
            Gson gson=new Gson();
            ImageBean imageBean=gson.fromJson(jsonObject,ImageBean.class);
            imageBeanList.add(imageBean);
        }

        return imageBeanList;
    }

}
