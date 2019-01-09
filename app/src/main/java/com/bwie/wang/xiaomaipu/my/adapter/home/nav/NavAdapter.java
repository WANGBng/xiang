package com.bwie.wang.xiaomaipu.my.adapter.home.nav;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.wang.xiaomaipu.R;
import com.bwie.wang.xiaomaipu.my.bean.home.nav.NavBean;

import java.util.List;

/**
 * date:2019/1/6.
 *
 * @author 王丙均
 */

public class NavAdapter extends RecyclerView.Adapter<NavAdapter.ViewHolder> {
    private Context context;
    private List<NavBean.ResultBean> nList;

    boolean isCheck=true;

    /**
     * 自定义一个分类的监听
     */
    public interface OnNavItemClickLister{
        void onNavItemClick(ViewHolder holder,int position);
    }
    private OnNavItemClickLister onNavItemClickLister;

    public void setOnNavItemClickLister(OnNavItemClickLister onNavItemClickLister) {
        this.onNavItemClickLister = onNavItemClickLister;
    }

    public NavAdapter(Context context, List<NavBean.ResultBean> nList) {
        this.context = context;
        this.nList = nList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.home_nav_adapter, null));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.nav_name.setText(nList.get(position).getName());
        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNavItemClickLister!=null){
                    onNavItemClickLister.onNavItemClick(holder,position);
                }
                /*if (isCheck){
                    holder.nav_name.setTextColor(Color.RED);

                    isCheck =false;
                }else {
                    holder.nav_name.setTextColor(Color.WHITE);
                    isCheck =true;
                }*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return nList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nav_name;

        public ViewHolder(View itemView) {
            super(itemView);
            nav_name = (TextView) itemView.findViewById(R.id.nav_name);
        }
    }
}
