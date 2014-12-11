package com.example.gestiodonto;

import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Nueva_Catedra extends ActionBarActivity {
	BD bd = new BD(this);
	AlertDialog.Builder dialogo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nueva__catedra);
		
		
		final EditText nombre_cat = (EditText) findViewById(R.id.nombre_cat_crear);
		
	    Button nueva_Cat = (Button) findViewById(R.id.crear_catedra_btn);
	    nueva_Cat.setOnClickListener(new View.OnClickListener() {
	    	
			@Override
			public void onClick(View v) {
				CharSequence nom = nombre_cat.getText();
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
				Random rnd = new Random();
				bd.addCatedra(rnd.nextInt(),nom.toString());
				Intent intent = new Intent(getBaseContext(),Catedras.class);
	        	startActivity(intent);
				}
				
			}
		});
	     
	     Button cancel_Cat = (Button) findViewById(R.id.cancel_new_catedra);
	     cancel_Cat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),Catedras.class);
				 startActivity(intent);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.opciones, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		 Intent intent;
		switch(id)
		{
		case R.id.pacientes:
			 intent = new Intent(this,Pacientes.class);
			 startActivity(intent);
			//Toast.makeText(getApplicationContext(), "Si sirve", Toast.LENGTH_LONG).show();
			return super.onOptionsItemSelected(item);	
		case R.id.registro:
			 intent = new Intent(this,Registro.class);
			 startActivity(intent);
			return super.onOptionsItemSelected(item);
		case R.id.turno:
			intent = new Intent(this,Turno.class);
			 startActivity(intent);
			return super.onOptionsItemSelected(item);
		case R.id.tratamiento:
			intent = new Intent(this,Tratamiento.class);
			 startActivity(intent);
			return super.onOptionsItemSelected(item);
		case R.id.catedras:
			 intent = new Intent(this,Catedras.class);
			 startActivity(intent);
			return super.onOptionsItemSelected(item);
		}
		return super.onOptionsItemSelected(item);
	}
}
