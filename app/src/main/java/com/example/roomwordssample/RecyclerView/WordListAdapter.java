package com.example.roomwordssample.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordssample.R;
import com.example.roomwordssample.Room.WordEntity;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private final LayoutInflater mLayoutInflater;
    // Cached copy of words
    private List<WordEntity> mWords;
    private Context mContext;

    public WordListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.recyclerview_item_word, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        if (mWords != null) {
            WordEntity currentItem = mWords.get(position);
            holder.wordItemView.setText(currentItem.getWord());
        } else {
            holder.wordItemView.setText(mContext.getString(R.string.no_data_found));
        }
    }

    @Override
    public int getItemCount() {
        if (mWords != null) {
            return mWords.size();
        } else {
            return 0;
        }
    }

    public void setWords(List<WordEntity> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView_wordItem);
        }
    }
}
