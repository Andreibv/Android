package com.example.tengounevento;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class Producto extends ActionBarActivity {

    private String nombre,codigo_evento,codigo_producto,total,sum="0"; 
    BD bd = new BD(this);
    ListAdapter adaptador;
    ListView lv;
    public Producto(){}
 
    public Producto(String codigo_producto, String codigo_evento, String nombre, String total){
    	super();
    	this.codigo_producto=codigo_producto;
    	this.codigo_evento=codigo_evento;
    	this.nombre =nombre ;
    	this.total=total;
    }
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_producto);
		
		TextView text=(TextView)findViewById(R.id.sumtextview);
		
		
		Bundle bundle = getIntent().getExtras();
		final String cod_evento = bundle.getString("codigo_evento"); 	
		lv = (ListView) findViewById(R.id.listProductos);
		
		Integer cod = Integer.parseInt(cod_evento);

		bd.addProducto(1,"1", "Papas","600");
		sum= bd.getSum(cod_evento);
		text.setText("Total: ₡"+sum);
		
		
		
		//Toast.makeText(getApplicationContext(), sum, Toast.LENGTH_SHORT).show();
		adaptador = new ArrayAdapter<Producto>(
				this, android.R.layout.simple_list_item_1, bd.getAllProductos(cod));

	       lv = (ListView) findViewById(R.id.listProductos);
	       lv.setAdapter(adaptador);
	       registerForContextMenu(lv);
	       
	       
	       
	     
	       Button new_pro = (Button) findViewById(R.id.newProducto);
		     new_pro.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(v.getContext(),Nuevo_Producto.class);
					intent.putExtra("codigo_even", cod_evento);
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
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		final Producto productos = bd.getTodosProductos().get(info.position);
		super.onContextItemSelected(item);
		
		Context context = getApplicationContext();
		
		switch(item.getItemId())
		{
		
			
		case R.id.eliminar:
			AlertDialog.Builder dialogoBorrar = new AlertDialog.Builder(this);
			dialogoBorrar.setTitle("Eliminar Producto");
			dialogoBorrar.setCancelable(false);
			dialogoBorrar.setMessage("¿Desea borrar el producto?");
		
			dialogoBorrar.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
		         public void onClick(DialogInterface dialog, int boton) {
		        	bd.deleteProducto(productos.getCodProducto());
		           
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
	        return  nombre+" ₡"+total;
	    }
	 
	  public String getCodProducto() {
			return codigo_producto;
		}
	  
	
	  public void setCodProducto(String codigo_producto) {
			this.codigo_producto = codigo_producto;
		}
	  
	  public String getCodEventProducto() {
			return codigo_evento;
		}
	  
	
	  public void setCodEventProducto(String codigo_evento) {
			this.codigo_evento = codigo_evento;
		}
	  
	  public String getNomProducto() {
			return nombre;
		}

		public void setNomProducto(String nombre) {
			this.nombre = nombre;
		}
		
		public String getTotal() {
			return total;
		}

		public void setTotal(String total) {
			this.total = total;
		}
		
		public String getSum() {
			return sum;
		}

		public void setSum(String sum) {
			this.sum = sum;
		}
}
