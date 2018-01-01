i新闻客户端

界面图

![image](https://github.com/rygu09/image_cache/blob/master/0_%E5%89%AF%E6%9C%AC.png)
![image](https://github.com/rygu09/image_cache/blob/master/1_%E5%89%AF%E6%9C%AC.png)

1. MVP架构，充分解耦和

2. Tablayout + Viewpager实现模块间切换
 
3. SwipeRefreshLayout + RecyclerView + cardview 实现新闻列表展示和下拉刷新

4. OkHttp + Gson + Glide实现网络数据获取和解析

5. 新闻内容界面使用CollapsingToolbarLayout

6. 上滑时隐藏Toolbar：

```
//RecyclerView中设置
app:layout_behavior="@string/appbar_scrolling_view_behavior"

//Toolbar中设置
app:layout_scrollFlags="scroll|enterAlways"
```

