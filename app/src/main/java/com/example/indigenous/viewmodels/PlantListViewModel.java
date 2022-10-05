package com.example.indigenous.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.indigenous.data.Plant;
import com.example.indigenous.data.PlantImageAndNameOnly;
import com.example.indigenous.data.PlantRepository;

import java.util.List;


public class PlantListViewModel extends ViewModel {
    private PlantRepository plantRepository;
    public LiveData<List<Plant>> plantListData;
    public LiveData<List<PlantImageAndNameOnly>> imageAndNameOnlyData;

    public PlantListViewModel(PlantRepository plantRepository) {
        super();
        this.plantRepository = plantRepository;
        imageAndNameOnlyData = plantRepository.getImageAndNameOnly();
    }
}
