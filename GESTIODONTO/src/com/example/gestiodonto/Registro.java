package com.example.gestiodonto;

import android.support.v7.app.ActionBarActivity;
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

public class Registro extends ActionBarActivity {
	int codreg;
	String paciente,turno,tratamiento,fecha;
	BD bd = new BD(this); 
    ListAdapter adaptador;
    ListView lv;
    
    public Registro(){}
    
    public Registro(int codreg,String paciente,String turno,String tratamiento,String fecha)
    {
    	super();
    	this.codreg=codreg;
    	this.paciente=paciente;
    	this.turno=turno;
    	this.tratamiento=tratamiento;
    	this.fecha=fecha;
    }
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registro);
		
		bd.addRegistro(1, "Paciente de Prueba", "Miercoles de 7:00 a 10:30am", "Calzas", "2014-11-13 06:51:02");
		
		adaptador = new ArrayAdapter<Registro>(
				 this, android.R.layout.simple_list_item_1, bd.getAllRegistros());
	        //Asociamos el adaptador a la vista.
	    lv = (ListView) findViewById(R.id.listRegistro);
	    lv.setAdapter(adaptador);
	    registerForContextMenu(lv);
	    
	    Button button = (Button) findViewById(R.id.crear_registro);
	     button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),Nuevo_Registro.class);
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
		public boolean onContextItemSelected(MenuItem item) {
			
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
			final Registro registro = bd.getAllRegistros().get(info.position);
			super.onContextItemSelected(item);
			
			Context context = getApplicationContext();
			
			switch(item.getItemId())
			{
			case R.id.ver:
				AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
				dialogo.setTitle("Información del Paciente");
				dialogo.setMessage(
						"Paciente: "+registro.getRegPaciente()+"\n"+
						"Atendido: "+registro.getRegFecha()+"\n"+
						"Se le aplicó: "+"\n"+registro.getRegTratamiento()+"\n"					
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
				dialogoBorrar.setTitle("Eliminar el Registro");
				dialogoBorrar.setCancelable(false);
				dialogoBorrar.setMessage("¿Desea borrar el registro?");
			
				dialogoBorrar.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
			         public void onClick(DialogInterface dialog, int boton) {
			        	bd.deleteRegistro(registro.getCodRegistro());
			           
			            setResult(RESULT_OK);
			            finish();
			            Intent intent= new Intent(getBaseContext(),Registro.class);			          
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
	        return  paciente+" "+fecha.substring(0, 10);
	    }
	 
	  public int getCodRegistro() {
			return codreg;
		}

	  public void setCodRegistro(int codreg) {
			this.codreg = codreg;
		}
	  
	  public String getRegPaciente() {
			return paciente;
		}

		public void setRegPaciente(String paciente) {
			this.paciente = paciente;
		}

	  public String getRegTurno() {
			return turno;
			}

	  public void setRegTurno(String turno) {
			this.turno = turno;
			}
	  
	  public String getRegTratamiento() {
			return tratamiento;
			}

	  public void setRegTratamiento(String tratamiento) {
			this.tratamiento = tratamiento;
			}
	  
	  public String getRegFecha() {
			return fecha;
			}

	  public void setRegFecha(String fecha) {
			this.fecha = fecha;
			}
}
