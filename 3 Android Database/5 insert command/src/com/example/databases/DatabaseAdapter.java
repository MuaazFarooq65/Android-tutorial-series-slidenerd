package com.example.databases;

import android.content.ContentValues;
import android.content.Context;
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
