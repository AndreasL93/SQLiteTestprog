package com.javapapers.android.sqlitestorageoption;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.javapapers.android.sqlitestorageoption.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;


public class NewAnimal extends Activity{
	EditText animalName;
	DBController controller = new DBController(this);
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.add_new_animal);
	        animalName = (EditText) findViewById(R.id.animalName);
	    }
	public void addNewAnimal(View view) {
		HashMap<String, String> queryValues =  new  HashMap<String, String>();
		queryValues.put("animalName", animalName.getText().toString());
		controller.insertAnimal(queryValues);
		this.callHomeActivity(view);


	}
	public void callHomeActivity(View view) {
		Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(objIntent);
	}	
}
