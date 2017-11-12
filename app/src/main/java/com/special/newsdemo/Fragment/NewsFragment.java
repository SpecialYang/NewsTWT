package com.special.newsdemo.Fragment;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.special.newsdemo.Adapter.NewsAdapter;
import com.special.newsdemo.NewContentActivity;
import com.special.newsdemo.R;
import com.special.newsdemo.model.APi;
import com.special.newsdemo.model.New;
import com.special.newsdemo.model.NewsResponse;
import com.special.newsdemo.util.HttpUtility;
import com.special.newsdemo.util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.IvParameterSpec;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    private static final String PERFIX_Url = "http://open.twtstudio.com/api/v1/";

    private int page;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdpater;
    private List<New> newsList = new ArrayList<>();
    public SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager layoutManager;
    private boolean loading = false;

    public static Fragment newInstance(int type){
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        Fragment newsFragment = new NewsFragment();
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_news);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_news);
        final int type = getArguments().getInt("type");
        page = 1;
        swipeRefreshLayout.setColorSchemeResources(R.color.colorProgress);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                page = 1;
                newsList.clear();
                refresh(type,page);
            }
        });

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        newsAdpater = new NewsAdapter(newsList,getActivity());
        recyclerView.setAdapter(newsAdpater);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (!loading && totalCount == lastVisibleItem + 1) {
                    page++;
                    refresh(type,page);
                }
            }
        });
        refresh(type,page);
        return view;
    }

    public void refresh(int type, int page){
        Log.i("refresh",String.valueOf(type)+"  "+ String.valueOf(page));
        loading = true;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PERFIX_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APi api = retrofit.create(APi.class);
        Call<NewsResponse> call = api.getNewsList(type,page);
        call.enqueue(new Callback<NewsResponse>(){
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if(response.isSuccessful()){
                    final NewsResponse news = response.body();
                    getActivity().runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            if(news.error_code == -1){
                                if(news.data.size() != 0)
                                    newsList.addAll(news.data);
                                else {
                                    Toast.makeText(getActivity(), "我是有底线的", Toast.LENGTH_SHORT).show();
                                    newsAdpater.changeFooter(recyclerView,layoutManager);
                                }
                                newsAdpater.notifyDataSetChanged();
                            }else
                                onError();
                        }
                    });
                }
                else{
                    onError();
                }
                swipeRefreshLayout.setRefreshing(false);
                loading = false;
            }
            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                t.printStackTrace();
                onError();
                swipeRefreshLayout.setRefreshing(false);
                loading = false;
            }
        });
    }

    private void onError(){
        getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run() {
                Toast.makeText(getActivity(),"获取新闻失败，服务器出错",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
