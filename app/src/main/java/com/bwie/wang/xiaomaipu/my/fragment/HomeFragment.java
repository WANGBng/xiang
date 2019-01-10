package com.bwie.wang.xiaomaipu.my.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bwie.wang.xiaomaipu.R;
import com.bwie.wang.xiaomaipu.mvp.presenter.home.HomeBannerPresenter;
import com.bwie.wang.xiaomaipu.mvp.presenter.home.NavChildsBean;
import com.bwie.wang.xiaomaipu.mvp.presenter.home.NavPresenter;
import com.bwie.wang.xiaomaipu.mvp.view.home.HomeView;
import com.bwie.wang.xiaomaipu.mvp.view.home.NavView;
import com.bwie.wang.xiaomaipu.my.adapter.home.Home_Hot_Line_Adapter;
import com.bwie.wang.xiaomaipu.my.adapter.home.Home_Magic_Line_Adapter;
import com.bwie.wang.xiaomaipu.my.adapter.home.Home_Quality_Line_Adapter;
import com.bwie.wang.xiaomaipu.my.adapter.home.nav.NavAdapter;
import com.bwie.wang.xiaomaipu.my.adapter.home.nav.NavChildAdapter;
import com.bwie.wang.xiaomaipu.my.adapter.home.nav.NavChildsAdapter;
import com.bwie.wang.xiaomaipu.my.bean.home.HomeBannerBean;
import com.bwie.wang.xiaomaipu.my.bean.home.HomeCommodityBean;
import com.bwie.wang.xiaomaipu.my.bean.home.nav.NavBean;
import com.bwie.wang.xiaomaipu.my.bean.home.nav.NavChildBean;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * date:2018/12/29.
 *
 * @author 王丙均
 */

public class HomeFragment extends Fragment implements HomeView, NavView {
    public static final String BUNDLE_TITLE = "title";
    boolean isCheck = true;
    @BindView(R.id.re_all)
    RelativeLayout reAll;
    @BindView(R.id.re_recycler_view_re)
    RelativeLayout reRecyclerViewRe;

    //点击隐藏显示
    private boolean isVisible = false;
    View view;
    Unbinder unbinder;
    @BindView(R.id.banner)
    FlyBanner banner;

    int h;
    @BindView(R.id.menu_n)
    ImageView menuN;
    @BindView(R.id.ed_sou)
    EditText edSou;
    @BindView(R.id.search_n)
    ImageView searchN;
    @BindView(R.id.re_xiao_xin_pin)
    ImageView reXiaoXinPin;
    @BindView(R.id.re_)
    ImageView re;
    @BindView(R.id.re_recyclerView)
    RecyclerView reRecyclerView;
    @BindView(R.id.mo_li_shi_shang)
    ImageView moLiShiShang;
    @BindView(R.id.mo_)
    ImageView mo;
    @BindView(R.id.mo_recyclerView)
    RecyclerView moRecyclerView;
    @BindView(R.id.pin_)
    ImageView pin;
    @BindView(R.id.sheng_recyclerView)
    RecyclerView shengRecyclerView;


    private List<String> images = new ArrayList<>();
    HomeBannerPresenter homeBannerPresenter;
    NavPresenter navPresenter;
    Home_Hot_Line_Adapter home_hot_line_adapter;
    Home_Magic_Line_Adapter home_magic_line_adapter;
    Home_Quality_Line_Adapter home_quality_line_adapter;

    //    一级导航的rec
    @BindView(R.id.linear_rec)
    RecyclerView linearRec;

    //er级导航的rec
    @BindView(R.id.linear_child_rec)
    RecyclerView linearChildRec;
    //首页导航的布局
    @BindView(R.id.nav_linear)
    LinearLayout navLinear;
    //    home所有的布局,导航除外
    @BindView(R.id.home_all)
    LinearLayout homeAll;
    //    导航孩子的孩子的商品的REC
    @BindView(R.id.nav_childs_rec)
    RecyclerView navChildsRec;
    //    导航孩子的孩子的商品的布局
    @BindView(R.id.nav_childs_linear)
    LinearLayout navChildsLinear;

