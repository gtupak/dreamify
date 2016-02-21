package com.dreamify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class NewBucketItem extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bucket_item);
    }

    /**
     * Called when user clicks add new bucket item button
     */
    public void onAddButtonClick(View view){
        // Grab editText from view
        EditText editText = (EditText) findViewById(R.id.editText);
        String newBucketItemName = editText.getText().toString();

        // Upload dream on database
        // TODO

        // Update home activity view
        // Temporary
        Intent intent = getIntent();
        intent.putExtra("dream", newBucketItemName);
        setResult(RESULT_OK, intent);
        finish();
    }
}
