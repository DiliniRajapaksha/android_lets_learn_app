package com.ll.fruits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DB_SCRIPT_FILE_NAME = "script.sql";
	private static final String DB_NAME = "llfruits";
	private static final int DB_VERSION = 4;
	private static final String LOG_TAG = "Database Helper";
	private Context context;

	/**
	 * @param context
	 */
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			Log.d(LOG_TAG, "creating database");
			String[] queries = getQueries();
			for (String query : queries) {
				db.execSQL(query);
			}
		} catch (IOException e) {
			Log.d(LOG_TAG, e.getMessage());
		}
	}

	private String[] getQueries() throws IOException {
		InputStream in = context.getAssets().open(DB_SCRIPT_FILE_NAME);
		InputStreamReader inReader = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(inReader);
		String line;
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		String[] queries = sb.toString().split(";");
		return queries;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			Log.d(LOG_TAG, "upgrading database");
			String[] queries = getQueries();
			for (String query : queries) {
				db.execSQL(query);
			}
		} catch (IOException e) {
			Log.d(LOG_TAG, e.getMessage());
		}

	}

}
