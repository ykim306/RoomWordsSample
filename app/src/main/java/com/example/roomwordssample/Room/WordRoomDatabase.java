package com.example.roomwordssample.Room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {WordEntity.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDao wordDao();

    private static WordRoomDatabase INSTANCE;

    public  static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                            , WordRoomDatabase.class, "WordEntity")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final WordDao mWordDao;

        PopulateDbAsync(WordRoomDatabase wordRoomDatabase) {
            mWordDao = wordRoomDatabase.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            int TOTAL_SAMPLE_WORD_COUNT = 3;
            mWordDao.deleteAll();

            for (int i=0; i<TOTAL_SAMPLE_WORD_COUNT; i++) {
                mWordDao.insert(new WordEntity("Word " + i));
            }

            return null;
        }
    }
}
