package com.example.gestiodonto;

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
import android.widget.AdapterView.AdapterContextMenuInfo;

public class Catedras extends Activity {
	private String codcat;
    private String nombrecat; 
    BD bd = new BD(this);
    ListAdapter adaptador;
    ListView lv;
    public Catedras(){}
    
    public Catedras(String codcat,String nomcat){
    	super();
    	this.codcat=codcat;
    	this.nombrecat=nomcat;
    }
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catedras);
		
		bd.addCatedra(1,"Catedra de Prueba");
		
		adaptador = new ArrayAdapter<Catedras>(
				this, android.R.layout.simple_list_item_1, bd.getAllCatedras());
	        //Asociamos el adaptador a la vista.
	       lv = (ListView) findViewById(R.id.listCatedras);
	       lv.setAdapter(adaptador);
	     // get all books
	   //  List<Pacientes> list =  bd.getAllPacientes();
	     registerForContextMenu(lv);
	     
	     Button new_cat = (Button) findViewById(R.id.crear_catedra);
	     new_cat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),Nueva_Catedra.class);
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

	
	public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuinfo)
	{
		 super.onCreateContextMenu(menu, v, menuinfo);
		 AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuinfo;
		 
			 menu.setHeaderTitle( adaptador.getItem(info.position).toString() );  
			 MenuInflater inflater = getMenuInflater();
			 inflater.inflate(R.menu.contextcat, menu);
		
		  
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
		final Catedras catedras = bd.getAllCatedras().get(info.position);
		super.onContextItemSelected(item);
		
		Context context = getApplicationContext();
		
		switch(item.getItemId())
		{
		case R.id.ver:
			AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
			dialogo.setTitle("Información del Paciente");
			dialogo.setMessage(
					"Nombre: "+catedras.getNomCatedra()
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
			
		case R.id.eliminar:
			AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
			dialogoBorrar.setTitle("Eliminar Catedra");
			dialogoBorrar.setCancelable(false);
			dialogoBorrar.setMessage("¿Desea borrar la catedra?");
		
			dialogoBorrar.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
		         public void onClick(DialogInterface dialog, int boton) {
		        	bd.deleteCatedra(catedras.getCodCatedra());
		           
		            setResult(RESULT_OK);
		            finish();
		            Intent intent= new Intent(getBaseContext(),Catedras.class);			          
					startActivity(intent);
					overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
		            
		         }
		      });
		       
			dialogoBorrar.setNegativeButton(android.R.string.no, null);
			dialogoBorrar.show();
			break;
			
		
		}
		return true;
		}
	
	 @Override
	  public String toString() {
	        return  nombrecat;
	    }
	 
	  public String getCodCatedra() {
			return codcat;
		}

	  public void setCodCatedra(String codcat) {
			this.codcat = codcat;
		}
	  
	  public String getNomCatedra() {
			return nombrecat;
		}

		public void setNomCatedra(String nomcat) {
			this.nombrecat = nomcat;
		}
	  
		
		
}
