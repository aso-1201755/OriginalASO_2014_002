package com.example.originalaso_2014_002;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper{
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
	public void insertHitokoto(SQLiteDatabase db,String inputMsg){
		String sqlstr = "insert into Hitokoto(phrase)values(`"+inputMsg+"`);";
		try{
			db.beginTransaction();
			db.execSQL(sqlstr);
			db.setTransactionSuccessful();
		} catch (SQLException e){
			Log.e("ERROR",e.toString());
		}finally {
			db.endTransaction();
		}
		return;
	}
	public SQLiteCursor selectRandomHitokoto(SQLiteDatabase db){
		
		String rtString = null;
		
		String sqlst = "SELECT_id,phrase From Hitokoto ORDER BY RANDOM();";
		try{
			SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(sqlst,null);
			if(cursor.getCount()!=0){
				cursor.moveToFirst();
				rtString = cursor.getString(1);
			}
			cursor.close();
			
			
		} catch(SQLException e){
			Log.e("ERROR",e.toString());
		}finally{
			
			SQLiteCursor cursor =null;
			
			String sqlstr = "SELECT _id,phrase From Hitokoto ORDER BY _id;";
			try{
				cursor = (SQLiteCursor)db.rawQuery(sqlstr,null);
				if(cursor.getCount()!=0){
					cursor.moveToFirst();
				}
			}catch (SQLException e){
				Log.e("EROR",e.toString());
			}finally{
				
			}
			return cursor;
			
		  }
		public void deleteHitokoto(SQLiteDatabase db, int id){
			String sqlstr ="DELETE FROM Hitokoto _id = " +id+ ";";
			try{
				db.beginTransaction();
				db.execSQL(sqlstr);
				db.setTransactionSuccessful();
				catch(SQLException e){
					Log.e("ERROR",e.toString());
				}finally{
					db.endTransaction();
				}
			}
		}
		
		return rtString;
	}
}
