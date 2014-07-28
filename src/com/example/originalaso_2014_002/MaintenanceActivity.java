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

public class MaintenanceActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener{
SQLiteDatabase sdb = null;

MySQLiteOpenHelper helper = null;

int selectedID = -1;

int lastPosition = -1;

@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO 自動生成されたメソッド・スタブ
	super.onCreate(savedInstanceState);
	setContentView(R.layout.maintenance);
}
private void setDBValuetoList(ListView lstHitokoto){
	
	SQLiteCursor cursor = null;

	if(sdb == null){
		helper = new MySQLiteOpenHelper(getApplicationContext());
	}
	try{
		sdb = helper.getWritableDatabase();
	}catch(SQLiteException e){

		Log.e("ERROR",e.toString());
	}
	cursor = this.helper.selectHitokotoList(sdb);

	int db_layout = android.R.layout.simple_list_item_activated_1;

	String[]from = {"phrase"};

	int[]to = new int[]{android.R.id.text1};

	SimpleCursorAdapter adapter =
	  new SimpleCursorAdapter(this,db_layout,cursor,from,to,0);

	lstHitokoto.setAdapter(adapter);
}

private void deleteFromHitokoto(int id){
	
	if(sdb == null){
		helper = new MySQLiteOpenHelper(getApplicationContext());
	}
	try{
		sdb = helper.getWritableDatabase();
	}catch(SQLiteException e){
		Log.e("ERROR",e.toString());
	}
	this.helper.deleteHitokoto(sdb, id);
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
	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){
		case R.id.btnDELETE:
			
			if(this.selectedID != -1){
				this.deleteFromHitokoto(this.selectedID);
				ListView lstHitokoto = (ListView)findViewById(R.id.LyHITOKOTO);
				
				this.setDBValuetoList(lstHitokoto);
				
				this.selectedID =-1;
				this.lastPosition = -1;	
			}
			else{
				Toast.makeText(MaintenanceActivity.this,"削除する行を選んでください",Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.btnMAINTE_BACk:
		finish();
		break;
		
		}
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}