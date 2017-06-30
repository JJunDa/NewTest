package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yls.newtest.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import bean.NewsEntity;

/**
 * Created by yls on 2017/6/28.
 */

public class NewsAdapter extends BaseAdapter {

    private Context mContext;
    // 列表显示的新闻数据
    private List<NewsEntity.ResultBean> listDatas;
/*
    private ImageView iv_icon;
    private TextView tv_title;
    private TextView tv_source;
    private TextView tv_comment;*/


    public NewsAdapter(Context context, List<NewsEntity.ResultBean> listDatas) {
        mContext = context;
        this.listDatas = listDatas;
    }

    @Override
    public int getCount() {
        return (listDatas != null)? listDatas.size() : 0;
    }

    @Override
    public NewsEntity.ResultBean getItem(int position) {
        return listDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 列表项数据
        NewsEntity.ResultBean info = (NewsEntity.ResultBean) getItem(position);

        int type = getItemViewType(position);
        if(type == ITEM_TYPE_HAVE_1_IMAGE){
            //一张图片的情况
            // 查找列表item中的子控件
            if(convertView == null){
//                convertView = View.inflate(mContext, R.layout.item_news_1,parent);
                convertView= LayoutInflater.from(mContext).inflate(R.layout.item_news_1,parent,false);
            }
            TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title_01);
            TextView tv_source = (TextView) convertView.findViewById(R.id.tv_source);
            TextView tv_comment = (TextView) convertView.findViewById(R.id.tv_comment_01);
            ImageView iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);

            // 显示列表item中的子控件
            tv_title.setText(info.getTitle());
            tv_comment.setText(info.getReplyCount() + "跟帖");
            tv_source.setText(info.getSource());
            Picasso.with(mContext).load(info.getImgsrc()).into(iv_icon);

        }else if(type == ITEM_TYPE_HAVE_3_IMAGE){
            //三张图片的情况
            // 查找列表item中的子控件
            if(convertView == null){
//                convertView = View.inflate(mContext, R.layout.item_news_2,parent);
                convertView= LayoutInflater.from(mContext).inflate(R.layout.item_news_2,parent,false);
            }
            TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title_02);
            TextView tv_comment = (TextView) convertView.findViewById(R.id.tv_comment_02);
            ImageView iv_01 = (ImageView) convertView.findViewById(R.id.iv_01);
            ImageView iv_02 = (ImageView) convertView.findViewById(R.id.iv_02);
            ImageView iv_03 = (ImageView) convertView.findViewById(R.id.iv_03);

            // 显示列表item中的子控件
            tv_title.setText(info.getTitle());
            tv_comment.setText(info.getReplyCount() + "跟帖");
            try{
            Picasso.with(mContext).load(info.getImgsrc()).into(iv_01);
            Picasso.with(mContext).load(info.getImgextra().get(0).getImgsrc()).into(iv_02);
            Picasso.with(mContext).load(info.getImgextra().get(1).getImgsrc()).into(iv_03);}
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return convertView;
    }

    //======================多个item布局(begin)================
    private static final int ITEM_TYPE_HAVE_1_IMAGE = 0;
    private static final int ITEM_TYPE_HAVE_3_IMAGE = 1;

    @Override
    public int getItemViewType(int position) {
        NewsEntity.ResultBean result = getItem(position);
        if(result.getImgextra() == null || result.getImgextra().size() == 0){
            return ITEM_TYPE_HAVE_1_IMAGE;
        }else {
            return ITEM_TYPE_HAVE_3_IMAGE;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }


    //======================多个item布局(end)================


}