    NavAdapter navAdapter;
    NavChildAdapter navChildAdapter;
    NavChildsAdapter navChildsAdapter;

    //这是轮播图的handler
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                Calendar instance = Calendar.getInstance();
                int hour = instance.get(Calendar.HOUR_OF_DAY);
                int tohour = 2;
                if (hour % 2 == 0) {
                    h = hour + tohour;
                } else {
                    h = hour - 1 + tohour;
                }
                handler.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };//上面是轮播图的handler

    boolean isBoolen = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.home_fragment_layout, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        //banner的Presenter
        homeBannerPresenter = new HomeBannerPresenter();
        homeBannerPresenter.attachView(this);
        homeBannerPresenter.getHomeBanner();
        //首页数据的Presenter
        homeBannerPresenter.getHomeCommodity();
        //导航
        navPresenter = new NavPresenter();
        navPresenter.attachView(this);
        navPresenter.getNavBea();
        navPresenter.getNavChildBea("1001002");
        navPresenter.getNavChildsBea("1001004002");

        unbinder = ButterKnife.bind(this, view);


        return view;
    }


    public static HomeFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * 首页的点击事件
     */
    @OnClick({R.id.menu_n, R.id.search_n, R.id.re_, R.id.mo_, R.id.pin_, R.id.re_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.menu_n:

                if (!isVisible) {
                    isVisible = true;
                    navLinear.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "主人您来啦!小多好想你", Toast.LENGTH_SHORT).show();

                } else {
                    navLinear.setVisibility(View.GONE);
                    isVisible = false;
                    Toast.makeText(getActivity(), "主人小多会想你的", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.search_n:
                //点击时将隐藏的输入框显示/隐藏
                if (!isVisible) {
                    isVisible = true;
                    edSou.setVisibility(View.VISIBLE);

                } else {
                    edSou.setVisibility(View.GONE);
                    isVisible = false;
                }
                Toast.makeText(getActivity(), "搜索按钮", Toast.LENGTH_SHORT).show();

                break;
            case R.id.re_:
                Toast.makeText(getActivity(), "热线新品更多", Toast.LENGTH_SHORT).show();
                // if (!isVisible) {
                //     homeAll.setVisibility(View.GONE);
//                    navChildsLinear.setVisibility(View.VISIBLE);
                //      Toast.makeText(getActivity(), "欢迎来到热线新品更多", Toast.LENGTH_SHORT).show();

                // }
                break;
            case R.id.mo_:
                Toast.makeText(getActivity(), "魔力时尚更多", Toast.LENGTH_SHORT).show();
//                if (!isVisible) {
//                    isVisible = true;
//                    homeAll.setVisibility(View.GONE);
//                    navChildsLinear.setVisibility(View.VISIBLE);
//                    Toast.makeText(getActivity(), "欢迎来到魔力时尚更多", Toast.LENGTH_SHORT).show();

                //  }
                break;
            case R.id.pin_:
                Toast.makeText(getActivity(), "品质生活更多", Toast.LENGTH_SHORT).show();
//                if (!isVisible) {
//                    isVisible = true;
//                    homeAll.setVisibility(View.GONE);
//                    navChildsLinear.setVisibility(View.VISIBLE);
//                    Toast.makeText(getActivity(), "欢迎来到品质生活更多", Toast.LENGTH_SHORT).show();
//
//                }
                break;
            case R.id.re_all:
//                Toast.makeText(getActivity(), "品质生活更多", Toast.LENGTH_SHORT).show();
//                if (!isVisible) {
//                    isVisible = true;
//                    homeAll.setVisibility(View.GONE);
//                    navChildsLinear.setVisibility(View.VISIBLE);
//                    Toast.makeText(getActivity(), "欢迎来到品质生活更多", Toast.LENGTH_SHORT).show();
//
//                }
                // 影藏软键盘
                InputMethodManager imm = (InputMethodManager) getActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edSou.getWindowToken(), 0);
                break;
            default:
                break;
        }
    }

    //轮播图
    @Override
    public void onHomeBannerSuccess(HomeBannerBean bannerBean) {
        //轮播图
        List<HomeBannerBean.ResultBean> result = bannerBean.getResult();
        for (int i = 0; i < result.size(); i++) {
            images.add(result.get(i).getImageUrl());
        }
        banner.setImagesUrl(images);

    }

    /**
     * 自定义的RecyclerView的item这是线性布局的
     */
    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }

    /**
     * 自定义的RecyclerView的item这是网格布局的
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        //列数
        private int spanCount;
        //间隔
        private int spacing;
        //是否包含边缘
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            //这里是关键，需要根据你有几列来判断
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                // spacing - column * ((1f / spanCount) * spacing)
                outRect.left = spacing - column * spacing / spanCount;
                // (column + 1) * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount;
                // top edge
                if (position < spanCount) {
                    outRect.top = spacing;
                } // item bottom
                outRect.bottom = spacing;
            } else { // column * ((1f / spanCount) * spacing)
                outRect.left = column * spacing / spanCount;
                // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    // item top
                    outRect.top = spacing;
                }
            }
        }
    }

    /**
     * 首页进行数据解析和展示
     */
    @Override
    public void onHomeCommoditySuccess(HomeCommodityBean.ResultBean homeCommodityBean) {

        int space = 20;
//      热销新品
        List<HomeCommodityBean.ResultBean.RxxpBean.CommodityListBean> commodityList = homeCommodityBean.getRxxp().get(0).getCommodityList();
        RecyclerView.LayoutManager gridLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        reRecyclerView.setLayoutManager(gridLayoutManager);

        home_hot_line_adapter = new Home_Hot_Line_Adapter(getActivity(), commodityList);
        reRecyclerView.setAdapter(home_hot_line_adapter);


        // 3 columns
        int spanCount = 3;
        // 50px
        int spacing = 20;
        final boolean includeEdge = false;
        reRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        // reRecyclerView.addItemDecoration(new SpacesItemDecoration(space));

//      魔力时尚
        List<HomeCommodityBean.ResultBean.MlssBean.CommodityListBeanXX> mlss = homeCommodityBean.getMlss().get(0).getCommodityList();
        RecyclerView.LayoutManager mlsh = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        moRecyclerView.setLayoutManager(mlsh);
        home_magic_line_adapter = new Home_Magic_Line_Adapter(getActivity(), mlss);
        moRecyclerView.setAdapter(home_magic_line_adapter);
        moRecyclerView.addItemDecoration(new SpacesItemDecoration(space));
//
//      品质生活
        List<HomeCommodityBean.ResultBean.PzshBean.CommodityListBeanX> commodityList1 = homeCommodityBean.getPzsh().get(0).getCommodityList();
        GridLayoutManager pzsh = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        shengRecyclerView.setLayoutManager(pzsh);
        home_quality_line_adapter = new Home_Quality_Line_Adapter(getActivity(), commodityList1);
        shengRecyclerView.setAdapter(home_quality_line_adapter);
        int spanCount1 = 2;
        // 50px
        int spacing1 = 10;
        boolean includeEdge1 = false;
        shengRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount1, spacing1, includeEdge1));

