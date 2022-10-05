package com.example.indigenous.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.indigenous.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class SeedDatabaseWorker extends Worker {

    public SeedDatabaseWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            InputStream inputStream = getApplicationContext().getAssets().open(Constants.JSON_NAME);
            JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream));
            Type type = new TypeToken<List<Plant>>(){}.getType();
            List<Plant> list = new Gson().fromJson(jsonReader,type);
            inputStream.close();

            AppDatabase.getInstance(getApplicationContext()).getPlantDao().insertAll(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success();
    }
}
