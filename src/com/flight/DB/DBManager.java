package com.flight.DB;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager {
	
	private Context context;
	private static DatabaseHelper DBHelper;
	private static SQLiteDatabase db;
	
	private static DBManager instance = null;
	
	private static final String DATABASE_NAME = "Flight_DB";
	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_CREATE_GPS = 
            "create table Flight_GPS " +
            "(_id INTEGER primary key autoincrement, " +
            "time INTEGER not null, " +
            "longitude  REAL not null, " +
            "latitude   REAL not null";
	
	public static synchronized DBManager getInstance(Context ctx) {
		if (instance == null) {
			instance = new DBManager(ctx);
		}
		return instance;
	}
	
	private DBManager(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE_GPS);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS Flight_GPS");
			onCreate(db);
		}
	}
	
	// ---opens the database---
	public static synchronized void open(Context ctx) throws SQLException {
//		try {
//			if(DBHelper == null){
//				DBHelper = new DatabaseHelper(ctx);
//			}
//			db = DBHelper.getWritableDatabase();
//		} catch (SQLException e) {
//		}
//		return;
	}

	// ---closes the database---
	public static synchronized void close() {
//		Log.i(TAG,"Start close");
//		DBHelper.close();
//		db = null;
//		Log.i(TAG,"End close");
	}

	// ---insert a job into the database---
	public static synchronized long insertTasks(long timestamp,int eventType,String paramValue) {
//		Log.i(TAG,"Start insertTasks");
//		
//		Context context = SharedObjects.getContext();
//		if(!Preference.getEulaAccepted(context)){
//			Log.e(TAG, "Called with EULA not accepted. Discarding...");
//			return -1;
//		}
//		
//		// survey answers have to be always stored since it's user input
//		boolean isSurveyRelated = false;
//		isSurveyRelated = eventType == Utility.EVENT_TYPE_SURVEY_ANSWERS ? true : isSurveyRelated;
//		isSurveyRelated = eventType == Utility.EVENT_TYPE_SURVEY_COMMENTS ? true : isSurveyRelated;
//		
//		if(!Preference.getMonitorEventsFromPreference(context) && !isSurveyRelated){
//			Log.e(TAG, "Called with monitor events disabled. Discarding...");
//			return -1;
//		}
//		
//		if (db == null)
//			open();
//		ContentValues initialValues = new ContentValues();
//		// last update
//		initialValues.put(KEY_LASTUPDATE,timestamp);
//		initialValues.put(KEY_EVENTTYPE, eventType);
//		initialValues.put(KEY_VALUE,paramValue);
//		initialValues.put(KEY_STATUS, Utility.STATUS_DEFAULT);
//		if (db == null)
//			close();
//		Log.i(TAG,"End insertTasks");
//		//MADE: To keep the current id of the database
//		lastId = db.insert(DATABASE_TABLE, null, initialValues);
//		return lastId;
		return 0;
	}

	// ---deletes a particular event using rowId---
	public static synchronized boolean deleteTasks(long rowId) {
//		Log.i(TAG,"Start deleteTasks");
//		open();
//		int result = db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null);
//		close();
//		Log.i(TAG,"End deleteTasks");
//		return result > 0;
		return false;
	}
	
}