package com.example.indigenous.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GardeningPlantRepository {
    private GardeningPlantDao dao;

    public GardeningPlantRepository(GardeningPlantDao dao) {
        this.dao = dao;
    }

    public LiveData<List<GardeningPlant>> loadAllPlants(){
        return dao.loadAllGardeningPlants();
    }

}
