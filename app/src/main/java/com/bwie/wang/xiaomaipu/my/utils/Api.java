package com.bwie.wang.xiaomaipu.my.utils;

import com.bwie.wang.xiaomaipu.mvp.presenter.home.NavChildsBean;
import com.bwie.wang.xiaomaipu.my.bean.GoodsList.GoodsListBean;
import com.bwie.wang.xiaomaipu.my.bean.GoodsList.SyncShoppingBean;
import com.bwie.wang.xiaomaipu.my.bean.circle.CircleBean;
import com.bwie.wang.xiaomaipu.my.bean.home.HomeBannerBean;
import com.bwie.wang.xiaomaipu.my.bean.home.HomeCommodityBean;
import com.bwie.wang.xiaomaipu.my.bean.home.InfoBean;
import com.bwie.wang.xiaomaipu.my.bean.home.nav.NavBean;
import com.bwie.wang.xiaomaipu.my.bean.home.nav.NavChildBean;
import com.bwie.wang.xiaomaipu.my.bean.loginorregister.LoginBean;
import com.bwie.wang.xiaomaipu.my.bean.loginorregister.RegisterBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * date:2018/12/29.
 *
 * @author 王丙均
 * @param
 * @parampage
 * @return Return
 * @javadoc
 */


public interface Api {

    /**登陆*/
    @POST("user/v1/login")
    Observable<LoginBean> getLogin(@Query("phone") String phone, @Query("pwd")String pwd);
    /**注册*/
    @POST("user/v1/register")
    Observable<RegisterBean> getRegister(@Query("phone") String phone, @Query("pwd")String pwd);

    /**这是首页的*/
    @GET("commodity/v1/bannerShow")
    Observable<HomeBannerBean> getBanner();
    /**展示数据
     */
    @GET("commodity/v1/commodityList")
    Observable<HomeCommodityBean> getCommodityList();
//一级导航

    @GET("commodity/v1/findFirstCategory")
    Observable<NavBean> getNavList();
//二级导航

    @GET("commodity/v1/findSecondCategory")
    Observable<NavChildBean> getNavTwoList(@Query("firstCategoryId")String firstCategoryId);
//二级导航展示

    @GET("commodity/v1/findCommodityByCategory?page=1&count=22")
    Observable<NavChildsBean> getNavTwosList(@Query("categoryId")String categoryId);
//    http://172.17.8.100/small/commodity/v1/findCommodityByCategory?categoryId=1001004002&page=1&count=5


//getCircle圈子



    @GET("circle/v1/findCircleList?userId=1010&sessionId=15320748258726&page=1&count=10")
    Observable<CircleBean> getCircle();

//详情

    @GET("commodity/v1/findCommodityDetailsById?userId=18&sessionId=15320748258726")
    Observable<InfoBean> queryGoodsByPid(@Query("commodityId")int commodityId);



//    查询购物车

    @GET("order/verify/v1/findShoppingCart?userId=299&sessionId=1547119469374299&commodityId=13")
    Observable<GoodsListBean> getGoodsListBea();
//    @GET("order/verify/v1/findShoppingCart")
//    Observable<GoodsListBean> getGoodsListBea(@Query("userId")int userId,@Query("sessionId")String sessionId);
//    同步购物车

    @PUT("order/verify/v1/syncShoppingCart")
    Observable<SyncShoppingBean> getSyncShoppingBea(@Part("userId") RequestBody userId, @Part("sessionId")RequestBody sessionId);







}
