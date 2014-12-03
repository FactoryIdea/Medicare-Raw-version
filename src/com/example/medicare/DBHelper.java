package com.example.medicare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "MEDICALDATA";
	private static final int DATABASE_VERSION = 9;
	private static final String TABLE_NAME1 = "BRAND";
	private static final String TABLE_NAME2 = "COMPOSITION";
	private static final String TABLE_NAME3 = "CATEGORY";
	private static final String TABLE_QUERY1 = "create table if not exists "
			+ TABLE_NAME1
			+ " ( DRUG_ID integer primary key AUTOINCREMENT,BRAND_NAME varchar,COMPANY varchar,MRP real, CATEGORY varchar,DESCRIPTION varchar)";
	private static final String TABLE_QUERY2 = "create table if not exists "
			+ TABLE_NAME2
			+ " ( COMP_ID integer primary key AUTOINCREMENT,COMPOSITION varchar,PARENT_ID int)";
	private static final String TABLE_QUERY3 = "create table if not exists "
			+ TABLE_NAME3
			+ "(ID integer primary key AUTOINCREMENT,CATEGORY varchar)";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(TABLE_QUERY1);
		db.execSQL(TABLE_QUERY2);
		db.execSQL(TABLE_QUERY3);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		if (oldVersion < newVersion) {
			db.execSQL("drop table if exists " + TABLE_NAME1);
			db.execSQL("drop table if exists " + TABLE_NAME2);
			db.execSQL("drop table if exists " + TABLE_NAME3);

			onCreate(db);
		}

	}

	public int insertData(ContentValues values, boolean isTable1) {
		SQLiteDatabase db = getWritableDatabase();
		long ll;
		if (isTable1) {
			ll = db.insert(TABLE_NAME1, null, values);
		} else {
			ll = db.insert(TABLE_NAME2, null, values);
		}
		return (int) ll;

	}

	public int insertTable3(ContentValues values) {
		SQLiteDatabase db = getWritableDatabase();
		long ll;

		ll = db.insert(TABLE_NAME3, null, values);

		return (int) ll;

	}

	public int delete(String values, boolean isTable1) {
		SQLiteDatabase db = getWritableDatabase();
		long ll;
		if (isTable1) {
			ll = db.delete(TABLE_NAME1, "BRAND_ID =? ", new String[] { values });
		} else {
			ll = db.delete(TABLE_NAME2, "PARENT_ID =? ",
					new String[] { values });
		}
		return (int) ll;
	}

	public Cursor getData(String query) // cursor return data in tabular form
										// function to take data from database
	{
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		return c;
	}

	public int update(ContentValues values, String id, boolean isTable1) {
		SQLiteDatabase db = getWritableDatabase();
		long ll;
		if (isTable1) {
			ll = db.update(TABLE_NAME1, values, "BRAND_ID =?",
					new String[] { id });
		} else {
			ll = db.update(TABLE_NAME2, values, "PARENT_ID =?",
					new String[] { id });
		}
		return (int) ll;
	}

	public int update1(ContentValues values, String id) {
		SQLiteDatabase db = getWritableDatabase();
		long ll;

		ll = db.update(TABLE_NAME3, values, "BRAND_ID =?", new String[] { id });

		return (int) ll;
	}
}
