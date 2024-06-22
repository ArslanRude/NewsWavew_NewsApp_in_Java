package com.example.newsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    List<Article> articleList = new LinkedList<>();
    NewsRecyclerAdaptor adaptor;
    LinearProgressIndicator progressIndicator;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;
    SearchView searchView;

    SwipeRefreshLayout refreshLayout;

    String catagory;

    LinearLayout noInternet;

    HorizontalScrollView horizontalScrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            noInternet = findViewById(R.id.no_internet);
            horizontalScrollView = findViewById(R.id.horizontal_button);

            recyclerView = findViewById(R.id.news_recycler_view);
            progressIndicator = findViewById(R.id.progress_bar);
            refreshLayout = findViewById(R.id.refresh);
            refreshLayout.setOnRefreshListener(this);



            btn1 = findViewById(R.id.btn_1);
            btn2 = findViewById(R.id.btn_2);
            btn3 = findViewById(R.id.btn_3);
            btn4 = findViewById(R.id.btn_4);
            btn5 = findViewById(R.id.btn_5);
            btn6 = findViewById(R.id.btn_6);
            btn7 = findViewById(R.id.btn_7);
            btn1.setOnClickListener(this);
            btn2.setOnClickListener(this);
            btn3.setOnClickListener(this);
            btn4.setOnClickListener(this);
            btn5.setOnClickListener(this);
            btn6.setOnClickListener(this);
            btn7.setOnClickListener(this);

            searchView = findViewById(R.id.search_view);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    getNews("GENERAL",query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });



            getNews("GENERAL",null);
            setupRecyclerView();

            if(!isConnected()){
                progressIndicator.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this,"No Internet",Toast.LENGTH_LONG).show();
                horizontalScrollView.setVisibility(View.INVISIBLE);
                noInternet.setVisibility(View.VISIBLE);
            }

            return insets;
        });
    }

    void setupRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptor = new NewsRecyclerAdaptor(articleList);
        recyclerView.setAdapter(adaptor);

    }
    void changeInProgress(boolean show){
        if (show)
            progressIndicator.setVisibility(RecyclerView.VISIBLE);

        else
            progressIndicator.setVisibility(RecyclerView.INVISIBLE);
    }
    void getNews(String catagory,String query){
        changeInProgress(true);
        NewsApiClient newsApiClient = new NewsApiClient("1456376b5ea14ab0a6cdd8d8ac380a80");
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .language("en")
                        .category(catagory)
                        .q(query)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        runOnUiThread(()->{
                            changeInProgress(false);
                            articleList = response.getArticles();
                            Collections.shuffle(articleList);
                            adaptor.updataData(articleList);
                            adaptor.notifyDataSetChanged();

                        });
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.i("Got Failure", Objects.requireNonNull(throwable.getMessage()));
                    }
                }
        );
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        catagory = btn.getText().toString();
        getNews(catagory,null);
    }

    @Override
    public void onRefresh() {
        getNews(catagory,null);
        horizontalScrollView.scrollTo(0,0);
        refreshLayout.setRefreshing(false);
    }

    boolean isConnected(){
            ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
            return connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}