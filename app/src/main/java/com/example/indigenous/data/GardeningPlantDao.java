package com.example.indigenous.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.indigenous.Constants;

import java.util.List;

@Dao
public interface GardeningPlantDao {

    @Insert
    void savePlant(GardeningPlant plant);

    @Query("select * from "+ Constants.GARDEN_TABLE_NAME +" ")
    LiveData<List<GardeningPlant>> loadAllGardeningPlants();
}
