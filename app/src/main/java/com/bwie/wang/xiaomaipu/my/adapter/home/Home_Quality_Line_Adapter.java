package com.bwie.wang.xiaomaipu.my.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.wang.xiaomaipu.R;
import com.bwie.wang.xiaomaipu.my.bean.home.HomeCommodityBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
/**
 * date:2018/12/29.
 * @author 王丙均
 */

public class Home_Quality_Line_Adapter extends RecyclerView.Adapter<Home_Quality_Line_Adapter.ViewHolder> {
    private Context context;
    private List<HomeCommodityBean.ResultBean.PzshBean.CommodityListBeanX> pzshList;

    public Home_Quality_Line_Adapter(Context context, List<HomeCommodityBean.ResultBean.PzshBean.CommodityListBeanX> pzshList) {
        this.context = context;
        this.pzshList = pzshList;
    }

    /**
     * 自定义一个分类的监听
     */
    public interface OnHomeQualityLineAdapterClickLister{
        void onHome_Quality_Line_AdapterItemClickLister(int position);
    }
    private OnHomeQualityLineAdapterClickLister onHomeQualityLineAdapterClickLister;


    public void setOnHomeQualityLineAdapterClickLister(OnHomeQualityLineAdapterClickLister onHomeQualityLineAdapterClickLister) {
        this.onHomeQualityLineAdapterClickLister = onHomeQualityLineAdapterClickLister;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.home_quality_line_adapter, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        HomeCommodityBean.ResultBean.PzshBean.CommodityListBeanX listBeanX = pzshList.get(position);

        holder.quality_title.setText(pzshList.get(position).getCommodityName());
        holder.quality_price.setText(String.valueOf("￥"+pzshList.get(position).getPrice()));

        Uri uri = Uri.parse(pzshList.get(position).getMasterPic());
        holder.quality_simple.setImageURI(uri);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                if (onHomeQualityLineAdapterClickLister != null){
                    onHomeQualityLineAdapterClickLister.onHome_Quality_Line_AdapterItemClickLister(position);
                }*/


                Intent intent = new Intent(context,InfoActivity.class);
                int commodityId = pzshList.get(position).getCommodityId();
                intent.putExtra("commodityId",commodityId);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return pzshList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView quality_simple;
        private TextView quality_title;
        private TextView quality_price;
        public ViewHolder(View itemView) {
            super(itemView);
            quality_simple = itemView.findViewById(R.id.quality_simple);
            quality_title = itemView.findViewById(R.id.quality_title);
            quality_price = itemView.findViewById(R.id.quality_price);
        }
    }
}