package com.example.roomwordssample;

import android.content.Intent;
import android.os.Bundle;

import com.example.roomwordssample.RecyclerView.WordListAdapter;
import com.example.roomwordssample.Room.WordEntity;
import com.example.roomwordssample.ViewModel.WordViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 100;

    private WordViewModel wordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new FabOnClickListener());

        RecyclerView recyclerView = findViewById(R.id.recyclerView_word);
        final WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        wordViewModel.getAllWords().observe(this, new Observer<List<WordEntity>>() {
            @Override
            public void onChanged(List<WordEntity> wordEntities) {
                adapter.setWords(wordEntities);
            }
        });
    }

    public void onActivityResult(int requestcode, int resultcode, Intent data) {
        super.onActivityResult(requestcode, resultcode, data);

        if (requestcode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultcode == RESULT_OK) {
            WordEntity word = new WordEntity(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
            wordViewModel.insert(word);
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.no_word_entered)
                            , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class FabOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent fabIntent = new Intent(MainActivity.this, NewWordActivity.class);
            startActivityForResult(fabIntent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        }
    }
}
