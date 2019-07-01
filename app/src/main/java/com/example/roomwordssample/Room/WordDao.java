package com.example.roomwordssample.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(WordEntity word);

    @Query("DELETE FROM WordEntity")
    void deleteAll();

    @Query("SELECT * FROM WordEntity ORDER BY mWord ASC")
    LiveData<List<WordEntity>> getAllWords();
}
