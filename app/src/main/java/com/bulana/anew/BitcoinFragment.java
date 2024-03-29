//package com.bulana.anew;
//
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BitcoinFragment extends Fragment {
//
//    private ArticleAdapter articleAdapter;
//    private static final String LOG_TAG = MainActivity.class.getSimpleName();
//    public int networkCallsCounter;
//    private final String STATE_LIST = "Adapter data";
//    public ArrayList<ArticleModel> articlesList;
//    private View loadingView;
//
//    public BitcoinFragment() {
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_bitcoin, container, false);
//
//        getActivity().setTitle("Bitcoin News");
//
//        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
//        GridLayoutManager gridLayout = new GridLayoutManager(getActivity(), 1);
//        recyclerView.setLayoutManager(gridLayout);
//        recyclerView.setHasFixedSize(true);
//
//        articleAdapter = new ArticleAdapter(getActivity(), (ArrayList<ArticleModel>) getData());
//        recyclerView.setAdapter(articleAdapter);
//
//        return view;
//    }
//
//    public List<ArticleModel> getData() {
//
//        Log.d(LOG_TAG, "onResume");
//
//            //prep articles
//        new ArticleData().getNewsList(new ArticleListAsyncResponse() {
//            @Override
//            public void processFinish(ArrayList<ArticleModel> articlesList) {
//
//                if (networkCallsCounter == 1) {
//                    ArticleModel article = new ArticleModel();
//                    article.setAuthor("AUTHOR");
//                    article.setTitle("TITLE");
//                    article.setDescription(("description"));
//                    article.setImageUrl("urlToImage");
//                    article.setPublishedDate("publishedAt");
//                    article.setNewsUrl("url");
//                    articlesList.add(0,article);
//                }
//
//
//                //Set article data
//                loadingView.setVisibility(View.GONE);
//                articleAdapter.updateData(articlesList);
//                networkCallsCounter++;
//
//             //   articleAdapter.setOnClickListener(new ArticleAdapter.OnItemClickListener() {
//
////                    @Override
////                    public void onArticleSelected(ArticleModel articleData) {
////
////                        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
////                        intent.putExtra("url", articleData.getNewsUrl());
////                        startActivity(intent);
////                    }
//         //       });
//            }
//        });
//        return articlesList;
//    }
//
//}