package com.example.originalaso_2014_002;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
public class MySQLiteOpenHelper{
	public MySQLiteOpenHelper(Context context){
		super(context, "20140021201755.sqlite3", null, 1);
	}
	public void onCreate(SQLiteDatabase db){
		db.execSQL("CREATE TABLE IF NOT EXISTS"+
	"Hitokoto(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT MULL,phrase TEXT)");
	}
	public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
		db.execSQL("drop table Hitokoto");
		onCreate(db);
	}
}
