package com.example.indigenous;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.indigenous.data.AppDatabase;
import com.example.indigenous.data.Plant;
import com.example.indigenous.data.PlantRepository;
import com.example.indigenous.databinding.FragmentPlantDetialBinding;
import com.example.indigenous.utilities.AppExecutors;
import com.example.indigenous.viewmodels.MyGardenListViewModel;
import com.example.indigenous.viewmodels.PlantDetailViewModel;

public class PlantDetailFragment extends Fragment {


    private static final String PLANT_ID = "plantId";
    private static final String ARG_PARAM2 = "param2";


    private String mPlantID;
    private String mParam2;
    private Plant plant;
    private FragmentPlantDetialBinding binding;

    public PlantDetailFragment() {
        // Required empty public constructor
    }

    public static PlantDetailFragment newInstance(String param1, String param2) {
        PlantDetailFragment fragment = new PlantDetailFragment();
        Bundle args = new Bundle();
        args.putString(PLANT_ID, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPlantID = getArguments().getString(PLANT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlantDetialBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);

        PlantDetailFragmentArgs args = PlantDetailFragmentArgs.fromBundle(getArguments());
        PlantDetailViewModel plantDetailViewModel = new PlantDetailViewModel(
                PlantRepository.getInstance(AppDatabase.getInstance(getContext()).getPlantDao()), args.getPlantId());
        binding.setViewModel(plantDetailViewModel);

        binding.setOnClick(v -> {
            // TODO: 5/10/2022 save plantID to database who
            AppExecutors.getInstance().diskIO.execute(()->{
                plantDetailViewModel.savePlantID(AppDatabase.getInstance(getContext()).getGardeningPlantDao());
            });

            // TODO: 5/10/2022 go back to my garden fragment
            Navigation.findNavController(getView()).navigate(PlantDetailFragmentDirections.plantDetailAction());
        });

        return binding.getRoot();
    }

}