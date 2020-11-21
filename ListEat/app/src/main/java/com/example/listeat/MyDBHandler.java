package com.example.listeat;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "listStorage.db";
    public static final String TABLE_INGREDIENTS = "ingredients";
    public static final String COLUMN_NAME_LIST = "nameList";
    public static final String COLUMN_INGREDIENTS = "nameIngredient";


    //We need to pass database information along to superclass
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_INGREDIENTS + "(" +
                COLUMN_NAME_LIST + " TEXT, " +
                COLUMN_INGREDIENTS + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        onCreate(db);
    }

    //Add a new row to the database
    public void addNewIngredientsList(MyList myList){
        String mesIngredients = myList.getMesIngredients();
        String nomList = myList.getNomList();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_LIST, nomList);
        values.put(COLUMN_INGREDIENTS,mesIngredients);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_INGREDIENTS, null, values);
        db.close();
    }

    public ArrayList<String> databaseToString(){

        ArrayList<String> list = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_INGREDIENTS;

        Cursor recordSet = db.rawQuery(query, null);

        recordSet.moveToFirst();

        while (!recordSet.isAfterLast()) {
            if (recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME_LIST)) != null) {
                list.add(recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME_LIST)));
            }
            recordSet.moveToNext();
        }
        db.close();
        return list;
    }

    public boolean nameExistAlready(String trim) {
        boolean exist = false;

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_INGREDIENTS;

        Cursor recordSet = db.rawQuery(query, null);
        recordSet.moveToFirst();

        while (!recordSet.isAfterLast()) {
            if (recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME_LIST)) != null) {
                exist = (recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME_LIST)).equals(trim));
            }
            recordSet.moveToNext();
        }
        db.close();

        return exist;
    }

    public void editIngredientsList(MyList myList, String nomList) {
        String newNom = myList.getNomList();
        String newIngredients = myList.getMesIngredients();

        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("UPDATE " + TABLE_INGREDIENTS + " SET "+ COLUMN_NAME_LIST + " = ?," + COLUMN_INGREDIENTS + " = ?" + " WHERE " + COLUMN_NAME_LIST + " = ? " ,
                new String[]{newNom,newIngredients,nomList});
        db.close();
    }

    public ArrayList<String> getListFromDB(String nomListe) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT "+ COLUMN_INGREDIENTS +" FROM " + TABLE_INGREDIENTS + " WHERE " + COLUMN_NAME_LIST + " = ?";
        ArrayList<String> displayedList = new ArrayList<String>();

        Cursor recordSet = db.rawQuery(query, new String[] { nomListe });
        recordSet.moveToFirst();

        while (!recordSet.isAfterLast()) {
            //On a un seul élément
            String strList = recordSet.getString(recordSet.getColumnIndex(COLUMN_INGREDIENTS));
            String[] tabList = strList.split(",");
            for (String st:tabList) {
                displayedList.add(st);
            }
            db.close();
            recordSet.moveToNext();
        }

        db.close();


        return displayedList;
    }

    public void deleteList(String nomList) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_INGREDIENTS + " WHERE " + COLUMN_NAME_LIST + " = ? " ,
                new String[]{nomList});
        db.close();
    }
}