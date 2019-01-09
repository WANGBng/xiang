package com.bwie.wang.xiaomaipu.my.adapter.home.nav;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.wang.xiaomaipu.R;
import com.bwie.wang.xiaomaipu.mvp.presenter.home.NavChildsBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * date:2019/1/8.
 *
 * @author 王丙均
 */

public class NavChildsAdapter extends RecyclerView.Adapter<NavChildsAdapter.ViewHolder> {
    private Context context;
    private List<NavChildsBean.ResultBean> childLists;

    public NavChildsAdapter(Context context, List<NavChildsBean.ResultBean> childLists) {
        this.context = context;
        this.childLists = childLists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.home_nav_childs_adapter, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NavChildsBean.ResultBean resultBean = childLists.get(position);
        holder.nav_simple.setImageURI(resultBean.getMasterPic());

        holder.nav_title.setText(resultBean.getCommodityName());
        holder.nav_price.setText(String.valueOf(resultBean.getPrice()));

    }

    @Override
    public int getItemCount() {
        return childLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView nav_simple;
        private TextView nav_title;
        private TextView nav_price;
        public ViewHolder(View itemView) {
            super(itemView);

            nav_simple = (SimpleDraweeView) itemView .findViewById(R.id.nav_simple);
            nav_title = (TextView) itemView .findViewById(R.id.nav_title);
            nav_price = (TextView) itemView .findViewById(R.id.nav_price);
        }
    }
}
