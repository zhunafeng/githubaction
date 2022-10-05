package com.example.indigenous.utilities;

import android.content.Context;

import com.example.indigenous.data.AppDatabase;
import com.example.indigenous.data.PlantDao;
import com.example.indigenous.data.PlantRepository;
import com.example.indigenous.viewmodels.PlantListViewModel;

public class InjectorUtils {

    public static PlantRepository getPlantRepository(Context context){
        return PlantRepository.getInstance(AppDatabase.getInstance(context).getPlantDao());
    }

}
