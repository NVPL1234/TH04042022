package com.example.SQLLiteTH.lab7b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="Place";
    private static final String TABLE_PLACE="Place";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_place_table= "CREATE TABLE " + TABLE_PLACE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" + ")";
        db.execSQL(create_place_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACE);
        onCreate(db);
    }

    public void addPlace(Place place){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME, place.getName());
        db.insert(TABLE_PLACE, null, values);
        db.close();
    }

    public void deletePlace(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_PLACE, KEY_NAME + " = ?", new String[]{name});
        db.close();
    }

    public List<Place> getPlaceList(){
        List<Place> placeList=new ArrayList<>();
        String sql="SELECT * FROM "+TABLE_PLACE;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                Place place=new Place();
                place.setName(cursor.getString(1));
                placeList.add(place);
            }while (cursor.moveToNext());
        }
        return placeList;
    }
}
