package com.example.indigenous.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.indigenous.data.GardeningPlant;
import com.example.indigenous.data.GardeningPlantRepository;

import java.util.List;

public class MyGardenListViewModel extends ViewModel {

    public LiveData<List<GardeningPlant>> data;

    public MyGardenListViewModel(GardeningPlantRepository repository) {
        data = repository.loadAllPlants();
    }

    public void savePlantID(String id) {

    }
}
