package com.example.originalaso_2014_002;

import android.app.Activity;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
public class MaintenanceActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListene
SQLiteDatabase sdb = null;

MySQLiteOpenHelper helper = null;

int selectedID = -1;

int lastPosition = -1;

	SQLiteCursor cursor = null;

	if(sdb == null){
		helper = new MySQliteOpenHelper(getApplicationContext());
	}
	try{
		sdb = helper.getWritableDatabase();
	}catch(SQLiteEception e){

		Log.e("EROOER",e.toString);
	}
	cursor = this.helper.selectHitokotoList(sdb);

	int db_layout = android.R.layout.simple_list_item_activated_1;

	String[]from = {"phrase"};

	int[]to = new int[]{android.R.id.text1};

	SimpleCursorAdapter adaptor =
	  newSimpleCursorAdapter(this,db_layout,cursor,from,to,0);

	lstHitokoto.setAdapter(adapter);
}
	@Override
	protected void onResume(){
		// TODO 自動作成されたメゾッド・スタブ
		super.onResume();

		Button btnDelete = (Button)findViewById(R.id.btnDELETE);
		Button btnMainte_Back = (Button)findViewById(R.id.btnMAINTE_BACK);
		ListView lstHitokoto = (ListView)findViewById(R.id.LyHITOKOTO);

		btnDelete.setOnClickListener(this);
		btnMainte_Back.setOnClickListener(this);

		lstHitokoto.setOnItemClickListener(this);

		this.setDBValuetoList(lstHitokoto);

	}
}