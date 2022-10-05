package com.example.indigenous.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.indigenous.Constants;

@Database(entities = {Plant.class,GardeningPlant.class}, version = 1, exportSchema = false)
@TypeConverters()
public abstract class AppDatabase extends RoomDatabase {

    public abstract PlantDao getPlantDao();
    public abstract GardeningPlantDao getGardeningPlantDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                instance = buildDatabase(context);
            }
        }
        return instance;
    }

    private static AppDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, Constants.DATABASE_NAME).
                addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        // TODO: 5/10/2022 插入内容
                        WorkManager.getInstance(context).enqueue(OneTimeWorkRequest.from(SeedDatabaseWorker.class));
                    }
                }).build();
    }
}
