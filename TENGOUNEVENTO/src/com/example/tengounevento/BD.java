package com.example.tengounevento;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BD extends SQLiteOpenHelper {
	SQLiteDatabase db;
	ContentValues values;
	Cursor cursor;
	String query;
	// Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "PacientesDB.db";
    
	
	public BD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
	
	
	String CREATE_EVENTOS_TABLE = "CREATE TABLE eventos (" +
			"codeven INTEGER PRIMARY KEY,"+
			"nombre TEXT," +
			"fecha TEXT);";
	

	String CREATE_PRODUCTOS_TABLE = "CREATE TABLE productos (" +
			"codprod INTEGER PRIMARY KEY,"+
			"codeven INTEGER,"+
			"nombre TEXT," +
			"total INTEGER);";
 
	@Override
	public void onCreate(SQLiteDatabase db) {
		
      //-------------------PACIENTES-------------------
		db.execSQL(CREATE_EVENTOS_TABLE);
		db.execSQL(CREATE_PRODUCTOS_TABLE);
      //-------------------PACIENTES-------------------
      
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older books table if existed
		 db.execSQL("DROP TABLE IF EXISTS eventos");
		 db.execSQL("DROP TABLE IF EXISTS productos");
        // create fresh books table
        this.onCreate(db);

	}
	
    //***************************************EVENTO*******************************************************************
    //***************************************EVENTO*******************************************************************
	
	private static final String TABLE_EVENTO = "eventos";
    private static final String KEY_CODEVEN= "codeven";
    private static final String KEY_NOMEVEN = "nombre";
    private static final String KEY_FECEVEN = "fecha";
    private static final String[] COLUMNSEVEN= {KEY_CODEVEN,KEY_NOMEVEN,KEY_FECEVEN};
	

	
    public void addEvento(Integer codeven,String nombre,String fecha){
    	   
		db = this.getWritableDatabase();
		
		values = new ContentValues();
		values.put(KEY_CODEVEN, codeven ); // get nombre
		values.put(KEY_NOMEVEN, nombre ); // get nombre
		values.put(KEY_FECEVEN, fecha ); // get nombre

		db.insert(TABLE_EVENTO, // table
				null, //nullColumnHack
				values); // key/value -> keys = column names/ values = column values

		db.close(); 
	}
    
   
    public void deleteEvento(String string) {
    	 
        db = this.getWritableDatabase();
 
        db.delete(TABLE_EVENTO,
        		KEY_CODEVEN+" = ?",
                new String[] { String.valueOf(string) });
 
        db.close();
    }
    
    public ArrayList <Evento> getAllEventos(){
		ArrayList <Evento> evento = new ArrayList<Evento>();
		
		query = "Select * FROM "+TABLE_EVENTO+
				" ORDER BY fecha DESC;";
		
		db = this.getWritableDatabase();
		cursor = db.rawQuery(query, null);
		
		Evento eventos=null;
		if(cursor.moveToFirst()){
			do{ 
				eventos = new Evento();
				eventos.setCodEven(cursor.getString(0));
				eventos.setNomEven(cursor.getString(1));
				eventos.setFecEven(cursor.getString(2)); 
				evento.add(eventos);
			}while(cursor.moveToNext());
		}
		return evento;	
	}
    
    
    public Evento getEvento(String codeven){
		 
        db = this.getReadableDatabase();
 
        cursor = db.query(TABLE_EVENTO, // a. table
        		COLUMNSEVEN, // b. column names
                " codeven = ?", // c. selections 
                new String[] { String.valueOf(codeven) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
 
        
        Evento eventos = new Evento();
        if (cursor.moveToFirst()) {
        	eventos = new Evento();
			eventos.setCodEven(cursor.getString(0));
			eventos.setNomEven(cursor.getString(1));
			eventos.setFecEven(cursor.getString(2));  
        }
     
        return eventos;
    }
    
	//***************************************EVENTO*******************************************************************
    //***************************************EVENTO*******************************************************************
	
    
   	//***************************************PRODUCTOS*******************************************************************
    //***************************************PRODUCTOS*******************************************************************

    private static final String TABLE_PRODUCTO = "productos";
    private static final String KEY_CODPROD = "codprod";
    private static final String KEY_CODEVENPRO= "codeven";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_TOTAL = "total";
    private static final String[] COLUMNSPRODUCTO = {KEY_CODPROD,KEY_CODEVENPRO,KEY_NOMBRE,KEY_TOTAL};
	

    
    public void addProducto(int codprod,String codeven,String nombre,String total){
    	   
		db = this.getWritableDatabase();
		
		values = new ContentValues();
		values.put(KEY_CODPROD, codprod ); // get nombre
		values.put(KEY_CODEVENPRO, codeven ); // get nombre
		values.put(KEY_NOMBRE, nombre ); // get nombre
		values.put(KEY_TOTAL, total ); // get nombre

		db.insert(TABLE_PRODUCTO, // table
				null, //nullColumnHack
				values); // key/value -> keys = column names/ values = column values

		db.close(); 
	}
    
   
    public void deleteProducto(String string) {
    	 
        db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTO,
        		KEY_CODPROD+" = ?",
                new String[] { String.valueOf(string) });
        db.close();
    }
 
    public ArrayList <Producto> getAllProductos(int Codevent){
		ArrayList <Producto> producto = new ArrayList<Producto>();
		
		query = "Select * FROM productos"+
				" WHERE codeven="+Codevent+";";
		
		db = this.getWritableDatabase();
		cursor = db.rawQuery(query, null);
		
		Producto productos=null;
		if(cursor.moveToFirst()){
			do{ 
				productos = new Producto();
				productos.setCodProducto(cursor.getString(0));
				productos.setCodEventProducto(cursor.getString(1));
				productos.setNomProducto(cursor.getString(2));
				productos.setTotal(cursor.getString(3));
				producto.add(productos);
			}while(cursor.moveToNext());
		}
		return producto;	
	}
    
    
    public ArrayList <Producto> getTodosProductos(){
  		ArrayList <Producto> producto = new ArrayList<Producto>();
  		
  		query = "Select * FROM productos";
  		
  		db = this.getWritableDatabase();
  		cursor = db.rawQuery(query, null);
  		
  		Producto productos=null;
  		if(cursor.moveToFirst()){
  			do{ 
  				productos = new Producto();
  				productos.setCodProducto(cursor.getString(0));
  				productos.setCodEventProducto(cursor.getString(1));
  				productos.setNomProducto(cursor.getString(2));
  				productos.setTotal(cursor.getString(3));
  				producto.add(productos);
  			}while(cursor.moveToNext());
  		}
  		return producto;	
  	}
    
    public String getSum(String Codevent){
		ArrayList <Producto> sum = new ArrayList<Producto>();
		String retorna="0";
		query = "Select sum(total) FROM productos" +
				" WHERE codeven="+Codevent+";";
		
		db = this.getWritableDatabase();
		cursor = db.rawQuery(query, null);
		
		Producto productos=null;
		if(cursor.moveToFirst()){
			retorna=cursor.getString(0);
		}
		return retorna;	
	}
    
    
    public Producto getProducto(String codpro){
		 
        db = this.getReadableDatabase();
 
        cursor = db.query(TABLE_PRODUCTO, // a. table
        		COLUMNSPRODUCTO, // b. column names
                " codprod = ?", // c. selections 
                new String[] { String.valueOf(codpro) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
 
        
        Producto productos = new Producto();
        if (cursor.moveToFirst()) {
        	productos = new Producto();
			productos.setCodProducto(cursor.getString(0));
			productos.setCodEventProducto(cursor.getString(1));
			productos.setNomProducto(cursor.getString(2));
			productos.setTotal(cursor.getString(3));
        }
     
        return productos;
    }
    
    
    //***************************************PRODUCTOS*******************************************************************
    //***************************************PRODUCTOS*******************************************************************
   	   
	
}
