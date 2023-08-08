package com.example.trainingmobilefave.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingmobilefave.R;
import com.example.trainingmobilefave.adapter.FavoriteCharacterAdapter;
import com.example.trainingmobilefave.database.CharacterDB;
import com.example.trainingmobilefave.model.FavoriteCharacter;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    RecyclerView rvCharacters;
    FavoriteCharacterAdapter favoriteCharacterAdapter;
    CharacterDB characterDB;
    List<FavoriteCharacter> favoriteCharacterList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Retrieve data from Shared Preferences
        SharedPreferences preferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "");
        String email = preferences.getString("email", "");

        // Display the retrieved data in TextViews
        TextView textViewUsername = view.findViewById(R.id.Username);
        TextView textViewEmail = view.findViewById(R.id.Email);

        textViewUsername.setText("Username : " + username);
        textViewEmail.setText("Email         : " + email);

        characterDB = new CharacterDB(getActivity());
        favoriteCharacterList = characterDB.getAllFavoriteCharacters();
        favoriteCharacterAdapter = new FavoriteCharacterAdapter(favoriteCharacterList, getActivity());

        rvCharacters = view.findViewById(R.id.rv_characters);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvCharacters.setLayoutManager(linearLayoutManager);
        rvCharacters.setAdapter(favoriteCharacterAdapter);



        return view;
    }
}
