package com.example.roomwordssample.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomwordssample.Repository.WordRepository;
import com.example.roomwordssample.Room.WordEntity;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository mWordRepository;
    private LiveData<List<WordEntity>> mAllWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        this.mWordRepository = new WordRepository(application);
        this.mAllWords = mWordRepository.getAllWords();
    }

    public LiveData<List<WordEntity>> getAllWords() {
        return mAllWords;
    }

    public void insert(WordEntity word) {
        mWordRepository.insert(word);
    }
}
