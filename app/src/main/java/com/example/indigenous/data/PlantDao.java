package com.example.indigenous.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.indigenous.Constants;

import java.util.List;

@Dao
public interface PlantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Plant> listLiveData);

    @Query("Select * from "+ Constants.TABLE_NAME+" order by name")
    LiveData<List<Plant>> getAllData();

    @Query("Select * from "+Constants.TABLE_NAME+" where id = :plantId ")
    LiveData<Plant> getPlantById(String plantId);

    @Query("select * from "+Constants.TABLE_NAME+" where growZoneNumber = :growZoneNumber order by name")
    LiveData<List<Plant>> getPlantWithGrowZone(String growZoneNumber);

    @Query("Select imageUrl, name, id from "+Constants.TABLE_NAME)
    LiveData<List<PlantImageAndNameOnly>> getImageAndNameOnly();
}
