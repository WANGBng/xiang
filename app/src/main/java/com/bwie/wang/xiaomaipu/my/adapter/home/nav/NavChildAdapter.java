package com.bwie.wang.xiaomaipu.my.adapter.home.nav;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.wang.xiaomaipu.R;
import com.bwie.wang.xiaomaipu.my.bean.home.nav.NavChildBean;

import java.util.List;

/**
 * date:2019/1/7.
 *
 * @author 王丙均
 */

public class NavChildAdapter extends RecyclerView.Adapter<NavChildAdapter.ViewHolder> {
    private Context context;
    private List<NavChildBean.ResultBean> childList;

    public NavChildAdapter(Context context, List<NavChildBean.ResultBean> childList) {
        this.context = context;
        this.childList = childList;
    }
    /**
     * 自定义一个分类的监听
     */
    public interface OnNavChildItemClickLister{
        void onNavChildItemClick(int position);
    }
    private OnNavChildItemClickLister onNavChildItemClickLister;

    public void setOnNavChildItemClickLister(OnNavChildItemClickLister onNavChildItemClickLister) {
        this.onNavChildItemClickLister = onNavChildItemClickLister;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.home_nav_child_adapter, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.nav_child_name.setText(childList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断监听是否存在
                if (onNavChildItemClickLister != null){
                    onNavChildItemClickLister.onNavChildItemClick(position);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return childList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nav_child_name;

        public ViewHolder(View itemView) {
            super(itemView);
            nav_child_name = (TextView) itemView.findViewById(R.id.nav_child_name);
        }
    }
}
