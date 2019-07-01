package com.example.roomwordssample.Room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WordEntity {
    @NonNull
    @PrimaryKey
    @ColumnInfo
    private String mWord;

    public WordEntity(@NonNull String word) {
        this.mWord = word;
    }

    public String getWord() {
        return this.mWord;
    }
}
