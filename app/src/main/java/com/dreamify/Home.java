package com.dreamify;

import android.app.Activity;
import android.content.Intent;
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

import java.util.LinkedList;

public class Home extends Activity {
    public static final int NEW_DREAM_ACTIVITY = 111;
    public static final int SHOW_DREAM_ACTIVITY = 222;

    public TableLayout tableLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        for(int i = 0; i < 5; i++){
            addDream("Item " + i);
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
                    String newDream = data.getStringExtra("dream");

                    addDream(newDream);
                }
                break;
            }
        }
    }

    /**
     * Helper method for adding a new dream
     */
    private void addDream(String dream){
        final TextView textView = new TextView(this);
        textView.setText(dream);
        textView.setTextSize(20);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ShowDream.class);
                TextView textView1 = (TextView) v;
                TableRow tableRowParent= (TableRow) v.getParent();
                CheckBox relatedCheckbox = (CheckBox) tableRowParent.getChildAt(1); // child(0) is text, child(1) is checkbox

                intent.putExtra("dream", textView1.getText());
                intent.putExtra("isChecked", relatedCheckbox.isChecked());

                startActivityForResult(intent, SHOW_DREAM_ACTIVITY);
            }
        });

        CheckBox checkBox = new CheckBox(this);

        TableRow tableRow = new TableRow(this);
        tableRow.addView(textView);
        tableRow.addView(checkBox);
        tableLayout.addView(tableRow);
    }
}
