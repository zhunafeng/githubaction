package com.example.indigenous.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indigenous.PlantListFragmentDirections;
import com.example.indigenous.data.PlantImageAndNameOnly;
import com.example.indigenous.databinding.ListPlantBinding;

public class PlantListAdapter extends ListAdapter<PlantImageAndNameOnly, PlantListAdapter.PlantListViewHolder> {

    public PlantListAdapter() {
        super(new PlantDiffCallback());
    }

    @NonNull
    @Override
    public PlantListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListPlantBinding binding = ListPlantBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new PlantListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantListViewHolder holder, int position) {
        PlantImageAndNameOnly plant = getItem(position);
        holder.bind(createOnClick(plant.getId()), plant);
        holder.itemView.setTag(plant);
    }

    private View.OnClickListener createOnClick(String plantId) {
        return v -> Navigation.findNavController(v).navigate(PlantListFragmentDirections.plantListToPlantDetail(plantId));
    }


    static class PlantListViewHolder extends RecyclerView.ViewHolder {
        private ListPlantBinding binding;

        public PlantListViewHolder(@NonNull ListPlantBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bind(View.OnClickListener listener, PlantImageAndNameOnly plant) {
            binding.setOnClick(listener);
            binding.setPlant(plant);
            binding.executePendingBindings();
        }
    }

    static class PlantDiffCallback extends DiffUtil.ItemCallback<PlantImageAndNameOnly> {


        @Override
        public boolean areItemsTheSame(@NonNull PlantImageAndNameOnly oldItem, @NonNull PlantImageAndNameOnly newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull PlantImageAndNameOnly oldItem, @NonNull PlantImageAndNameOnly newItem) {
            return oldItem.equals(newItem);
        }
    }
}
