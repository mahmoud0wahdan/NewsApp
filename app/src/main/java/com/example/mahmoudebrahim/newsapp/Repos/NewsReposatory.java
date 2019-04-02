package com.example.mahmoudebrahim.newsapp.Repos;

import android.util.Log;

import com.example.mahmoudebrahim.Api.ApiManager;
import com.example.mahmoudebrahim.Api.Model.NewsSourceItemResponse.ArticlesItem;
import com.example.mahmoudebrahim.Api.Model.NewsSourceItemResponse.NewsSourceItemResponse;
import com.example.mahmoudebrahim.Api.Model.NewsSourceResponse.NewsResponse;
import com.example.mahmoudebrahim.Api.Model.NewsSourceResponse.SourcesItem;
import com.example.mahmoudebrahim.Database.MyDatabase;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsReposatory {


    private static final String APIKEY="e6f44b57b3284f6f8d1218c1c5b535c3";
    String lang;

    public NewsReposatory(String language) {
        this.lang= language;

    }

    public void getNewsSorces(final OnSourcesPreparedListener onSourcesPreparedListener){

        ApiManager.getAPIs().getSourcesNews(APIKEY,lang)
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        if(response.isSuccessful()&& "ok".equals(response.body().getStatus()))

                        if(onSourcesPreparedListener!=null)
                            onSourcesPreparedListener.OnSoursesPrepared(response.body().getSources());
                        InsertIntoNewsThread insertIntoDB=new InsertIntoNewsThread(response.body().getSources());
                        insertIntoDB.start();
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        GetSourcesFromDBThread getSourcesFromDBThread=new GetSourcesFromDBThread(onSourcesPreparedListener);
                        getSourcesFromDBThread.start();
                    }
                });
    }

    public void getSoureItemNewsBySourceID(String lang, final SourcesItem sourcesItem, final OnNewsSourcePreparedListener onNewsSourcePreparedListener){
        ApiManager.getAPIs().getSourceItem(APIKEY,sourcesItem.getId(),lang)
        .enqueue(new Callback<NewsSourceItemResponse>() {
            @Override
            public void onResponse(Call<NewsSourceItemResponse> call, Response<NewsSourceItemResponse> response) {
                if (response.isSuccessful()&&"ok".equals(response.body().getStatus())){
                    onNewsSourcePreparedListener.OnNewsPrepared(response.body().getArticles());
                    InsertIntoArticals intoDBArticals=new InsertIntoArticals(response.body().getArticles());
                    intoDBArticals.start();
                }
            }

            @Override
            public void onFailure(Call<NewsSourceItemResponse> call, Throwable t) {
                    GetArticalsBySource getArticalsBySource=new GetArticalsBySource(onNewsSourcePreparedListener,sourcesItem);
                    getArticalsBySource.start();
            }
        });
    }


    public interface OnSourcesPreparedListener{

        void OnSoursesPrepared(List<SourcesItem> soursesList);
    }
    public interface OnNewsSourcePreparedListener{
        void OnNewsPrepared(List<ArticlesItem> articlesItems);
    }

    public class InsertIntoNewsThread extends Thread{

        List<SourcesItem> items;

        public InsertIntoNewsThread(List<SourcesItem> items){
            this.items=items;
        }
        @Override
        public void run() {
            super.run();
            MyDatabase.getInstance()
                    .soursesDAO().addAllSources(items);
            Log.i("DB", "Insertion Successfully");
        }
    }
    public class GetSourcesFromDBThread extends Thread{

        List<SourcesItem> sourcesItems;
        OnSourcesPreparedListener onSourcesPreparedListener;

        public GetSourcesFromDBThread(OnSourcesPreparedListener onSourcesPreparedListener){
            this.onSourcesPreparedListener=onSourcesPreparedListener;
        }
        @Override
        public void run() {
            super.run();
            sourcesItems=MyDatabase.getInstance()
                    .soursesDAO().getAllSources();
            onSourcesPreparedListener.OnSoursesPrepared(sourcesItems);
            Log.i("DB", "Getting Sources Success");
        }
    }
    public class InsertIntoArticals extends Thread{
        List<ArticlesItem> articlesItems;
        public InsertIntoArticals (List<ArticlesItem> articlesItems){
            this.articlesItems=articlesItems;
        }

        @Override
        public void run() {
            super.run();
            MyDatabase.getInstance().soursesDAO().addAllArticals(articlesItems);
        }
    }
    public class GetArticalsBySource extends Thread{
        List<ArticlesItem> articlesItems;
        SourcesItem sourcesItem;
        OnNewsSourcePreparedListener onNewsSourcePreparedListener;
        public GetArticalsBySource(OnNewsSourcePreparedListener onNewsSourcePreparedListener, SourcesItem sourcesItem){
            this.onNewsSourcePreparedListener=onNewsSourcePreparedListener;
            this.sourcesItem=sourcesItem;
        }

        @Override
        public void run() {
            super.run();
            articlesItems=
            MyDatabase.getInstance()
                    .soursesDAO()
                    .getAllArticalsAtSource(sourcesItem.getId());
            onNewsSourcePreparedListener.OnNewsPrepared(articlesItems);
        }
    }
}
