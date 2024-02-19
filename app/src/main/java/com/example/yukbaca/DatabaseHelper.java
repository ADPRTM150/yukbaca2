package com.example.yukbaca;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;



public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    static abstract class MyColumns implements BaseColumns {
        public static final String TABLE_NAME = "people_table";
        public static final String ID = "ID";
        public static final String NAME = "name";
        public static final String NILAI = "nilai";
        public static final String NILAI1 = "nilai1";
        public static final String NILAI2 = "nilai2";
        public static final String NILAI3 = "nilai3";
    }

    public DatabaseHelper(Context context) {
        super(context, MyColumns.TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " +
                MyColumns.TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + MyColumns.NAME +" TEXT, "+ MyColumns.NILAI +" TEXT, "+ MyColumns.NILAI1 +" TEXT, "+ MyColumns.NILAI2 +" TEXT, "+ MyColumns.NILAI3 +" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(String.format("DROP IF TABLE EXISTS %s", MyColumns.TABLE_NAME));
        onCreate(db);
    }

}
























