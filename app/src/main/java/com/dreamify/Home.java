package com.dreamify;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.LinkedList;

public class Home extends Activity {
    public static final int NEW_DREAM_ACTIVITY = 111;
    public ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ListView listView = (ListView) findViewById(R.id.listView);

        LinkedList<String> linkedStringList = new LinkedList<String>();

        for(int i = 0; i < 5; i++){
            linkedStringList.add("Item " + i);
        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, linkedStringList);

        listView.setAdapter(arrayAdapter);
    }

    /**
     * Called when user clicks '+' button
     */
    public void addBucketItem(View view){
        Intent intent = new Intent(this, NewBucketItem.class);
        startActivityForResult(intent, NEW_DREAM_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (NEW_DREAM_ACTIVITY): {
                if (resultCode == RESULT_OK) {
                    String newDream = data.getStringExtra("dream");
                    arrayAdapter.add(newDream);
                }
                break;
            }
        }
    }
}
