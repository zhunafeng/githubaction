package com.example.indigenous.data;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * repository to handle all data from online or local
 */
public class PlantRepository {
    private PlantDao plantDao;
    private static PlantRepository instance;

    public PlantRepository(PlantDao plantDao) {
        this.plantDao = plantDao;
    }

    public static PlantRepository getInstance(PlantDao plantDao){
        if (instance == null){
            synchronized (PlantRepository.class){
                instance = new PlantRepository(plantDao);
            }
        }
        return instance;
    }

    public LiveData<List<Plant>> getAllPlant(){
        if (plantDao != null){
            return plantDao.getAllData();
        }
        return null;
    }

    public LiveData<List<PlantImageAndNameOnly>> getImageAndNameOnly(){
        if (plantDao != null){
            return plantDao.getImageAndNameOnly();
        }
        return null;
    }

    public LiveData<Plant> getPlantByID(String plantID){
        if (plantDao != null){
            return plantDao.getPlantById(plantID);
        }
        return null;
    }


}
