package com.example.gestiodonto;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Nuevo_paciente extends Activity {

	
	EditText cedula,nombre,apellido1,apellido2,fechanac,expediente,telefono,direccion;
  

	private Button crearPaciente,cancelaOpcion;
	BD bd = new BD(this);
	Pacientes pacientes= new Pacientes();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nuevo_paciente);
		 
		//mapeamos los botones.
		crearPaciente = (Button) findViewById(R.id.crear_Paciente_btn);
		cancelaOpcion = (Button) findViewById(R.id.cancel_new_paciente);
		
		 
		//obtenemos los valores de los edittext
		cedula = (EditText) findViewById(R.id.cedula_crear);
		nombre = (EditText) findViewById(R.id.nombre_crear);
		apellido1 = (EditText) findViewById(R.id.apellido1_crear);
		apellido2 = (EditText) findViewById(R.id.apellido2_crear);
		fechanac = (EditText) findViewById(R.id.fechanac_crear);
		telefono = (EditText) findViewById(R.id.telefono_crear);
		direccion = (EditText) findViewById(R.id.direccion_crear);
		expediente =(EditText) findViewById(R.id.expediente_crear);
		
		cancelaOpcion.setOnClickListener(new OnClickListener(){
			 public void onClick(View v) {
				 Intent intent = new Intent(getBaseContext(),Pacientes.class);
	        	   startActivity(intent);
			 }
		});
		
		crearPaciente.setOnClickListener(new OnClickListener() {
           @Override
            public void onClick(View v) {
        	   
        	   //obtenemos el texto de los editText
        	   CharSequence ced = cedula.getText();
        	   pacientes.setCedula(ced.toString());
        	   CharSequence nom = nombre.getText();
        	   CharSequence ap1 = apellido1.getText();
        	   CharSequence ap2 = apellido2.getText();
        	   CharSequence fn = fechanac.getText();
        	   CharSequence ex = expediente.getText();
        	   final Integer exp = Integer.parseInt(ex.toString());
        	   CharSequence tel = telefono.getText();
        	   CharSequence dir = direccion.getText();
        	   
        	   //mostramos un mensaje
        	   Toast.makeText(getApplicationContext(), "Hecho: "+ced, Toast.LENGTH_SHORT).show();
        	   
        	   //Llamamos la funcion crear.
        	   bd.addPaciente(ced.toString(),nom.toString(),ap1.toString(),ap2.toString(),fn.toString(),exp,tel.toString(),dir.toString());
        	   Intent intent = new Intent(getBaseContext(),Pacientes.class);
        	   startActivity(intent);
        	   
            }
        });    
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.opciones , menu);
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