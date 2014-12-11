package com.example.gestiodonto;



import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity{

	
	Activity activity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
	
	/*public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuinfo)
	{
		 super.onCreateContextMenu(menu, v, menuinfo);
		 getMenuInflater().inflate(R.menu.opciones  , menu);
	}
	
	
	public boolean onContextItemSeleted(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.pacientes :
			Toast.makeText(MainActivity.this, "Menu Editar", Toast.LENGTH_LONG).show();
			return super.onOptionsItemSelected(item);
		case R.id.eliminar:
			Toast.makeText(MainActivity.this,"Menu Editar", Toast.LENGTH_LONG).show();
			return super.onOptionsItemSelected(item);
		case R.id.ver:
			Toast.makeText(MainActivity.this, "Menu Editar", Toast.LENGTH_LONG).show();
			return super.onOptionsItemSelected(item);
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	*/
}




