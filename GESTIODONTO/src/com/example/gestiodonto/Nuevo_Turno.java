package com.example.gestiodonto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TimePicker;

public class Nuevo_Turno extends ActionBarActivity {
	BD bd = new BD(this);
	String selected_val;
	Spinner spinner,turnodesde_h,turnodesde_m,turnohasta_h,turnohasta_m;
	String hIS,mIS,hFS,mFS;
	//int horaI,minI,horaF,minF;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nuevo__turno);
		
			 spinner = (Spinner) findViewById(R.id.spinner_dia);
			 turnodesde_h = (Spinner) findViewById(R.id.turnodesde_h);
			 turnodesde_m = (Spinner) findViewById(R.id.turnodesde_m);
			 turnohasta_h = (Spinner)findViewById(R.id.turnohasta_h);
			 turnohasta_m = (Spinner)findViewById(R.id.turnohasta_m);
			 
	        List<String> list = new ArrayList<String>();
	        list.add("Lunes");
	        list.add("Martes");
	        list.add("Miercoles");
	        list.add("Jueves");
	        list.add("Viernes");
	        list.add("Sabado");
	        
	        
	        List<String> list_h = new ArrayList<String>();
	        list_h.add("7");
	        list_h.add("8");
	        list_h.add("9");
	        list_h.add("10");
	        list_h.add("11");
	        list_h.add("12");
	        list_h.add("13");
	        list_h.add("14");
	        list_h.add("15");
	        list_h.add("16");
	        list_h.add("17");
	        list_h.add("18");
	        list_h.add("19");
	        list_h.add("20");
	        
	        
	        List<String> list_m = new ArrayList<String>();
	        list_m.add("00");
	        list_m.add("15");
	        list_m.add("30");
	        list_m.add("45");
	       
	        
	  
	        
	        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
					 this, android.R.layout.simple_spinner_dropdown_item, list);
	        
	        
	        ArrayAdapter<String> adaptador_h = new ArrayAdapter<String>(
					 this, android.R.layout.simple_spinner_dropdown_item, list_h);
	        
	        
	        ArrayAdapter<String> adaptador_m = new ArrayAdapter<String>(
					 this, android.R.layout.simple_spinner_dropdown_item, list_m);
			spinner.setAdapter((SpinnerAdapter) adaptador);
			turnodesde_h.setAdapter((SpinnerAdapter) adaptador_h);
			turnodesde_m.setAdapter((SpinnerAdapter) adaptador_m);
			turnohasta_h.setAdapter((SpinnerAdapter) adaptador_h);
			turnohasta_m.setAdapter((SpinnerAdapter) adaptador_m);
			 
			 
		
				
				
	
			 //Spinner dia
			  
		spinner.setOnItemSelectedListener( new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) { 
				selected_val= spinner.getSelectedItem().toString();
			
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				//  
				
			}
			
		});
		
		//Spinner hora Inicio
		
		turnodesde_h.setOnItemSelectedListener( new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) { 
				hIS= turnodesde_h.getSelectedItem().toString();
			
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		//minuto de inicio
		turnodesde_m.setOnItemSelectedListener( new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				//  
				mIS= turnodesde_m.getSelectedItem().toString();
			
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				//  
				
			}
			
		});
		
		//Hora final
		
		turnohasta_h.setOnItemSelectedListener( new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				//  
				hFS= turnohasta_h.getSelectedItem().toString();
			
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				//  
				
			}
			
		});
		
		//min final
		
		turnohasta_m.setOnItemSelectedListener( new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				//  
				mFS= turnohasta_m.getSelectedItem().toString();
			
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				//  
				
			}
			
		});
		
		
		  Button nuevo_tur = (Button) findViewById(R.id.crear_turno_btn);
		  nuevo_tur.setOnClickListener(new View.OnClickListener() {
		    		
		    		
					@Override
					public void onClick(View v) {
						
						//Toast.makeText(getApplicationContext(),hIS+":"+mIS,Toast.LENGTH_LONG).show();
						Random rnd = new Random();
						
						bd.addTurno(rnd.nextInt(), selected_val, hIS+":"+mIS, hFS+":"+mFS);
						
						//bd.addTratamiento(rnd.nextInt(), nom.toString(), selected_val);
						//bd.addCatedra(rnd.nextInt(),nom.toString());
						Intent intent = new Intent(getBaseContext(),Turno.class);
			        	startActivity(intent);
									
					}
				});
			     
			     Button cancel_Tur = (Button) findViewById(R.id.cancel_new_turno);
			     cancel_Tur.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(v.getContext(),Turno.class);
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
