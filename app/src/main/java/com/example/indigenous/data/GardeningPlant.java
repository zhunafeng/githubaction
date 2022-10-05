package com.example.indigenous.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.indigenous.Constants;

@Entity(tableName = Constants.GARDEN_TABLE_NAME)
public class GardeningPlant {

    @NonNull
    @ColumnInfo(name = "plant_id")
    private String pantID;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setPantID(@NonNull String pantID) {
        this.pantID = pantID;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getPantID() {
        return pantID;
    }

    public int getId() {
        return id;
    }

    public GardeningPlant(@NonNull String pantID) {
        this.pantID = pantID;
    }
}
