package com.example.mahmoudebrahim.newsapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.example.mahmoudebrahim.Api.ApiManager;
import com.example.mahmoudebrahim.Api.Model.NewsSourceItemResponse.ArticlesItem;
import com.example.mahmoudebrahim.Api.Model.NewsSourceResponse.SourcesItem;
import com.example.mahmoudebrahim.Base.BaseDialogActivity;
import com.example.mahmoudebrahim.newsapp.Adapter.NewsRecyclerView;
import com.example.mahmoudebrahim.newsapp.Repos.NewsReposatory;

import java.util.List;

public class HomeActivity extends BaseDialogActivity {

    protected TabLayout tabNews;
    protected RecyclerView recyclerviewNews;
    NewsReposatory newsReposatory;
    NewsRecyclerView newsAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String lang = "en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(layoutManager);
        newsReposatory = new NewsReposatory(lang);
        newsReposatory.getNewsSorces(mySourcesPrepared);
        showProgressBar(R.string.loading);
        tabNews.addOnTabSelectedListener(mybaseOnTabSelectedListener);

    }

    TabLayout.BaseOnTabSelectedListener mybaseOnTabSelectedListener = new TabLayout.BaseOnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            recyclerView.scrollToPosition(RecyclerView.SCROLLBAR_POSITION_DEFAULT);
           // showProgressBar(R.string.loading);
            SourcesItem sourcesItem = (SourcesItem) tab.getTag();
            newsReposatory.getSoureItemNewsBySourceID(
                    "en",
                    sourcesItem,
                    myNewsSourcePreparedListener);

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            showProgressBar(R.string.loading);
            SourcesItem sourcesItem = (SourcesItem) tab.getTag();
            newsReposatory.getSoureItemNewsBySourceID(
                    "en",
                    sourcesItem,
                    myNewsSourcePreparedListener);
        }
    };
    NewsReposatory.OnNewsSourcePreparedListener myNewsSourcePreparedListener = new NewsReposatory.OnNewsSourcePreparedListener() {
        @Override
        public void OnNewsPrepared(final List<ArticlesItem> articlesItems) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    dismissProgressBar();
                    newsAdapter.changeData(articlesItems);

                }
            });

        }
    };

    NewsReposatory.OnSourcesPreparedListener mySourcesPrepared = new NewsReposatory.OnSourcesPreparedListener() {
        @Override
        public void OnSoursesPrepared(final List<SourcesItem> soursesList) {
            dismissProgressBar();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    dismissProgressBar();
                    addToTabLayout(soursesList);
                }

            });

        }
    };



        private void addToTabLayout(List<SourcesItem> soursesList) {
            if (soursesList == null)
                return;
            tabNews.removeAllTabs();
            for (SourcesItem t :
                    soursesList) {
                TabLayout.Tab tab = tabNews.newTab();
                tab.setText(t.getName());
                tab.setTag(t);
                tabNews.addTab(tab);
            }
        }

        private void initView() {
            tabNews = (TabLayout) findViewById(R.id.tab_news);
            recyclerviewNews = (RecyclerView) findViewById(R.id.recyclerview_news);
            layoutManager = new LinearLayoutManager(this);
            newsAdapter = new NewsRecyclerView(null);
            recyclerView = findViewById(R.id.recyclerview_news);
        }

}
