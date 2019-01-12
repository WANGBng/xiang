package com.bwie.wang.xiaomaipu.my.adapter.home;

import android.content.Context;
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
 *
 * @author 王丙均
 */

public class Home_Hot_Line_Adapter extends RecyclerView.Adapter<Home_Hot_Line_Adapter.ViewHolder> {
    private Context context;
    private List<HomeCommodityBean.ResultBean.RxxpBean.CommodityListBean> rxxpList;


    public Home_Hot_Line_Adapter(Context context, List<HomeCommodityBean.ResultBean.RxxpBean.CommodityListBean> rxxpList) {
        this.context = context;
        this.rxxpList = rxxpList;
    }
    /**
     * 自定义一个分类的监听
     */
    public interface OnHomeHotLineAdapterClickLister{
        void onHomeHotLineAdapterItemClickLister(int position);
    }
    private OnHomeHotLineAdapterClickLister onHomeHotLineClickLister;

    public void setOnHomeHotLineClickLister(OnHomeHotLineAdapterClickLister onHomeHotLineClickLister) {
        this.onHomeHotLineClickLister = onHomeHotLineClickLister;
    }

    //    private
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.home_hot_line_adapter, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Uri uri = Uri.parse(rxxpList.get(position).getMasterPic());
        holder.rxxp_simple.setImageURI(uri);
        holder.rxxp_te_title.setText(rxxpList.get(position).getCommodityName());
        holder.rxxp_te_price.setText(String.valueOf("￥：" + rxxpList.get(position).getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (onHomeHotLineClickLister!=null){
                    onHomeHotLineClickLister.onHomeHotLineAdapterItemClickLister(position);
                }
                /*Intent intent = new Intent(context,InfoActivity.class);
                int commodityId = rxxpList.get(position).getCommodityId();
                intent.putExtra("commodityId",commodityId);
                context.startActivity(intent);*/


            }
        });





    }

    @Override
    public int getItemCount() {
        return rxxpList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView rxxp_simple;
        private TextView rxxp_te_title;
        private TextView rxxp_te_price;
        public ViewHolder(View itemView) {
            super(itemView);
            rxxp_simple = (SimpleDraweeView) itemView.findViewById(R.id.rxxp_simple);
            rxxp_te_title = (TextView) itemView.findViewById(R.id.rxxp_te_title);
            rxxp_te_price = (TextView) itemView.findViewById(R.id.rxxp_te_price);
        }
    }
}
