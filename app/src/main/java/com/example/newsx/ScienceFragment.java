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


public class ScienceFragment extends Fragment {

    String API_KEYc = "b0a320f12ce6435db52d94dd968f5259";
    RecyclerView recyclerView;
    Adaptor adaptor;
    ArrayList<Model> modelClassArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_science, container, false);

        recyclerView = view.findViewById(R.id.science_recyclerView);
        modelClassArrayList = new ArrayList<>();
        adaptor = new Adaptor(getContext(), modelClassArrayList);

        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getNews();
        return view;
    }
    void getNews(){
        ApiUtilities.getApiInterface().getCategory("in", "science",100, API_KEYc).enqueue(new Callback<MainNews>() {

            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if (response.isSuccessful()){
                    modelClassArrayList.addAll(response.body().getArticles());
                    adaptor.notifyDataSetChanged();
                    Log.d("API", "Articles received: " + response.body().getArticles().size());

                }
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {
                Log.e("API", "Failed to fetch news", t);
            }
        });
    }
}