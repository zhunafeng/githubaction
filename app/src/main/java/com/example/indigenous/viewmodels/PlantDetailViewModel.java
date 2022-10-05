package com.example.indigenous.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.indigenous.data.GardeningPlantDao;
import com.example.indigenous.data.GardeningPlantRepository;
import com.example.indigenous.data.Plant;
import com.example.indigenous.data.PlantRepository;

public class PlantDetailViewModel extends ViewModel {
    private PlantRepository plantRepository;
    public LiveData<Plant> currentPlant;
    public String plantID;

    public PlantDetailViewModel(PlantRepository plantRepository, String plantID) {
        this.plantRepository = plantRepository;
        this.plantID = plantID;
        this.currentPlant = plantRepository.getPlantByID(plantID);
    }

    public void savePlantID(GardeningPlantDao dao) {

        new GardeningPlantRepository(dao);
    }

}
