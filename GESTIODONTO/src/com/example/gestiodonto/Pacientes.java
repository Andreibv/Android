package com.example.gestiodonto;



import java.util.List;

 


import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Pacientes extends Activity {
	private String cedula;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String fechanac;
    private int expediente;
    private String telefono;
    private String direccion;
    BD bd = new BD(this); 
    ListAdapter adaptador;
    ListView lv;
    public Pacientes(){}
    
    public Pacientes(String cedula, String nombre,String apellido1,String apellido2,String fechanac,int expediente,String telefono,String direccion) {
        super();
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechanac = fechanac;
        this.expediente = expediente;
        this.telefono = telefono;
        this.direccion = direccion;
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pacientes);
		
		bd.addPaciente("434434", "Paciente", "de Prueba", "2°Apellido", "01/01/2014", 001, "506 88 88 88 88", "Santa Cruz");
		
		adaptador = new ArrayAdapter<Pacientes>(
				 this, android.R.layout.simple_list_item_1, bd.getAllPacientes());
	        //Asociamos el adaptador a la vista.
	    lv = (ListView) findViewById(R.id.listPacientes);
	    lv.setAdapter(adaptador);
	     // get all books
	   //  List<Pacientes> list =  bd.getAllPacientes();
	     registerForContextMenu(lv);
	     
	     Button button = (Button) findViewById(R.id.crear_paciente);
	     button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),Nuevo_paciente.class);
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
	public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuinfo)
	{
		 super.onCreateContextMenu(menu, v, menuinfo);
		 AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuinfo;
		 
		 if(v == lv){
			 menu.setHeaderTitle( adaptador.getItem(info.position).toString() );  
			 MenuInflater inflater = getMenuInflater();
			 inflater.inflate(R.menu.context, menu);
		 }
	  
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		final Pacientes pacientes = bd.getAllPacientes().get(info.position);
		super.onContextItemSelected(item);
		
		Context context = getApplicationContext();
		
		switch(item.getItemId())
		{
		case R.id.ver:		
			//---------------------------------------------------------------------------------------------------
			AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
			dialogo.setTitle("Información del Paciente");
			dialogo.setMessage(
					"Identificación:"+pacientes.getCedula()
					+"\nNombre "+pacientes.getNombre()
					+"\nApellidos: "+pacientes.getApellido1()+" "+pacientes.getApellido2()
					+"\nFecha Nac: "+pacientes.fechanac
					+"\nExpediente: "+pacientes.expediente
					+"\nTelefono: "+pacientes.telefono
					+"\nDirección: "+pacientes.direccion
					);
			dialogo.setCancelable(false);
			dialogo.setNegativeButton("Visto", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			dialogo.show();
			break;
			//---------------------------------------------------------------------------------------------------
		case R.id.editar:
			//---------------------------------------------------------------------------------------------------
			/*Toast toast1 = Toast.makeText(
					context,
					"Menu Editar",
					Toast.LENGTH_LONG);
			toast1.show();
			*/
			Intent intentEdit = new Intent(context,EditarPaciente.class);
			intentEdit.putExtra("cedula", pacientes.getCedula());
			startActivity(intentEdit);
			
			break;
			//---------------------------------------------------------------------------------------------------
		case R.id.eliminar:
			//---------------------------------------------------------------------------------------------------
			AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
				dialogoBorrar.setTitle("Eliminar Paciente");
				dialogoBorrar.setCancelable(false);
				dialogoBorrar.setMessage("¿Desea borrar el paciente?");
			
				dialogoBorrar.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
			         public void onClick(DialogInterface dialog, int boton) {
			            bd.deletePaciente(pacientes.getCedula());
			        	
			            //Toast.makeText(HipotecaFormulario.this, R.string.hipoteca_eliminar_confirmacion, Toast.LENGTH_SHORT).show();
			            /**
			             * Devolvemos el control
			             */
			            setResult(RESULT_OK);
			            finish();
			            Intent intent= new Intent(getBaseContext(),Pacientes.class);			          
						startActivity(intent);
						overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
			            
			         }
			      });
			       
				dialogoBorrar.setNegativeButton(android.R.string.no, null);
				dialogoBorrar.show();
				//adaptador
				
			break;
			//---------------------------------------------------------------------------------------------------
		}
		return super.onContextItemSelected(item);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
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
	
	 @Override
	  public String toString() {
	        return  nombre +" "+apellido1;
	    }
	    
	    /*Set and Get 's*/
	    public String getCedula() {
			return cedula;
		}

		public void setCedula(String cedula) {
			this.cedula = cedula;
		}
		
		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		public String getApellido1() {
			return apellido1;
		}

		public void setApellido1(String apellido1) {
			this.apellido1 = apellido1;
		}
		
		public String getApellido2() {
			return apellido2;
		}

		public void setApellido2(String apellido2) {
			this.apellido2 = apellido2;
		}
		
		public String getFechanac() {
			return fechanac;
		}

		public void setFechanac(String fechanac) {
			this.fechanac = fechanac;
		}
		
		public int getExpediente() {
			return expediente;
		}

		public void setExpediente(int expediente) {
			this.expediente = expediente;
		}
		
		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}
		
		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}
		
}