//热销
        home_hot_line_adapter.setOnHomeHotLineClickLister(new Home_Hot_Line_Adapter.OnHomeHotLineAdapterClickLister() {
            @Override
            public void onHomeHotLineAdapterItemClickLister(int position) {
                // homeBannerPresenter.getHomeCommodity();


            }
        });
//        魔力时尚
        home_magic_line_adapter.setOnHomeMagicLinClickLister(new Home_Magic_Line_Adapter.OnHomeMagicLinClickLister() {
            @Override
            public void onHomeMagicLinClickLister(int position) {

            }
        });
//      品质生活
        home_quality_line_adapter.setOnHomeQualityLineAdapterClickLister(new Home_Quality_Line_Adapter.OnHomeQualityLineAdapterClickLister() {
            @Override
            public void onHome_Quality_Line_AdapterItemClickLister(int position) {

            }
        });


    }

    /**
     * 首页导航
     */
    @Override
    public void onNavBeanSuccess(NavBean navBean) {
//      首页导航
        final List<NavBean.ResultBean> results = navBean.getResult();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        linearRec.setLayoutManager(layoutManager);
        navAdapter = new NavAdapter(getActivity(), results);
        linearRec.setAdapter(navAdapter);

        //点击传值,
        navAdapter.setOnNavItemClickLister(new NavAdapter.OnNavItemClickLister() {
            @Override
            public void onNavItemClick(final NavAdapter.ViewHolder holder, final int position) {
                navPresenter.getNavChildBea(results.get(position).getId());

            }
        });
    }

    @Override
    public void onNavBeanFailed(String navMsg) {
        Log.d("导航获取失败", "onFailed: " + navMsg);
    }

    /**
     * q
     * 导航的孩子展示
     */
    @Override
    public void onNavChildBeanSuccess(NavChildBean navChildBean) {
        final List<NavChildBean.ResultBean> result = navChildBean.getResult();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        linearChildRec.setLayoutManager(layoutManager);
        navChildAdapter = new NavChildAdapter(getActivity(), result);
        linearChildRec.setAdapter(navChildAdapter);
        //导航子条目适配器的监听
        navChildAdapter.setOnNavChildItemClickLister(new NavChildAdapter.OnNavChildItemClickLister() {
            //            点击事件的传id值,并且展示数据
            @Override
            public void onNavChildItemClick(int position) {
                homeAll.setVisibility(View.GONE);
                navChildsLinear.setVisibility(View.VISIBLE);
                navPresenter.getNavChildsBea(result.get(position).getId());

            }
        });
    }

    @Override
    public void onNavChildBeanFailed(String navCMsg) {
        Log.d("导航子条目获取失败", "onFailed: " + navCMsg);
    }

    /**
     * 导航的孩子的孩子
     */
    @Override
    public void onNavChildsBeanSuccess(NavChildsBean navChildsBean) {
        List<NavChildsBean.ResultBean> result = navChildsBean.getResult();

        RecyclerView.LayoutManager gGridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        navChildsRec.setLayoutManager(gGridLayoutManager);

        navChildsAdapter = new NavChildsAdapter(getActivity(), result);
        navChildsRec.setAdapter(navChildsAdapter);
        //导航子条目适配器的监听
//        navChildAdapter.setOnNavChildItemClickLister(new NavChildAdapter.OnNavChildItemClickLister() {
//            //            点击事件的传id值,并且展示数据
//            @Override
//            public void onNavChildItemClick(int position) {
//                homeAll.setVisibility(View.GONE);
//                navChildsLinear.setVisibility(View.VISIBLE);
//                navPresenter.getNavChildsBea(result.get(position).getId());
//
//            }
//        });

    }

    @Override
    public void onNavChildsBeanFailed(String navCMsg) {
        Log.d("导航子条目的孩子获取失败", "onFailed: " + navCMsg);
    }

    @Override
    public void onFailed(Throwable t) {
        Log.d("首页数据获取失败", "onFailed: " + t.getMessage());
    }


    //    进行获取焦点，点击返回键返回上一级
    @Override
    public void onResume() {
        super.onResume();

        getFours();
    }

//        进行获取焦点

    long exitTime = 0;

    private void getFours() {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                    homeAll.setVisibility(View.VISIBLE);
                    navChildsLinear.setVisibility(View.GONE);
                    if ((System.currentTimeMillis() - exitTime) > 2000) {
                        Toast.makeText(getActivity(), "再按一次就退出了哟", Toast.LENGTH_SHORT).show();
                        exitTime = System.currentTimeMillis();
                    } else {
                        getActivity().finish();
                        System.exit(0);
                      /*  InputMethodManager imm = (InputMethodManager) getActivity()
                                .getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(edSou.getWindowToken(), 0);*/
                    }
                    return true;
                }
                return false;
            }
        });

    }//    进行获取焦点


    //    解决内存溢出
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        homeBannerPresenter.detachView();
        navPresenter.detachView();

    }//    解决内存溢出


}