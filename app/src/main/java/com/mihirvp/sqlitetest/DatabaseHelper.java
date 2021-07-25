package com.mihirvp.sqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String pass_store = "PASS_STORE";
    public static final String column_url = "URL";
    public static final String column_password = "PASSWORD";
    public static final String column_status = "ACTIVE_STATUS";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Storage.db", null, 1);

    }




    //First Time Creating Database
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + pass_store +
                " (SID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                column_url + " TEXT, " +
                column_password + " TEXT, " +
                column_status + " BOOLEAN )";

        db.execSQL(createTableStatement);

    }


    //On Version Upgrade eg. Adding New Parameters Etc.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }
    //add Item (one)
    public boolean addOne(StoreModel storeModel){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(column_url, storeModel.getUrl());
        contentValues.put(column_password, storeModel.getPassword1());
        contentValues.put(column_status, storeModel.isActive());

        long insert = db.insert(pass_store, null, contentValues);
        if (insert == -1){
            return false;
        }
        else  return  true;
    }

    //adding Delete Functionality
    public boolean deleteOne(StoreModel storeModel){

        //find a particular URL, Delete if present and send true, send false else
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = " DELETE FROM "+ pass_store +" WHERE SID = "+ storeModel.getUsid() ;

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            return true;
        }else return false;
    }




    public List<StoreModel> getEveryone(){
        List<StoreModel> returnList = new ArrayList<>();

        String querryString = "SELECT * FROM "+ pass_store ;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(querryString,null);

        if (cursor.moveToFirst()){
            //loop through if results present

            do {
                int urlID = cursor.getInt(0);
                String urlName = cursor.getString(1);
                String passwordName = cursor.getString(2);
                boolean isPassActive = cursor.getInt(3) == 1;

                StoreModel newStoreModel = new StoreModel(urlID,urlName,passwordName,isPassActive);
                returnList.add(newStoreModel);

            }while (cursor.moveToNext());

        }else {
            //Toast.makeText(StoreModel.this,"Cannot store new Values",Toast.LENGTH_SHORT).show();

            System.out.println( "Cannot resolve");
        }

        return  returnList;

    }

}
