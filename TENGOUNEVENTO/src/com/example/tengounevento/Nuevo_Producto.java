package com.example.tengounevento;

import java.util.Random;
 

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Nuevo_Producto extends ActionBarActivity {
	BD bd=new BD(this);
	int cod_e,tot;
	String cod_evento;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nuevo__producto);
		
		Bundle bundle = getIntent().getExtras();
	    cod_evento = bundle.getString("codigo_even"); 
		
		final EditText nombre_pro = (EditText) findViewById(R.id.nombre_producto_crear);
		final EditText total_pro = (EditText) findViewById(R.id.total_producto_crear);
		
		//Toast.makeText(getApplicationContext(), cod_evento+"Ya cai "+nombre_pro.getText(), Toast.LENGTH_SHORT).show();
	    
		Button nueva_Cat = (Button) findViewById(R.id.crear_producto_btn);
	    nueva_Cat.setOnClickListener(new View.OnClickListener() {
	    	
			@Override
			public void onClick(View v) {
				CharSequence nom = nombre_pro.getText();
				CharSequence total = total_pro.getText(); 
			 
				Random rnd = new Random();
		
				//Toast.makeText(getApplicationContext(), nom.toString()+" "+total.toString()+" "+cod_evento, Toast.LENGTH_LONG).show();
				bd.addProducto(rnd.nextInt(), cod_evento, nom.toString(), total.toString());
				
				Intent intent = new Intent(getBaseContext(),Producto.class);
				intent.putExtra("codigo_evento", cod_evento);
	        	startActivity(intent);
			 
				
			}
		});
	     
	     Button cancel_pro = (Button) findViewById(R.id.cancel_new_producto);
	     cancel_pro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),Producto.class);
				intent.putExtra("codigo_evento", cod_evento);
				 startActivity(intent);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.eventos, menu);
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
		case R.id.get_back_eventos:
			 intent = new Intent(this,Evento.class);
			 startActivity(intent);
			//Toast.makeText(getApplicationContext(), "Si sirve", Toast.LENGTH_LONG).show();
			return super.onOptionsItemSelected(item);	
		case R.id.salir_from_prod:
			Intent homeIntent = new Intent(Intent.ACTION_MAIN);
		    homeIntent.addCategory( Intent.CATEGORY_HOME );
		    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
		    startActivity(homeIntent); 
			return super.onOptionsItemSelected(item);
		 
			
			}
		return super.onOptionsItemSelected(item);
	}
}
