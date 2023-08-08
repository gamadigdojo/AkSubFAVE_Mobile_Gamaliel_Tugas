package com.example.trainingmobilefave.model;

import com.google.gson.annotations.SerializedName;

public class Jpg{

	@SerializedName("image_url")
	private String imageUrl;

	public String getImageUrl(){
		return imageUrl;
	}
}