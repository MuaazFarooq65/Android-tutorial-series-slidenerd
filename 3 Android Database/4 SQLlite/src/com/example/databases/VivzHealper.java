package com.example.databases;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VivzHealper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME="vivzdatabase";
	private static final String TABLE_NAME="VIVZTABLE";
	private static final int DATABASE_VERSION=3;
	
	private static final String UID="_id";
	private static final String NAME="Name";
	private static final String ADDRESS="Address";
	
	private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255));";
	private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
	private Context context;

	public VivzHealper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context=context;
		Message.message(context, "constructor called");
	}
	
	/*
	 * metodo chamado quando criamos DB pela 1 vez
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Message.message(context, "onCreate called");
		try {
			db.execSQL(CREATE_TABLE);
		} catch (SQLException e) {
			Message.message(context, ""+e);
		}
	}
	
	/*
	 * Modificacao tabelas
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Message.message(context, "onUpgrade called");
		try {
			db.execSQL(DROP_TABLE);
			onCreate(db);
		} catch (SQLException e) {
			Message.message(context, ""+e);
		}
	}

}
