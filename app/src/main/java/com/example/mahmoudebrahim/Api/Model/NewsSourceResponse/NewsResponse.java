package com.example.mahmoudebrahim.Api.Model.NewsSourceResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class NewsResponse{

	@SerializedName("sources")
	private List<SourcesItem> sources;

	@SerializedName("status")
	private String status;

	public void setSources(List<SourcesItem> sources){
		this.sources = sources;
	}

	public List<SourcesItem> getSources(){
		return sources;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"NewsResponse{" + 
			"sources = '" + sources + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}