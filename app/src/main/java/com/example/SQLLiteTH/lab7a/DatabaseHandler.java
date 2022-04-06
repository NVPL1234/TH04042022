package com.example.SQLLiteTH.lab7a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="Person";
    private static final String TABLE_PERSON="Person";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_person_table= "CREATE TABLE " + TABLE_PERSON + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT"+ ")";
        db.execSQL(create_person_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PERSON);
        onCreate(db);
    }

    public void addPerson(Person person){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME, person.getName());
        db.insert(TABLE_PERSON,null,values);
        db.close();
    }

    public void deletePerson(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_PERSON, KEY_NAME + " = ?", new String[]{name});
        db.close();
    }

    public List<Person> getPersonList(){
        List<Person> personList=new ArrayList<>();
        String selectQuery="SELECT * FROM "+TABLE_PERSON;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Person person=new Person();
                person.setName(cursor.getString(1));
                personList.add(person);
            }while (cursor.moveToNext());
        }
        return personList;
    }
}
