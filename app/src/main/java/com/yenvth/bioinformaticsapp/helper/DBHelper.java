package com.yenvth.bioinformaticsapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yenvth.bioinformaticsapp.model.History;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "BioDB";
    // Table Names
    private static final String TABLE_HISTORIES = "histories";
    //Todo: Word table create statement

    private static final String CREATE_TOPICS = "CREATE TABLE " + TABLE_HISTORIES +
            "( history_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "                protein_id_1 VARCHAR(15) NOT NULL ," +
            "                protein_id_2 VARCHAR(15) NOT NULL , " +
            "                protein_name_1 VARCHAR(30) NOT NULL ," +
            "                protein_name_2 VARCHAR(30) NOT NULL ," +
            "                interaction INT NOT NULL ," +
            "                probability DOUBLE NOT NULL)";


    private SQLiteDatabase sqLiteDatabase;
    private ContentValues contentValues;
    private Cursor cursor;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TOPICS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORIES);
            onCreate(db);
        }
    }

    //--------- Functions for Word HISTORIES-------------------//
    public ArrayList<History> getHistories() {
        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.query(true, TABLE_HISTORIES, null, null, null, null, null, null, null);
        ArrayList<History> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int his_id = cursor.getInt(cursor.getColumnIndex("history_id"));
            String protein_id_1 = cursor.getString(cursor.getColumnIndex("protein_id_1"));
            String protein_id_2 = cursor.getString(cursor.getColumnIndex("protein_id_2"));
            String protein_name_1 = cursor.getString(cursor.getColumnIndex("protein_name_1"));
            String protein_name_2 = cursor.getString(cursor.getColumnIndex("protein_name_2"));
            int interaction = cursor.getInt(cursor.getColumnIndex("interaction"));
            double probability = cursor.getDouble(cursor.getColumnIndex("probability"));
            list.add(new History(his_id, protein_id_1, protein_id_2, protein_name_1, protein_name_2, interaction, probability));
        }
        closeDB();
        return list;
    }

    public void insertHistory(History his) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("protein_id_1", his.getProtein_id_1());
        contentValues.put("protein_id_2", his.getProtein_id_2());
        contentValues.put("protein_name_1", his.getProtein_name_1());
        contentValues.put("protein_name_2", his.getProtein_name_2());
        contentValues.put("interaction", his.getInteraction());
        contentValues.put("probability", his.getProbability());


        sqLiteDatabase.insert(TABLE_HISTORIES, null, contentValues);
    }

    public void deleteAllHistories() {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORIES);
        closeDB();
    }

    public void deleteHistory(int his_id) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_HISTORIES + " WHERE topic_id = " + his_id);
        closeDB();
    }


    //Todo: Đóng Database
    private void closeDB() {
        if (sqLiteDatabase != null) sqLiteDatabase.close();
        if (contentValues != null) contentValues.clear();
        if (cursor != null) cursor.close();
    }

}
