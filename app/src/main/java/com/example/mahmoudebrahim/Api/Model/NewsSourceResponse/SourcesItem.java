package com.example.mahmoudebrahim.Api.Model.NewsSourceResponse;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class SourcesItem{

    @ColumnInfo
	@SerializedName("country")
	private String country;

    @ColumnInfo
	@SerializedName("name")
	private String name;

    @ColumnInfo
	@SerializedName("description")
	private String description;

    @ColumnInfo
	@SerializedName("language")
	private String language;

    @ColumnInfo
    @NonNull
	@PrimaryKey
	@SerializedName("id")
	private String id;

    @ColumnInfo
	@SerializedName("category")
	private String category;

    @ColumnInfo
	@SerializedName("url")
	private String url;

    public SourcesItem() {
    }

    public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setLanguage(String language){
		this.language = language;
	}

	public String getLanguage(){
		return language;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"SourcesItem{" + 
			"country = '" + country + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",language = '" + language + '\'' + 
			",id = '" + id + '\'' + 
			",category = '" + category + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}