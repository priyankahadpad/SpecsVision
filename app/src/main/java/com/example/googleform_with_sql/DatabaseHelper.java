package com.example.googleform_with_sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

private static final String DATABASE_NAME = "mylist.db";
private static final String TABLE_NAME = "mylist_data";
private static final String COL1="ID";
private static final String COL2="name";

public DatabaseHelper (Context context){
    super(context,DATABASE_NAME,null,1);
}

    @Override
    public void onCreate(SQLiteDatabase db) {
      String createTable = "CREATE TABLE " +TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +  "name TEXT)";
      db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF  EXISTS " +TABLE_NAME);
    onCreate(db);

    }
    public boolean addData(String name){
    SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2, name);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;
        }else {
            return true;
        }}


        public void deleteData(long id) {
            SQLiteDatabase db = this.getWritableDatabase();
            String query= ("DELETE FROM" +TABLE_NAME+ "WHERE" +COL1 + "='" +id+ "'");
            db.close();
        }

    public Cursor getData()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        String query="SELECT * FROM " +TABLE_NAME;
        Cursor data=db.rawQuery(query,null);
        return data;

    }

}






