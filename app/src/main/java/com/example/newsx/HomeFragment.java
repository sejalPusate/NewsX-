package com.example.newsx;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    String API_KEYc = "b0a320f12ce6435db52d94dd968f5259";
    RecyclerView recyclerView;
    Adaptor adaptor;
    ArrayList<Model> modelClassArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.home_recyclerView);
        modelClassArrayList = new ArrayList<>();
        adaptor = new Adaptor(getContext(), modelClassArrayList);

        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getNews();
        return view;
    }
    void getNews(){
        Log.d("API_TEST", "Attempting to fetch news...");
        ApiUtilities.getApiInterface().getNews("in", 100, API_KEYc).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                Log.d("API_RESPONSE", "Code: " + response.code());
                if (response.isSuccessful()){
                    modelClassArrayList.addAll(response.body().getArticles());
                    adaptor.notifyDataSetChanged();
                    Log.d("API", "Articles received: " + response.body().getArticles().size());
                } else {
                    Log.e("API_ERROR", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {
                Log.e("API", "Failed to fetch news", t);
            }
        });

    }
}