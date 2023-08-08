package com.example.trainingmobilefave.model;

public class FavoriteCharacter {
    private String name, nameKanji, url, imageUrl;
    private int id, favorites;

    public FavoriteCharacter(int id, String name, String nameKanji, String url, String imageUrl, int favorites) {
        this.name = name;
        this.nameKanji = nameKanji;
        this.url = url;
        this.imageUrl = imageUrl;
        this.id = id;
        this.favorites = favorites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameKanji() {
        return nameKanji;
    }

    public void setNameKanji(String nameKanji) {
        this.nameKanji = nameKanji;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }
}
