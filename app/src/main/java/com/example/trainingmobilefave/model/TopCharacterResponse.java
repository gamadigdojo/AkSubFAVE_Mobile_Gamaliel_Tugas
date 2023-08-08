package com.example.trainingmobilefave.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TopCharacterResponse {

	@SerializedName("pagination")
	private Pagination pagination;

	@SerializedName("data")
	private List<Character> characters;

	public Pagination getPagination(){
		return pagination;
	}

	public List<Character> getCharacters(){
		return characters;
	}
}