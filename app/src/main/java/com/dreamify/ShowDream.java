package com.dreamify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ShowDream extends Activity {
    private TextView selectedDreamTitle;
    private TextView selectedDreamDetails;
    private CheckBox doneCheckbox;
    private ListView selectedCategoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dream);

        selectedDreamTitle = (TextView) findViewById(R.id.selectedDreamTitle);
        doneCheckbox = (CheckBox) findViewById(R.id.selectedDreamCheckbox);
        selectedCategoriesList = (ListView) findViewById(R.id.selectedDreamCategoriesSpinner);
        selectedDreamDetails = (TextView) findViewById(R.id.selectedDreamDetails);

        Intent intent = getIntent();
        Dream dream = (Dream) intent.getSerializableExtra("SELECTED_DREAM");

        selectedDreamTitle.setText(dream.getTitle());
        selectedDreamDetails.setText(dream.getDetails());
        doneCheckbox.setChecked(dream.isChecked());

        // Show dream categories in list
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        for(Category category: dream.getCategory()){
            arrayAdapter.add(category.toString());
            selectedCategoriesList.setAdapter(arrayAdapter);
        }
    }
}
