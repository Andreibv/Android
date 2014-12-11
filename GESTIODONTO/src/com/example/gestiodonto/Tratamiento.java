package com.example.gestiodonto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class Tratamiento extends Activity {
	BD bd = new BD(this);
	private String codtra;
    private String nombretra; 
    private String nombrecattra; 
    ListAdapter adaptador;
    ListView lv;
    
    public Tratamiento(){}
    
    public Tratamiento(String codtra, String nombretra,String nombrecattra)
    {
    	super();
    	this.codtra=codtra;
    	this.nombretra=nombretra;
    	this.nombrecattra=nombrecattra;
    	
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tratamiento);
		
		bd.addTratamiento(1, "Tratamiento de Prueba", "Endodoncia");
		
		adaptador = new ArrayAdapter<Tratamiento>(
				 this, android.R.layout.simple_list_item_1, bd.getAllTratamientos());
	        //Asociamos el adaptador a la vista.
		  lv = (ListView) findViewById(R.id.listTratamiento);
	      lv.setAdapter(adaptador);
	    
	     registerForContextMenu(lv);
	    
	     Button button = (Button) findViewById(R.id.crear_tratamiento_b);
	     button.setOnClickListener(new View.OnClickListener() {
	    	 Intent intent;
			@Override
			public void onClick(View v) {
				 intent = new Intent(v.getContext(),Nuevo_Tratamiento.class);
				 startActivity(intent);
				
			}
		});
	     
		  
	}

	
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuinfo)
	{
		 super.onCreateContextMenu(menu, v, menuinfo);
		 AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuinfo;
		 
		 if(v == lv){
			 menu.setHeaderTitle( adaptador.getItem(info.position).toString() );  
			 MenuInflater inflater = getMenuInflater();
			 inflater.inflate(R.menu.contextcat, menu);
		 }
	  
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
	
	

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		final Tratamiento tratamiento = bd.getAllTratamientos().get(info.position);
		super.onContextItemSelected(item);
		
		Context context = getApplicationContext();
		
		switch(item.getItemId())
		{
		case R.id.ver:		
			//---------------------------------------------------------------------------------------------------
			AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
			dialogo.setTitle("Información del Tratamiento");
			dialogo.setMessage(
					"Pertenece a: "+tratamiento.getNombreCatedraTratamiento()
					+"\nNombre: "+tratamiento.getNomTratamiento()
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
	
		case R.id.eliminar:
			//---------------------------------------------------------------------------------------------------
			AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
				dialogoBorrar.setTitle("Eliminar Tratamiento");
				dialogoBorrar.setCancelable(false);
				dialogoBorrar.setMessage("¿Desea borrar el tratamiento?");
			
				dialogoBorrar.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
			         public void onClick(DialogInterface dialog, int boton) {
			           
			        	bd.deleteTratamiento(tratamiento.getCodTratamiento());
			            //Toast.makeText(HipotecaFormulario.this, R.string.hipoteca_eliminar_confirmacion, Toast.LENGTH_SHORT).show();
			            /**
			             * Devolvemos el control
			             */
			            setResult(RESULT_OK);
			            finish();
			            Intent intent= new Intent(getBaseContext(),Tratamiento.class);			          
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
	  public String toString() {
	        return  nombretra;
	    }
	 
	  public String getCodTratamiento() {
			return codtra;
		}

	  public void setCodTratamiento(String codtra) {
			this.codtra = codtra;
		}
	  
	  public String getNomTratamiento() {
			return nombretra;
		}

		public void setNomTratamiento(String nomtra) {
			this.nombretra = nomtra;
		}
		
		 public String getNombreCatedraTratamiento() {
			return nombrecattra;
		}

	  public void setNombreCatedraTratamiento(String nombrecattra) {
			this.nombrecattra = nombrecattra;
		}
}
