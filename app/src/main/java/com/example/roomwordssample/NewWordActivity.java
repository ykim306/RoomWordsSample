package com.example.roomwordssample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewWordActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.roomwordssample.REPLY";

    private EditText mEditWordView;
    private Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        mEditWordView = findViewById(R.id.editText_new_word);
        mSaveButton = findViewById(R.id.button_save_new_word);

        mSaveButton.setOnClickListener(new SaveButtonOnClickListener());
    }

    private class SaveButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent replyIntent = new Intent();

            if (TextUtils.isEmpty(mEditWordView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String word = mEditWordView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, word);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        }
    }
}
