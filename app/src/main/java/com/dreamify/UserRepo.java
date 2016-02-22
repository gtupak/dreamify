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
public class UserRepo {
    private DBHelper dbHelper;

    public UserRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insertU(User user) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.KEY_UID, user.user_ID);
        values.put(User.KEY_NAME, user.username);

        // Inserting Row
        long user_Id = db.insert(User.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) user_Id;
    }

    public void deleteU(int user_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(User.TABLE, User.KEY_UID + "= ?", new String[] { String.valueOf(user_Id) });
        db.close(); // Closing database connection
    }

    public void updateU(User user) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(User.KEY_NAME, user.username);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(User.TABLE, values, User.KEY_UID + "= ?", new String[] { String.valueOf(user.user_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getStudentList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                User.KEY_UID + "," +
                User.KEY_NAME + "," +
                " FROM " + User.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> userList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("id", cursor.getString(cursor.getColumnIndex(User.KEY_UID)));
                user.put("name", cursor.getString(cursor.getColumnIndex(User.KEY_NAME)));
                userList.add(user);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;

    }

    public User getUserById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                User.KEY_UID + "," +
                User.KEY_NAME + "," +
                " FROM " + User.TABLE
                + " WHERE " +
                User.KEY_UID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        User user = new User();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                user.user_ID =cursor.getInt(cursor.getColumnIndex(User.KEY_UID));
                user.username =cursor.getString(cursor.getColumnIndex(User.KEY_NAME));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return user;
    }
}
