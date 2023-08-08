package com.example.trainingmobilefave.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingmobilefave.R;
import com.example.trainingmobilefave.database.CharacterDB;
import com.example.trainingmobilefave.model.FavoriteCharacter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteCharacterAdapter extends RecyclerView.Adapter<FavoriteCharacterAdapter.viewHolder> {
    List<FavoriteCharacter> favoriteCharacterList;
    Context context;
    CharacterDB characterDB;

    public FavoriteCharacterAdapter(List<FavoriteCharacter> favoriteCharacterList, Context context) {
        this.favoriteCharacterList = favoriteCharacterList;
        this.context = context;
        this.characterDB = new CharacterDB(context);
    }

    @NonNull
    @Override
    public FavoriteCharacterAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteCharacterAdapter.viewHolder holder, int position) {
        FavoriteCharacter character = favoriteCharacterList.get(position);
        holder.tvName.setText(character.getName());
        holder.tvKanji.setText(character.getNameKanji());
        holder.tvFavorites.setText(character.getFavorites() + " Likes");
        holder.detailButton.setOnClickListener(v -> {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(character.getUrl())));
        });
        Picasso.with(context)
                .load(character.getImageUrl())
                .resize(250,250)
                .placeholder(R.drawable.ic_baseline_downloading_24)
                .error(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(holder.ivThumbnail);

        holder.detailButton.setOnClickListener(v -> {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(character.getUrl())));
        });

        holder.favoriteButton.setOnClickListener(v -> {
            if(characterDB.deleteCharacter(character.getId()) > 0){
                Toast.makeText(context,"Character removed from favorite!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"Failed to remove!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteCharacterList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvKanji, tvFavorites;
        Button detailButton, favoriteButton;
        ImageView ivThumbnail;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_character_name);
            tvKanji = itemView.findViewById(R.id.tv_character_kanji);
            tvFavorites = itemView.findViewById(R.id.tv_favorites);
            detailButton = itemView.findViewById(R.id.detail_btn);
            favoriteButton = itemView.findViewById(R.id.favorite_btn);
            ivThumbnail = itemView.findViewById(R.id.iv_thumbnail);

        }
    }
}
