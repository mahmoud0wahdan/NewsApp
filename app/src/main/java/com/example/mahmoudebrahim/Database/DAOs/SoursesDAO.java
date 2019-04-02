package com.example.mahmoudebrahim.Database.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.mahmoudebrahim.Api.Model.NewsSourceItemResponse.ArticlesItem;
import com.example.mahmoudebrahim.Api.Model.NewsSourceResponse.SourcesItem;

import java.util.List;

import javax.xml.transform.Source;

@Dao
public interface SoursesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addAllSources(List<SourcesItem> items);

    @Query("select * from SourcesItem ;")
    List<SourcesItem> getAllSources();

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void addAllArticals(List<ArticlesItem> items);

    @Query("select * from Articlesitem where art_id = :id ;")
    List<ArticlesItem> getAllArticalsAtSource(String id);
}
