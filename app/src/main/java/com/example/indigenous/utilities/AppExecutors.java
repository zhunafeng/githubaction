package com.example.indigenous.utilities;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class AppExecutors {
    private static AppExecutors instance;

    public Executor diskIO;
    public Executor networkIO;
    public Executor mainThread;

    public static AppExecutors getInstance() {
        if (instance == null) {
            synchronized (AppExecutors.class) {
                instance = new AppExecutors(
                        new DiskIOThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MainThreadExecutor());
            }
        }
        return instance;
    }

    public AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            mainThreadHandler.post(command);
        }
    }

    private static class DiskIOThreadExecutor implements Executor {
        private Executor diskIO;

        public DiskIOThreadExecutor() {
            diskIO = Executors.newSingleThreadExecutor();
        }

        @Override
        public void execute(Runnable command) {
            diskIO.execute(command);
        }
    }
}
