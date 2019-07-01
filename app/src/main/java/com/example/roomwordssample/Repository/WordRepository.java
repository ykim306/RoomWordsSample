package com.example.roomwordssample.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.roomwordssample.Room.WordDao;
import com.example.roomwordssample.Room.WordEntity;
import com.example.roomwordssample.Room.WordRoomDatabase;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<WordEntity>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<WordEntity>> getAllWords() {
        return  mAllWords;
    }

    public void insert(WordEntity word) {
        // AsyncTask call
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<WordEntity, Void, Void> {
        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao wordDao) {
            this.mAsyncTaskDao = wordDao;
        }

        @Override
        protected Void doInBackground(WordEntity... wordEntities) {
            mAsyncTaskDao.insert(wordEntities[0]);
            return null;
        }
    }
}
