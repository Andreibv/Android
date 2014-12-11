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
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class Turno extends ActionBarActivity {

		private int codtur;
		private String dia;
		private String horaI;
		private String horaF;
		
		BD bd = new BD(this); 
	    ListAdapter adaptador;
	    ListView lv;
	    
	    public Turno(){}
	    
	    public Turno(Integer codtur,String dia,String horaI, String horaF){
	    	super();
	    	this.codtur=codtur;
	    	this.dia=dia;
	    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_turno);
		
		
		bd.addTurno(89,"Miercoles", "7:30am", "11:30am");
		
		adaptador = new ArrayAdapter<Turno>(
				 this, android.R.layout.simple_list_item_1, bd.getAllTurnos());
	        //Asociamos el adaptador a la vista.
	    lv = (ListView) findViewById(R.id.listTurno);
	    lv.setAdapter(adaptador);
	    registerForContextMenu(lv);
	    
	    
	    
	    Button button = (Button) findViewById(R.id.crear_turno_b);
	     button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),Nuevo_Turno.class);
				 startActivity(intent);
				//Toast.makeText(getApplicationContext(), "Crear turno ya casi", Toast.LENGTH_SHORT).show();
				
			}
		});
	}

	private void setRegisterForContext(ListView lv2) {
		// TODO Auto-generated method stub
		
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
			 inflater.inflate(R.menu.contexttur, menu);
		 }
	  
	}
	 

		@Override
		public boolean onContextItemSelected(MenuItem item) {
			
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
			final Turno turno = bd.getAllTurnos().get(info.position);
			super.onContextItemSelected(item);
			
			Context context = getApplicationContext();
			
			switch(item.getItemId())
			{
			case R.id.eliminar:
				//---------------------------------------------------------------------------------------------------
				AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
					dialogoBorrar.setTitle("Eliminar Turno");
					dialogoBorrar.setCancelable(false);
					dialogoBorrar.setMessage("¿Desea borrar el turno?");
				
					dialogoBorrar.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
				         public void onClick(DialogInterface dialog, int boton) {
				            
				        	bd.deleteTurno(turno.getCodTurno());
				          
				            setResult(RESULT_OK);
				            finish();
				            Intent intent= new Intent(getBaseContext(),Turno.class);			          
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
	        return  dia+" de "+horaI+" hasta "+horaF;
	    }
	 
	  public int getCodTurno() {
			return codtur;
		}

	  public void setCodTurno(int codtur) {
			this.codtur = codtur;
		}
	  
	  public String getDiaTurno() {
			return dia;
		}

		public void setDiaTurno(String dia) {
			this.dia = dia;
		}

	  public String getHoraITurno() {
			return horaI;
			}

	  public void setHoraITurno(String horaI) {
			this.horaI = horaI;
			}
	  
	  public String getHoraFTurno() {
			return horaI;
			}

	  public void setHoraFTurno(String horaF) {
			this.horaF = horaF;
			}
			
			
}
