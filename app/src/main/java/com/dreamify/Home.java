package com.dreamify;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * App colours from https://www.materialpalette.com/blue-grey/lime
 */

public class Home extends Activity {
    public static final int NEW_DREAM_ACTIVITY = 111;
    public static final int SHOW_DREAM_ACTIVITY = 222;

    public TableLayout tableLayout;

    public static HashMap<String,Dream> dreamsInTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        dreamsInTable = new HashMap<>();

        // Filling with dummy dreams
        // TODO
        for(int i = 0; i < 5; i++){
            Category [] categories = new Category[] {Category.TRAVEL, Category.ACTIVITY};
            Dream dream = new Dream("Dream " + i, "Dream " + i + " description", categories, i);
            addDream(dream);
        }
    }

    /**
     * Called when user clicks '+' button
     */
    public void addBucketItem(View view){
        Intent intent = new Intent(this, NewBucketItem.class);
        startActivityForResult(intent, NEW_DREAM_ACTIVITY);
    }

    /**
     * Receive result from Activity and update the list
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (NEW_DREAM_ACTIVITY): {
                if (resultCode == RESULT_OK) {
                    Dream newDream = (Dream) data.getSerializableExtra("NEW_DREAM");
                    addDream(newDream);
                }
                break;
            }
        }
    }

    /**
     * Helper method for adding a new dream
     */
    private void addDream(Dream dream){
        final TextView textView = new TextView(this);
        textView.setText(dream.getTitle());
        textView.setTextSize(20);

        // Direct the user to the show dream activity
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ShowDream.class);
                TextView dreamTitle = (TextView) v;

                Dream selectedDream = dreamsInTable.get(dreamTitle.getText());

                intent.putExtra("SELECTED_DREAM", selectedDream);

                startActivityForResult(intent, SHOW_DREAM_ACTIVITY);
            }
        });

        CheckBox checkBox = new CheckBox(this);

        TableRow tableRow = new TableRow(this);
        tableRow.setBackgroundColor(Color.parseColor("#727272"));

        tableRow.addView(textView);
        tableRow.addView(checkBox);
        tableLayout.addView(tableRow);

        dreamsInTable.put(dream.getTitle(), dream);
    }
}
