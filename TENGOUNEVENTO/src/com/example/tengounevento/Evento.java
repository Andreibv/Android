package com.example.tengounevento;
 
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class Evento extends ActionBarActivity {
	
    private String codigo_evento,nombre_evento,fecha_evento; 
    BD bd = new BD(this);
    ListAdapter adaptador;
    ListView lv;
    public Evento(){}
    
    public Evento(String codigo_evento,String nombre_evento){
    	super();
    	
    	this.codigo_evento=codigo_evento;
    	this.nombre_evento=nombre_evento;
    }
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evento);
		
		bd.addEvento(1, "Evento de Prueba", "2014-11-15 17:30:00");
		 
		
		adaptador = new ArrayAdapter<Evento>(
				this, android.R.layout.simple_list_item_1, bd.getAllEventos());

	       lv = (ListView) findViewById(R.id.listEventos);
	       lv.setAdapter(adaptador);
	     
	       registerForContextMenu(lv);
	       
	       Button new_cat = (Button) findViewById(R.id.newEvento);
		     new_cat.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(v.getContext(),Nuevo_Evento.class);
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
		 Intent intent;
		switch(id)
		{
		case R.id.action_settings:
			Toast.makeText(getApplicationContext(), "Yes iam, Andrei BV student", Toast.LENGTH_SHORT).show(); 
			return super.onOptionsItemSelected(item);	
		case R.id.salir:
			Toast.makeText(getApplicationContext(), "Chao", Toast.LENGTH_LONG).show(); 
			Intent homeIntent = new Intent(Intent.ACTION_MAIN);
		    homeIntent.addCategory( Intent.CATEGORY_HOME );
		    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
		    startActivity(homeIntent); 
			return super.onOptionsItemSelected(item);
			}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuinfo)
	{
		 super.onCreateContextMenu(menu, v, menuinfo);
		 AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuinfo;
		 
			 menu.setHeaderTitle( adaptador.getItem(info.position).toString() );  
			 MenuInflater inflater = getMenuInflater();
			 inflater.inflate(R.menu.context, menu);  
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		final Evento eventos = bd.getAllEventos().get(info.position);
		super.onContextItemSelected(item);
		
		Context context = getApplicationContext();
		
		switch(item.getItemId())
		{
		case R.id.ver:
			Intent intentEdit = new Intent(context,Producto.class);
			intentEdit.putExtra("codigo_evento", eventos.getCodEven());
			startActivity(intentEdit);
			
			break;
			
		case R.id.eliminar:
			AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
			dialogoBorrar.setTitle("Eliminar Evento");
			dialogoBorrar.setCancelable(false);
			dialogoBorrar.setMessage("¿Desea borrar el evento?");
		
			dialogoBorrar.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
		         public void onClick(DialogInterface dialog, int boton) {
		        	bd.deleteEvento(eventos.getCodEven());
		           
		            setResult(RESULT_OK);
		            finish();
		            Intent intent= new Intent(getBaseContext(),Evento.class);			          
					startActivity(intent);
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
	        return  nombre_evento;
	    }
	 
	  public String getCodEven() {
			return codigo_evento;
		}

	  public void setCodEven(String codigo_evento) {
			this.codigo_evento = codigo_evento;
		}
	  
	  public String getNomEven() {
			return nombre_evento;
		}

		public void setNomEven(String nombre_evento) {
			this.nombre_evento = nombre_evento;
		}
		
		public String getFecEven() {
			return fecha_evento;
		}

		public void setFecEven(String fecha_evento) {
			this.fecha_evento = fecha_evento;
		}
		
		
}
