package com.example.tengounevento;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
 
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Nuevo_Evento extends ActionBarActivity {
	BD bd = new BD(this);
	AlertDialog.Builder dialogo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nuevo__evento);
		
		
		
		final EditText nombre_even = (EditText) findViewById(R.id.nombre_cat_crear);
		
	    Button nueva_Cat = (Button) findViewById(R.id.crear_evento_btn);
	    nueva_Cat.setOnClickListener(new View.OnClickListener() {
	    	
			@Override
			public void onClick(View v) {
				CharSequence nom = nombre_even.getText();
				String forvalidar=nom.toString();
				int longitud = nom.length(); 
				
			 if(forvalidar.equals(""))
				{
					Toast.makeText(getApplicationContext(), "Alerta\nNO valores vacios", Toast.LENGTH_SHORT).show();
					
				}
			 else if (longitud <= 3)
				{
					
					Toast.makeText(getApplicationContext(), "Alerta\nMinimo 3 letras", Toast.LENGTH_SHORT).show();
					
				}else
				{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date(); 
				Random rnd = new Random();
				bd.addEvento(rnd.nextInt(), nom.toString(), dateFormat.format(date).toString()); 
				Intent intent = new Intent(getBaseContext(),Evento.class);
	        	startActivity(intent);
				}
				
			}
		});
	     
	     Button cancel_Cat = (Button) findViewById(R.id.cancel_new_evento);
	     cancel_Cat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),Evento.class);
				 startActivity(intent);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
