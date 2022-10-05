package com.example.indigenous.data;

import androidx.annotation.Nullable;

public class PlantImageAndNameOnly {

    private String id;
    private String imageUrl;
    private String  name;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof PlantImageAndNameOnly
                && this.id.equals(((PlantImageAndNameOnly) obj).id);
    }
}
