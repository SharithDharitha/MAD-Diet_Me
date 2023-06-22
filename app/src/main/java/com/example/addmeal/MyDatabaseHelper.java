package com.example.addmeal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "dietMe.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME= "items";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_FOODNAME = "food_name_1";
    private static final String COLUMN_ADDRESS = "address";

    private static final String TABLE_NAME1 = "User";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "USERNAME";
    private static final String COL_3 = "EMAIL";
    private static final String COL_4 = "PASSWORD";





    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION );
        this.context = context;
    }


    // Create Table
    @Override
    public void onCreate(SQLiteDatabase db) {

        //For login
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME1 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , USERNAME TEXT UNIQUE, EMAIL TEXT, PASSWORD TEXT)");

        //For Device Details
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_FOODNAME + " TEXT, " +
                COLUMN_ADDRESS + " TEXT); ";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
          onCreate(db);

    }

    // Add Item details
    void addItem(String Category, String FoodName_Type, String Address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CATEGORY, Category);
        cv.put(COLUMN_FOODNAME, FoodName_Type);
        cv.put(COLUMN_ADDRESS, Address);

        long result = db.insert(TABLE_NAME, null,cv);
        if(result == -1){
            Toast.makeText(context,"Failed ! ",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Added Successful !",Toast.LENGTH_SHORT).show();
        }

    }

    // Retrive All Of Data
    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return  cursor;
    }


    // Update Item Details
    void UpdateData(String row_id, String Category, String FoodName_Type, String Address){
        Log.d("totallllllll",Category);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  cv =new ContentValues();
        cv.put(COLUMN_CATEGORY, Category);
        cv.put(COLUMN_FOODNAME, FoodName_Type);
        cv.put(COLUMN_ADDRESS, Address);

        long result = db.update(TABLE_NAME, cv, "id=?", new String[]{row_id});

        if(result == -1){
            Toast.makeText(context, "Update failed ", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Updated Successfully.  ", Toast.LENGTH_SHORT).show();
        }
    }

    //Delete Selected raw
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{row_id});
        //db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NAME + "'");
        if(result == -1){
            Toast.makeText(context,"Fail to delete !", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Delete Successfully !", Toast.LENGTH_SHORT).show();
        }
    }



    //Login & Sign up Start
    public boolean registerUser(String username , String email , String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2 , username);
        values.put(COL_3 , email);
        values.put(COL_4 , password);

        long result = db.insert(TABLE_NAME1 , null , values);
        return result != -1;

    }

    public boolean updatepassword(String username , String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_4, password);

        long result = db.update(TABLE_NAME1 ,values , "USERNAME =?", new String[] {username} );
        return result != -1;

    }



    public boolean checkUser(String username , String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String [] columns = { COL_1 };
        String selection = COL_2 + "=?" + " and " + COL_4 + "=?";
        String [] selectionargs = { username , password};
        Cursor cursor = db.query(TABLE_NAME1 , columns , selection , selectionargs , null ,null , null);
        int count = cursor.getCount();
        db.close();
        cursor.close();
        return count > 0;

    }


    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String [] columns = { COL_1 };
        String selection = COL_2 + "=?";
        String [] selectionargs = {username};
        Cursor cursor = MyDB.query(TABLE_NAME1 , columns , selection , selectionargs , null,null,null);
        int count = cursor.getCount();
        cursor.close();
        return cursor.getCount() > 0;

    }



}
