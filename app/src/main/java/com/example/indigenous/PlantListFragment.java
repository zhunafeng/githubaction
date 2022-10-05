package com.example.indigenous;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.indigenous.adapters.PlantListAdapter;
import com.example.indigenous.data.AppDatabase;
import com.example.indigenous.data.PlantRepository;
import com.example.indigenous.databinding.FragmentPlantListBinding;
import com.example.indigenous.utilities.InjectorUtils;
import com.example.indigenous.viewmodels.PlantListViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlantListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlantListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private PlantListViewModel plantListViewModel;

    public PlantListFragment() {
        // Required empty public constructor
    }

    public static PlantListFragment newInstance(String param1, String param2) {
        PlantListFragment fragment = new PlantListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPlantListBinding binding = FragmentPlantListBinding.inflate(inflater,container,false);

        this.plantListViewModel = new PlantListViewModel(InjectorUtils.getPlantRepository(getContext()));

        PlantListAdapter adapter = new PlantListAdapter();
        binding.plantListRecycler.setAdapter(adapter);
        PlantListViewModel viewModel = new PlantListViewModel(PlantRepository.getInstance(AppDatabase.getInstance(container.getContext()).getPlantDao()));
        viewModel.imageAndNameOnlyData.observe(getViewLifecycleOwner(),plants -> {
            if (plants != null){
                adapter.submitList(plants);
            }
        });
        return binding.getRoot();
    }

}