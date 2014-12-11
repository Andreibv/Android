package com.example.gestiodonto;

import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Nuevo_Tratamiento extends Activity {
	 ListAdapter adaptador;
	 Spinner spinner;
	 BD bd=new BD(this);
	 String selected_val;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nuevo__tratamiento);
		
		final EditText nombre_tra = (EditText) findViewById(R.id.nombre_tra_crear);
		
		spinner = (Spinner) findViewById(R.id.spinner_Tratamient);
		adaptador = new ArrayAdapter<Catedras>(
				 this, android.R.layout.simple_spinner_dropdown_item, bd.getAllCatedras());
		spinner.setAdapter((SpinnerAdapter) adaptador);
		
		spinner.setOnItemSelectedListener( new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				selected_val= spinner.getSelectedItem().toString();
			
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
	
	
        

		  Button nuevo_Tra = (Button) findViewById(R.id.crear_tratamiento_btn);
		     nuevo_Tra.setOnClickListener(new View.OnClickListener() {
		    		
		    		
					@Override
					public void onClick(View v) {
						Random rnd = new Random();
						
						CharSequence nom = nombre_tra.getText();
						
						bd.addTratamiento(rnd.nextInt(), nom.toString(), selected_val);
						//bd.addCatedra(rnd.nextInt(),nom.toString());
						Intent intent = new Intent(getBaseContext(),Tratamiento.class);
			        	startActivity(intent);
									
					}
				});
			     
			     Button cancel_Cat = (Button) findViewById(R.id.cancel_new_tratamiento);
			     cancel_Cat.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(v.getContext(),Tratamiento.class);
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
