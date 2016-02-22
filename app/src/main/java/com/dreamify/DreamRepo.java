package kevinhuang.dreamify;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by test on 2/21/2016.
 */
public class DreamRepo {
    private DBHelper dbHelper;

    public DreamRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insertD(Dream dream) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Dream.KEY_DID, dream.dream_ID);
        values.put(Dream.KEY_DTEXT, dream.text);
        values.put(Dream.KEY_CATEGORY, dream.category);

        // Inserting Row
        long dream_Id = db.insert(Dream.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) dream_Id;
    }

    public void deleteD(int dream_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Dream.TABLE, Dream.KEY_DID + "= ?", new String[] { String.valueOf(dream_Id) });
        db.close(); // Closing database connection
    }

    public void updateU(Dream dream) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Dream.KEY_DTEXT, dream.text);
        values.put(Dream.KEY_CATEGORY, dream.category);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Dream.TABLE, values, Dream.KEY_DID + "= ?", new String[] { String.valueOf(dream.dream_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getDreamList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Dream.KEY_DID + "," +
                Dream.KEY_DTEXT + "," +
                Dream.KEY_CATEGORY + "," +
                " FROM " + Dream.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> dreamList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> dream = new HashMap<String, String>();
                dream.put("id", cursor.getString(cursor.getColumnIndex(Dream.KEY_DID)));
                dream.put("name", cursor.getString(cursor.getColumnIndex(User.KEY_NAME)));
                dreamList.add(dream);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return dreamList;

    }

    public Dream getDreamById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Dream.KEY_DID + "," +
                Dream.KEY_DTEXT + "," +
                Dream.KEY_CATEGORY + "," +
                " FROM " + Dream.TABLE
                + " WHERE " +
                Dream.KEY_DID + "=?";

        int iCount =0;
        Dream dream = new Dream();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                dream.dream_ID =cursor.getInt(cursor.getColumnIndex(Dream.KEY_DID));
                dream.text =cursor.getString(cursor.getColumnIndex(Dream.KEY_DTEXT));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return dream;
    }
}
