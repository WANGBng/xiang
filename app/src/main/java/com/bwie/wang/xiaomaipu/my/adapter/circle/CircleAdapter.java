package com.bwie.wang.xiaomaipu.my.adapter.circle;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.wang.xiaomaipu.R;
import com.bwie.wang.xiaomaipu.mvp.presenter.home.NavChildsBean;
import com.bwie.wang.xiaomaipu.my.bean.circle.CircleBean;
import com.bwie.wang.xiaomaipu.my.utils.DateUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * date:2019/1/4.
 *
 * @author 王丙均
 */

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.ViewHolder> {
    private Context context;
    private List<CircleBean.ResultBean> cList;

    //自定义点击事件
    public interface OnPraClickListener {
        void onPraClickListener(int position);
    }
    private OnPraClickListener praClickListener;

    public void setPraClickListener(OnPraClickListener praClickListener) {
        this.praClickListener = praClickListener;
    }
    public CircleAdapter(Context context, List<CircleBean.ResultBean> cList) {
        this.context = context;
        this.cList = cList;
    }

    int num = 0;
    int greatNum = 0;
    int i = 0;
    boolean isSelected = false;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.circle_layout_adapter, null));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Uri uri = Uri.parse(cList.get(position).getHeadPic());
        holder.circle_user_simple.setImageURI(uri);
        holder.circle_user_name.setText(cList.get(position).getNickName());
        //时间
        String dateToString = DateUtils.getDateToString(cList.get(position).getCreateTime());
        holder.circle_date.setText(dateToString);
        holder.circle_title.setText(cList.get(position).getContent());
        Uri uri1 = Uri.parse(cList.get(0).getImage());
        holder.circle1_simple.setImageURI(uri1);
        Uri uri2 = Uri.parse(cList.get(1).getImage());
        holder.circle2_simple.setImageURI(uri2);
        holder.zan_shu.setText(String.valueOf(cList.get(position).getGreatNum()));

        greatNum = cList.get(position).getGreatNum();

        i = greatNum + num;
        holder.zan_simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           /*     if (OnPraClickListener!=null){

                }*/

/**
 * 这有问题,还是先赶进度
 */
                if (!isSelected){
                    isSelected=true;
                    holder.zan_simple.setImageResource(R.mipmap.common_btn_prise_s_hdpi);
                    for (int j = 0; j < cList.size(); j++) {
                        holder.zan_shu.setText(String.valueOf(cList.get(position).getGreatNum()+1));
                    }
                }else{
                    holder.zan_simple.setImageResource(R.mipmap.common_btn_prise_n_hdpi);
                    for (int j = 0; j < cList.size(); j++) {
                        holder.zan_shu.setText(String.valueOf(cList.get(position).getGreatNum()));
                    }
                    isSelected=false;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return cList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView circle_user_simple;
        private TextView circle_user_name;
        private TextView circle_date;
        private TextView circle_title;
        private SimpleDraweeView circle1_simple;
        private SimpleDraweeView circle2_simple;
        private ImageView zan_simple;
        private TextView zan_shu;
        public ViewHolder(View itemView) {
            super(itemView);

            circle_user_simple = (SimpleDraweeView) itemView.findViewById(R.id.circle_user_simple);
            circle_user_name = (TextView) itemView.findViewById(R.id.circle_user_name);
            circle_date = (TextView) itemView.findViewById(R.id.circle_date);
            circle_title = (TextView) itemView.findViewById(R.id.circle_title);
            circle1_simple = (SimpleDraweeView) itemView.findViewById(R.id.circle1_simple);
            circle2_simple = (SimpleDraweeView) itemView.findViewById(R.id.circle2_simple);

            zan_shu = (TextView) itemView.findViewById(R.id.zan_shu);
            zan_simple = (ImageView) itemView.findViewById(R.id.zan_simple);
        }
    }
}
