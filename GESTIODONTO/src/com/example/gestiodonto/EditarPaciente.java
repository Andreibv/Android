package com.example.gestiodonto;



import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarPaciente extends ActionBarActivity {
	BD bd = new BD(this);
	Pacientes pacientes = new Pacientes();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_paciente);
		

			Bundle bundle = getIntent().getExtras();
		final String ced = bundle.getString("cedula");
		String nom =  bd.getPaciente(ced).getNombre().toString();
		String ap1 =  bd.getPaciente(ced).getApellido1().toString();
		String ap2 =  bd.getPaciente(ced).getApellido2().toString();
		String tel =  bd.getPaciente(ced).getTelefono().toString();
		String fn =  bd.getPaciente(ced).getFechanac().toString();
		int exp =  bd.getPaciente(ced).getExpediente();
		String dir =  bd.getPaciente(ced).getDireccion() .toString();
		String ex = Integer.toString(exp);
		
		
		EditText campo_cedula = (EditText) findViewById(R.id.cedula_editar);
		EditText campo_nombre = (EditText) findViewById(R.id.nombre_editar);
		EditText campo_apellido1 = (EditText) findViewById(R.id.apellido1_editar);
		EditText campo_apellido2 = (EditText) findViewById(R.id.apellido2_editar);
		EditText campo_telefono = (EditText) findViewById(R.id.telefono_editar);
		EditText campo_fechanac = (EditText) findViewById(R.id.fechanac_editar);
		EditText campo_expediente = (EditText) findViewById(R.id.expediente_editar);
		EditText campo_direccion = (EditText) findViewById(R.id.direccion_editar);
		
		campo_cedula.setEnabled(false);
		campo_cedula.setText(ced);
		campo_nombre.setText(nom);
		campo_apellido1.setText(ap1);
		campo_apellido2.setText(ap2);
		campo_telefono.setText(tel);
		campo_fechanac.setText(fn);
		campo_expediente.setText(ex);
		campo_direccion.setText(dir);
		

		Button cancelaOpcion = (Button) findViewById(R.id.cancel_editar_paciente);
		cancelaOpcion.setOnClickListener(new OnClickListener(){
			 public void onClick(View v) {
				 Intent intent = new Intent(getBaseContext(),Pacientes.class);
	        	   startActivity(intent);
			 }
		});
			
		Button editarPaciente = (Button) findViewById(R.id.editar_Paciente_btn);
		editarPaciente.setOnClickListener(new OnClickListener(){
			 public void onClick(View v) {
				 EditText campo_cedula = (EditText) findViewById(R.id.cedula_editar);
					EditText campo_nombre = (EditText) findViewById(R.id.nombre_editar);
					EditText campo_apellido1 = (EditText) findViewById(R.id.apellido1_editar);
					EditText campo_apellido2 = (EditText) findViewById(R.id.apellido2_editar);
					EditText campo_telefono = (EditText) findViewById(R.id.telefono_editar);
					EditText campo_fechanac = (EditText) findViewById(R.id.fechanac_editar);
					EditText campo_expediente = (EditText) findViewById(R.id.expediente_editar);
					EditText campo_direccion = (EditText) findViewById(R.id.direccion_editar);
					
				 String ced_new = campo_cedula.getText().toString();
				 String nombre_new = campo_nombre.getText().toString();
				 String ap1_new = campo_apellido1.getText().toString();
				 String ap2_new = campo_apellido2.getText().toString();
				 String telefono_new = campo_telefono.getText().toString();
				 String fechanac_new = campo_fechanac.getText().toString();
				 String expediente_new = campo_expediente.getText().toString();
				 String direccion_new = campo_direccion.getText().toString();
				 Integer expediente = Integer.parseInt(expediente_new);
				 
				 bd.updatePaciente(ced_new, nombre_new, ap1_new, ap2_new, fechanac_new, expediente, telefono_new, direccion_new);
				 Toast.makeText(getApplicationContext(), "Actualizando", Toast.LENGTH_SHORT).show();
				 Intent intent = new Intent(getBaseContext(),Pacientes.class);
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
