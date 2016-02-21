package com.dreamify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

public class ShowDream extends Activity {
    private TextView selectedDream;
    private CheckBox doneCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dream);

        selectedDream = (TextView) findViewById(R.id.selectedDreamName);
        doneCheckbox = (CheckBox) findViewById(R.id.selectedDreamCheckbox);

        Intent intent = getIntent();
        String dream = intent.getStringExtra("dream");
        boolean isChecked = intent.getBooleanExtra("isChecked", false);

        selectedDream.setText(dream);
        doneCheckbox.setChecked(isChecked);
    }
}
