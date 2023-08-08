package com.example.trainingmobilefave.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingmobilefave.R;
import com.example.trainingmobilefave.adapter.CharacterAdapter;
import com.example.trainingmobilefave.model.Character;
import com.example.trainingmobilefave.model.TopCharacterResponse;
import com.example.trainingmobilefave.utils.APIInterface;
import com.example.trainingmobilefave.utils.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    RecyclerView rvCharacters;
    ProgressBar progressBar;
    List<Character> characterList = new ArrayList<>();
    CharacterAdapter characterAdapter;
    private int CURRENT_PAGE = 1;
    private boolean isLoading = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvCharacters = view.findViewById(R.id.rv_characters);
        progressBar = view.findViewById(R.id.progress_bar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        characterAdapter = new CharacterAdapter(characterList, getActivity());
        rvCharacters.setLayoutManager(linearLayoutManager);
        rvCharacters.setAdapter(characterAdapter);

        initScrollListener();
       loadCharacterData();

        return view;
    }

    private void initScrollListener(){
        rvCharacters.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if(!isLoading){
                    if(linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition()
                            ==
                    characterList.size() - 1){
                        CURRENT_PAGE++;
                        isLoading = true;
                        progressBar.setVisibility(View.VISIBLE);
                        loadCharacterData();

                    }
                }
            }
        });
    }

    private void loadCharacterData(){
        APIInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
        Call<TopCharacterResponse> call = apiInterface.getTopCharacters(CURRENT_PAGE);
        call.enqueue(new Callback<TopCharacterResponse>() {
            @Override
            public void onResponse(Call<TopCharacterResponse> call, Response<TopCharacterResponse> response) {
                if (response.isSuccessful()){
                    TopCharacterResponse topCharacterResponse = response.body();
                    assert topCharacterResponse != null;
                    characterList.addAll(topCharacterResponse.getCharacters());
                    characterAdapter.setCharacterList(characterList);

                }else {
                    Toast.makeText(getActivity(), "Request Error ::" + response.errorBody(),Toast.LENGTH_SHORT).show();
                }
                isLoading = false;
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<TopCharacterResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "There's Error ::" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
