package com.special.newsdemo.Adapter;

/**
 * Created by Special on 2017/11/12.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.special.newsdemo.Fragment.NewsFragment;
import com.special.newsdemo.NewContentActivity;
import com.special.newsdemo.R;
import com.special.newsdemo.model.New;

import org.w3c.dom.Text;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<New> newsList;
    private static final int TYPE_NEWS = 1;
    private static final int TYPE_FOOTER = 2;
    private Context context;

    public NewsAdapter(List<New> newsList,Context context){
        this.newsList = newsList;
        this.context = context;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView newPic;
        TextView newTitle;
        TextView newSum;
        TextView visitCount;
        TextView comment;
        RelativeLayout touchForNewsContent;

        public ViewHolder(View view){
            super(view);
            newPic = (ImageView) view.findViewById(R.id.new_pic);
            newTitle = (TextView) view.findViewById(R.id.new_title);
            newSum = (TextView) view.findViewById(R.id.new_sum);
            visitCount = (TextView) view.findViewById(R.id.visitcount);
            comment = (TextView) view.findViewById(R.id.comment);
            touchForNewsContent = (RelativeLayout) view.findViewById(R.id.new_header);
        }
    }

    class FooterHolder extends RecyclerView.ViewHolder{
        private ProgressBar progressBar;
        private TextView textView;
        public FooterHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.news_loadmore);
            textView = itemView.findViewById(R.id.footer_text);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Log.i("position",String.valueOf(position));
        if(position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        }
        else
            return TYPE_NEWS;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if(viewType == TYPE_NEWS) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            holder = (NewsAdapter.ViewHolder) new NewsAdapter.ViewHolder(view);
            final NewsAdapter.ViewHolder viewHolder = (NewsAdapter.ViewHolder) holder;
            viewHolder.touchForNewsContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    New aNew = newsList.get(viewHolder.getAdapterPosition());
                    Intent intent = new Intent(context, NewContentActivity.class);
                    intent.putExtra("index", aNew.index);
                    context.startActivity(intent);
                }
            });
        }
        else if(viewType == TYPE_FOOTER){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, parent, false);
            holder = new NewsAdapter.FooterHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_NEWS) {
            NewsAdapter.ViewHolder newHolder = (NewsAdapter.ViewHolder) holder;
            New newItem = newsList.get(position);
            Glide.with(context).load(newItem.pic).error(R.drawable.placeholder).into(newHolder.newPic);
            newHolder.newTitle.setText(newItem.subject);
            newHolder.newSum.setText(newItem.summary);
            newHolder.visitCount.setText("浏览量：" + String.valueOf(newItem.visitCount));
            newHolder.comment.setText("评论量：" + String.valueOf(newItem.comments));
        }
    }


    @Override
    public int getItemCount() {
        return newsList.size() + 1;
    }

    public void changeFooter(RecyclerView recyclerView,LinearLayoutManager layoutManager){
        int firistVisiblePositon = layoutManager.findFirstVisibleItemPosition();
        View view = recyclerView.getChildAt(getItemCount() - 1 - firistVisiblePositon);
        if(view != null) {
            ProgressBar progressBar = view.findViewById(R.id.news_loadmore);
            progressBar.setVisibility(View.INVISIBLE);
            TextView textView = view.findViewById(R.id.footer_text);
            textView.setText("我是有底线的啊");
        }
    }
}
