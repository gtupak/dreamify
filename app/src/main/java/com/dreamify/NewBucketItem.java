package com.dreamify;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.LinkedList;

public class NewBucketItem extends Activity {
    private EditText newDreamTitle;
    private EditText newDreamDetails;
    private ScrollView categoryScrollView;

    private ArrayList<Category> checkedCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bucket_item);

        newDreamTitle = (EditText) findViewById(R.id.newDreamTitle);
        newDreamDetails = (EditText) findViewById(R.id.newDreamDetails);
        categoryScrollView = (ScrollView) findViewById(R.id.categoriesScrollView);
        checkedCategories = new ArrayList<Category>(){
            @Override
            public Object[] toArray() {
                Category[] result = new Category[this.size()];
                int i = 0;
                while(this.iterator().hasNext()){
                    result[i] = this.iterator().next();
                }
                return result;
            }
        };

        // Put categories in the scroll view
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        for (Category category : Category.values()){
            CheckedTextView checkedTextView = new CheckedTextView(this);
            checkedTextView.setText(category.toString());
            checkedTextView.setTextColor(Color.parseColor("#212121"));
            checkedTextView.setCheckMarkDrawable(android.R.drawable.checkbox_off_background);
            linearLayout.addView(checkedTextView);
        }

        categoryScrollView.addView(linearLayout);
    }

    /**
     * Called when user clicks add new bucket item button
     */
    public void onAddButtonClick(View view){
        // Grab selected categories
        LinearLayout categoryChoiceLayout = (LinearLayout) categoryScrollView.getChildAt(0);

        CheckedTextView categoryChoice;
        for(int i = 0; i < categoryChoiceLayout.getChildCount(); i++){
            categoryChoice = (CheckedTextView) categoryChoiceLayout.getChildAt(0);
            if (categoryChoice.isChecked()){
                String categoryTitle = categoryChoice.getText().toString();
                if (categoryTitle.equals(Category.TRAVEL.toString())) {
                    checkedCategories.add(Category.TRAVEL);
                    continue;
                }
                else if (categoryTitle.equals(Category.FINANCIAL.toString())) {
                    checkedCategories.add(Category.FINANCIAL);
                    continue;
                }
                else if (categoryTitle.equals(Category.ACTIVITY.toString())) {
                    checkedCategories.add(Category.ACTIVITY);
                    continue;
                }
                else if (categoryTitle.equals(Category.ACHIEVEMENT.toString())) {
                    checkedCategories.add(Category.ACHIEVEMENT);
                    continue;
                }
            }
        }

        // Make Dream object for return
        Dream dream = new Dream(newDreamTitle.getText().toString(),
                                newDreamDetails.getText().toString(),
                                (Category[]) checkedCategories.toArray(),
                                21); // TODO dummy ID

        // Upload dream on database
        // TODO

        // Update home activity view
        Intent intent = getIntent();
        intent.putExtra("NEW_DREAM", dream);
        setResult(RESULT_OK, intent);
        finish();
    }
}
