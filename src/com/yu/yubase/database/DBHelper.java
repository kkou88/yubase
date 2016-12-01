package com.yu.yubase.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.yu.yubase.constant.DB;

public class DBHelper extends SQLiteOpenHelper {
	public static DBHelper mDBHelper;
	
	public static synchronized DBHelper getInstance(Context context) {
		if(mDBHelper == null) {
			if(context == null) {
				return null;
			} else {
				mDBHelper = new DBHelper(context);
			}
		}
		return mDBHelper;
	}
	
	private DBHelper(Context context) {
		super(context, DB.DB_NAME, null, DB.VERSION);
		mDBHelper = this;
	}
	private DBHelper(Context context, String name) {
		super(context, name, null, DB.VERSION);
	}
	
	private DBHelper(Context context, String name, CursorFactory factory) {
		super(context, name, factory, DB.VERSION);
	}
	
	private DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.beginTransaction();
		
		//创建用户数据表,并插入数据
		db.execSQL(DB.SQL_CREATE_TB_USER);
		//创建银行卡数据表
		db.execSQL(DB.SQL_CREATE_TB_BANKCARD);
		
		db.setTransactionSuccessful();
		db.endTransaction();
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}
	
	public boolean insert(int tableName, ContentValues values) {
		SQLiteDatabase db = getReadableDatabase();
		db.beginTransaction();
		switch(tableName){
			case DB.ID_USER:
				db.insert(DB.TB_USER, null, values);
				break;
			case DB.ID_BANKCARD:
				db.insert(DB.TB_BANKCARD, null, values);
				break;
		}
		db.setTransactionSuccessful();
		db.endTransaction();
		return true;
	}
	
	public boolean delete(int tableName, String selection, String[] selectionArgs) {
		SQLiteDatabase db = getReadableDatabase();
		db.beginTransaction();
		switch(tableName){
			case DB.ID_USER:
				db.delete(DB.TB_USER, selection, selectionArgs);
				break;
			case DB.ID_BANKCARD:
				db.delete(DB.TB_BANKCARD, selection, selectionArgs);
				break;
		}
		db.setTransactionSuccessful();
		db.endTransaction();
		return true;
	}

	public int update(int tableName, ContentValues values, String selection,String[] selectionArgs) {
		SQLiteDatabase db = getReadableDatabase();
		db.beginTransaction();
		int temp= 0;
		switch(tableName){
			case DB.ID_USER:
				temp = db.update(DB.TB_USER, values, selection, selectionArgs);
				break;
			case DB.ID_BANKCARD:
				temp = db.update(DB.TB_BANKCARD, values, selection, selectionArgs);
				break;
		}
		db.setTransactionSuccessful();
		db.endTransaction();
		return temp;
	}
	
	public Cursor query(int tableName, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = getReadableDatabase();
		db.beginTransaction();
		Cursor cursor = null;
		switch(tableName){
			case DB.ID_USER:
				cursor = db.query(DB.TB_USER, projection, selection, selectionArgs, null, null, sortOrder);
				break;
			case DB.ID_BANKCARD:
				cursor = db.query(DB.TB_BANKCARD, projection, selection, selectionArgs, null, null, sortOrder);
				break;
		}
		db.setTransactionSuccessful();
		db.endTransaction();
		return cursor;
	}
	
}
