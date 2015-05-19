package com.javapapers.android.sqlitestorageoption;

import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBController  extends SQLiteOpenHelper {
	private static final String LOGCAT = null;

	//DBController Klasse wird Benutzt um die Datenbank zu Initialisieren und Befehle darauf zu erstellen

	public DBController(Context applicationcontext) {
        super(applicationcontext, "androidsqlite.db", null, 1);
        Log.d(LOGCAT,"Created");

		// erstellt die Datenbank als Datei mit Namen "androidsqlite.db", null parameter beschreibt
		// die fehler behandlung also keine, 1 ist eine Versionsnummer
		// Log.d gibt beim Debug die Nachricht "Created" aus
    }
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		String query;
		query = "CREATE TABLE animals ( animalId INTEGER PRIMARY KEY, animalName TEXT)";
        database.execSQL(query);
        Log.d(LOGCAT,"animals Created");

		// Erstellt die Tabelle Animals in der androidsqlite.db mit animalId als Primärschlüssel und Integer
		// und animalName als Text (String)
		// Befehl wird als String festgelegt und dann mit database.execSQL(String) in der Datenbank ausgeführt

	}
	@Override
	public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
		String query;
		query = "DROP TABLE IF EXISTS animals";
		database.execSQL(query);
        onCreate(database);

		// DROP TABLE falls die Tabelle Animals schon Existiert
	}

	//Eigene insert Methode um Animals der DB hinzuzufügen
	public void insertAnimal(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("animalName", queryValues.get("animalName"));
		database.insert("animals", null, values);
		database.close();
	}
	
	public int updateAnimal(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();	 
	    ContentValues values = new ContentValues();
	    values.put("animalName", queryValues.get("animalName"));
	    return database.update("animals", values, "animalId" + " = ?", new String[] { queryValues.get("animalId") });
	    //String updateQuery = "Update  words set txtWord='"+word+"' where txtWord='"+ oldWord +"'";
	    //Log.d(LOGCAT,updateQuery);
	    //database.rawQuery(updateQuery, null);
	    //return database.update("words", values, "txtWord  = ?", new String[] { word });
	}
	
	public void deleteAnimal(String id) {
		Log.d(LOGCAT,"delete");
		SQLiteDatabase database = this.getWritableDatabase();	 
		String deleteQuery = "DELETE FROM  animals where animalId='"+ id +"'";
		Log.d("query",deleteQuery);		
		database.execSQL(deleteQuery);
	}
	
	public ArrayList<HashMap<String, String>> getAllAnimals() {
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM animals";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	map.put("animalId", cursor.getString(0));
	        	map.put("animalName", cursor.getString(1));
                wordList.add(map);
	        } while (cursor.moveToNext());
	    }
	 
	    // Liefert alle Animals als ArrayList zurück
	    return wordList;
	}
	
	public HashMap<String, String> getAnimalInfo(String id) {
		HashMap<String, String> wordList = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM animals where animalId='"+id+"'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
	        do {

	        	wordList.put("animalName", cursor.getString(1));

	        } while (cursor.moveToNext());
	    }				    
	return wordList;
		// Gibt ein Animal mit der übergeben ID aus, (würde mit Name mehr sinn machen)
	}	
}
