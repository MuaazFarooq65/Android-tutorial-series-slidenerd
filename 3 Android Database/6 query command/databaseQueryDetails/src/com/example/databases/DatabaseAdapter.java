package com.example.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseAdapter {

	VivzHelper helper;

	public DatabaseAdapter(Context context) {
		helper = new VivzHelper(context);
	}

	// criamos inner classe para q ninguem acesse ao nome das tabelas + db
	public long inserData(String name, String password) {
		SQLiteDatabase db = helper.getWritableDatabase();

		// para inserimos dados preciamos classe content values
		ContentValues contentValues = new ContentValues();
		contentValues.put(VivzHelper.NAME, name);
		contentValues.put(VivzHelper.PASSWORD, password);
		
		long id = db.insert(VivzHelper.TABLE_NAME, null, contentValues);
		return id;
	}
	
	public String getAllData(){
		SQLiteDatabase db =helper.getWritableDatabase();
		String[] columns={VivzHelper.UID, VivzHelper.NAME, VivzHelper.PASSWORD};
		Cursor cursor=db.query(VivzHelper.TABLE_NAME, columns, null, null, null, null, null);
		StringBuffer buffer = new StringBuffer();
		while (cursor.moveToNext()) {
			int index1=cursor.getColumnIndex(VivzHelper.UID);
			int UID=cursor.getInt(index1);

			int index2=cursor.getColumnIndex(VivzHelper.NAME);
			String NAME=cursor.getString(index2);
			
			int index3=cursor.getColumnIndex(VivzHelper.PASSWORD);
			String PASSWORD=cursor.getString(index3);
			buffer.append(UID+" "+NAME+" "+PASSWORD+"\n");
		}
		return buffer.toString();
	}
	
	/*
	 * metemos alguma condicao
	 */
	public String getName(String name){
		SQLiteDatabase db =helper.getWritableDatabase();
		String[] columns={VivzHelper.NAME, VivzHelper.PASSWORD};
		Cursor cursor=db.query(VivzHelper.TABLE_NAME, columns, VivzHelper.NAME+" = '"+name+"'", null, null, null, null);
		StringBuffer buffer = new StringBuffer();
		while (cursor.moveToNext()) {
			String NAME=name;
			
			int index3=cursor.getColumnIndex(VivzHelper.PASSWORD);
			String PASSWORD=cursor.getString(index3);
			buffer.append(NAME+" "+PASSWORD+"\n");
		}
		return buffer.toString();
	}
	
	public String getIdWhereNamePassword(String name, String password){
		SQLiteDatabase db =helper.getWritableDatabase();
		String[] columns={VivzHelper.UID};
		String[] selectionArgs={name, password};
		Cursor cursor = db.query(VivzHelper.TABLE_NAME, columns,
				VivzHelper.NAME + " =? AND " + VivzHelper.PASSWORD + " =?",
				selectionArgs, null, null, null, null);
		StringBuffer buffer = new StringBuffer();
		while (cursor.moveToNext()) {
			
			int index0=cursor.getColumnIndex(VivzHelper.UID);
			int personId=cursor.getInt(index0);
			buffer.append(personId+"\n");
		}
		return buffer.toString();
	}

	static class VivzHelper extends SQLiteOpenHelper {
		private static final String DATABASE_NAME = "vivzdatabase";
		private static final String TABLE_NAME = "VIVZTABLE";
		private static final int DATABASE_VERSION = 6;

		private static final String UID = "_id";
		private static final String NAME = "Name";
		private static final String PASSWORD = "Password";

		private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
				+ " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME
				+ " VARCHAR(255), " + PASSWORD + " VARCHAR(255));";
		private static final String DROP_TABLE = "DROP TABLE IF EXISTS "
				+ TABLE_NAME;
		private Context context;

		public VivzHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			this.context = context;
			Message.message(context, "constructor called");
		}

		/*
		 * metodo chamado quando criamos DB pela 1 vez
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(CREATE_TABLE);
				Message.message(context, "onCreate called");
			} catch (SQLException e) {
				Message.message(context, "" + e);
				Log.d("andre", "--> "+e);
			}
		}

		/*
		 * Modificacao tabelas
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			try {
				Message.message(context, "onUpgrade called");
				db.execSQL(DROP_TABLE);
				onCreate(db);
			} catch (SQLException e) {
				Message.message(context, "" + e);
			}
		}
	}
}
