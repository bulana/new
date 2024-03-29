package com.bulana.anew;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class BitcoinNewsFragment extends Fragment {

    private ArticleAdapter articleAdapter;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public int networkCallsCounter;
    public ArrayList<ArticleModel> articlesList;
    private View loadingView;
    private View noDataImage;
    private Bundle savedInstanceState;

    public BitcoinNewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflating view
        View view = inflater.inflate(R.layout.fragment_all_news, container, false);

        //
        noDataImage = view.findViewById(R.id.noData);
        loadingView = view.findViewById(R.id.loading_spinner);
        noDataImage.setVisibility(View.GONE);

        articleAdapter = new ArticleAdapter(getActivity());

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.fragmentRecyclerView);
        GridLayoutManager gridLayout = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayout);
        recyclerView.setHasFixedSize(true);

        //regardless
        recyclerView.setAdapter(articleAdapter);
        //get data and set to articleList
        getData();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();

        if(savedInstanceState != null){
            articlesList = savedInstanceState.getParcelableArrayList("Articles");
        }

        getData();
        networkCallsCounter++;

    }

    public List<ArticleModel> getData() {

        Log.d(LOG_TAG, "onResume");

            //prep articles
        new ArticleData().getNewsList(Constant.BITCOIN_URL,new ArticleListAsyncResponse() {
            @Override
            public void processFinish(ArrayList<ArticleModel> articlesList) {

                if (networkCallsCounter == 2) {
                    ArticleModel article = new ArticleModel();
                    article.setAuthor("AUTHOR");
                    article.setTitle("TITLE");
                    article.setDescription(("description"));
                    article.setImageUrl("urlToImage");
                    article.setPublishedDate("publishedAt");
                    article.setNewsUrl("url");
                    articlesList.add(0,article);
                }
                //Set article data
                loadingView.setVisibility(View.GONE);
                if(articlesList != null && articlesList.size() > 0) {
                    articleAdapter.updateData(articlesList);
                }

                //Loader and Spinner
                if(articlesList != null){

                } else {
                    loadingView.setVisibility(View.GONE);
                }

                articleAdapter.setOnClickListener(new ArticleAdapter.OnItemClickListener() {

                    @Override
                    public void onArticleSelected(ArticleModel articleData) {

                        Intent intent = new Intent(getActivity(), DetailsActivity.class);
                        intent.putExtra("url", articleData.getNewsUrl());
                        startActivity(intent);
                    }
                });
            }
        });
        return articlesList;
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        savedInstanceState = state;
        state.putParcelableArrayList("Articles",articlesList);
    }

}