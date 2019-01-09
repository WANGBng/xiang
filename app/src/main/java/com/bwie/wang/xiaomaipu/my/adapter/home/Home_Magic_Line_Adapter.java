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

public class Home_Magic_Line_Adapter extends RecyclerView.Adapter<Home_Magic_Line_Adapter.ViewHolder> {
    private Context context;
    private List<HomeCommodityBean.ResultBean.MlssBean.CommodityListBeanXX> mlssList;

    public Home_Magic_Line_Adapter(Context context, List<HomeCommodityBean.ResultBean.MlssBean.CommodityListBeanXX> mlssList) {
        this.context = context;
        this.mlssList = mlssList;
    }
    /**
     * 自定义一个分类的监听
     */
    public interface OnHomeMagicLinClickLister{
        void onHomeMagicLinClickLister(int position);
    }
    private OnHomeMagicLinClickLister onHomeMagicLinClickLister;

    public void setOnHomeMagicLinClickLister(OnHomeMagicLinClickLister onHomeMagicLinClickLister) {
        this.onHomeMagicLinClickLister = onHomeMagicLinClickLister;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.home_magic_line_adapter, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        HomeCommodityBean.ResultBean.MlssBean.CommodityListBeanXX commodityListBeanXX = mlssList.get(position);

        holder.mlss_title.setText(commodityListBeanXX.getCommodityName());
        holder.mlss_price.setText(String.valueOf("￥"+commodityListBeanXX.getPrice()));
//获取图片
        Uri uri = Uri.parse(commodityListBeanXX.getMasterPic());
        holder.mlss_simple.setImageURI(uri);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onHomeMagicLinClickLister!=null){
                    onHomeMagicLinClickLister.onHomeMagicLinClickLister(position);
                }

//                Intent intent = new Intent(context,InfoActivity.class);
//                int pid = list.get(position).getCommodityId();
//                intent.putExtra("pid",pid);
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlssList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView mlss_simple;
        private TextView mlss_title;
        private TextView mlss_price;
        public ViewHolder(View itemView) {
            super(itemView);
            mlss_simple = itemView.findViewById(R.id.mlss_simple);
            mlss_title =  itemView.findViewById(R.id.mlss_title);
            mlss_price =  itemView.findViewById(R.id.mlss_price);
        }
    }
}
