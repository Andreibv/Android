package com.example.gestiodonto;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class BD extends SQLiteOpenHelper {
	
	SQLiteDatabase db;
	Cursor cursor;
	String query;
	ContentValues values;
	// Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "PacientesDB.db";

	public BD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
	String CREATE_PACIENTES_TABLE = "CREATE TABLE pacientes ( " +
            "cedula TEXT PRIMARY KEY, " + 
            "nombre TEXT, "+
            "apellido1 TEXT,"+
            "apellido2 TEXT,"+
            "fechanac TEXT,"+
            "expediente INTEGER,"+
            "telefono TEXT,"+
            "direccion TEXT )";
	
	String CREATE_CATEDRAS_TABLE = "CREATE TABLE catedras (" +
			"codcat INTEGER PRIMARY KEY,"+
			"nombre TEXT);";
	
	String CREATE_TRATAMIENTO_TABLE = "CREATE TABLE tratamiento (" +
			"codtra INTEGER PRIMARY KEY,"+
			"nombre TEXT," +
			"nombrecattra TEXT);";
	
	String CREATE_TURNO_TABLE = "CREATE TABLE turnos (" +
			"codtur INTEGER PRIMARY KEY,"+
			"dia TEXT," +
			"horaI TEXT," +
			"horaF TEXT);";
	
	String CREATE_REGISTRO_TABLE = "CREATE TABLE registros (" +
			"codreg INTEGER PRIMARY KEY,"+
			"paciente TEXT," +
			"turno TEXT,"+
			"tratamiento TEXT," +
			"fecha TEXT);";
	
	String CREATE_USUARIOS_TABLE = "CREATE TABLE usuarios (" +
			"usuario TEXT PRIMARY KEY,"+
			"password TEXT);";

	@Override
	public void onCreate(SQLiteDatabase db) {
		
      //-------------------PACIENTES-------------------
		db.execSQL(CREATE_PACIENTES_TABLE);
		db.execSQL(CREATE_CATEDRAS_TABLE);
		db.execSQL(CREATE_TRATAMIENTO_TABLE);
		db.execSQL(CREATE_TURNO_TABLE);
		db.execSQL(CREATE_REGISTRO_TABLE);
		db.execSQL(CREATE_USUARIOS_TABLE);
      //-------------------PACIENTES-------------------
      
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS pacientes");
        db.execSQL("DROP TABLE IF EXISTS catedras");
        db.execSQL("DROP TABLE IF EXISTS tratamiento");
        db.execSQL("DROP TABLE IF EXISTS turnos");
        db.execSQL("DROP TABLE IF EXISTS registros");
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        // create fresh books table
        this.onCreate(db);

	}
	
	
	
    //***************************************REGISTRO*******************************************************************
    //***************************************REGISTRO*******************************************************************
	
	   private static final String TABLE_REGISTROS = "registros";
	    private static final String KEY_CODREG= "codreg";
	    private static final String KEY_TURNO = "turno";
	    private static final String KEY_PACIENTE = "paciente";
	    private static final String KEY_TRATAMIENTO = "tratamiento";
	    private static final String KEY_FECHA = "fecha";
	    private static final String[] COLUMNSREG = {KEY_CODREG,KEY_PACIENTE,KEY_TURNO,KEY_TRATAMIENTO,KEY_FECHA};
		

		
	    public void addRegistro(Integer codreg,String paciente,String turno,String tratamiento,String fecha){
	    	   
			db = this.getWritableDatabase();
			
			values = new ContentValues();
			values.put(KEY_CODREG, codreg ); // get nombre
			values.put(KEY_PACIENTE, paciente ); // get nombre
			values.put(KEY_TURNO, turno ); // get nombre
			values.put(KEY_TRATAMIENTO, tratamiento ); // get nombre
			values.put(KEY_FECHA, fecha ); // get nombre
		

			db.insert(TABLE_REGISTROS, // table
					null, //nullColumnHack
					values); // key/value -> keys = column names/ values = column values

			db.close(); 
		}
	    
	   
	    public void deleteRegistro(int i) {
	    	 
	        db = this.getWritableDatabase();
	 
	        db.delete(TABLE_REGISTROS,
	        		KEY_CODREG+" = ?",
	                new String[] { String.valueOf(i) });
	 
	        db.close();
	    }
	    
	    public ArrayList <Registro> getAllRegistros(){
			ArrayList <Registro> registro = new ArrayList<Registro>();
			
			query = "Select * FROM "+TABLE_REGISTROS+
					" ORDER BY fecha DESC;";
			
			db = this.getWritableDatabase();
			cursor = db.rawQuery(query, null);
			
			Registro registros=null;
			if(cursor.moveToFirst()){
				do{ 
					registros = new Registro();
					registros.setCodRegistro(cursor.getInt(0));
					registros.setRegPaciente(cursor.getString(1));
					registros.setRegTurno(cursor.getString(2));
					registros.setRegTratamiento(cursor.getString(3));
					registros.setRegFecha(cursor.getString(4));
					registro.add(registros);
				}while(cursor.moveToNext());
			}
			return registro;	
		}
	    
	    
	    public Registro getRegistro(String codreg){
			 
	        db = this.getReadableDatabase();
	 
	        cursor = db.query(TABLE_REGISTROS, // a. table
	        		COLUMNSREG, // b. column names
	                " codreg = ?", // c. selections 
	                new String[] { String.valueOf(codreg) }, // d. selections args
	                null, // e. group by
	                null, // f. having
	                null, // g. order by
	                null); // h. limit
	 
	        
	        Registro registros = new Registro();
	        if (cursor.moveToFirst()) {
	        	registros.setCodRegistro(cursor.getInt(0));
				registros.setRegPaciente(cursor.getString(1));
				registros.setRegTurno(cursor.getString(2));
				registros.setRegTratamiento(cursor.getString(3));
				registros.setRegFecha(cursor.getString(4));
	        }
	     
	        return registros;
	    }
	    
    //***************************************REGISTRO*******************************************************************
    //***************************************REGISTRO*******************************************************************
 
	
	//***************************************PACIENTES*******************************************************************
	//***************************************PACIENTES*******************************************************************
	
	// Books table name
    private static final String TABLE_PACIENTES = "pacientes";

 
    private static final String KEY_CEDULA = "cedula";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_APELLIDO1 = "apellido1";
    private static final String KEY_APELLIDO2 = "apellido2";
    private static final String KEY_FECHANAC = "fechanac";
    private static final String KEY_EXPEDIENTE = "expediente";
    private static final String KEY_TELEFONO = "telefono";
    private static final String KEY_DIRECCION = "direccion";
    
 
    private static final String[] COLUMNS = {KEY_CEDULA,KEY_NOMBRE,KEY_APELLIDO1,KEY_APELLIDO2,KEY_FECHANAC,KEY_EXPEDIENTE,KEY_TELEFONO,KEY_DIRECCION};
	
	public void addPaciente(String cedula,String nombre,String apellido1,String apellido2,String fechanac,Integer expediente,String telefono,String direccion){
   
		db = this.getWritableDatabase();
		
		values = new ContentValues();
		values.put(KEY_CEDULA, cedula); // get cedula 
		values.put(KEY_NOMBRE, nombre ); // get nombre
		values.put(KEY_APELLIDO1, apellido1 ); // get apellido1
		values.put(KEY_APELLIDO2, apellido2 ); // get apelido2
		values.put(KEY_FECHANAC, fechanac ); // get fechanac
		values.put(KEY_EXPEDIENTE, expediente ); // get expediente
		values.put(KEY_TELEFONO, telefono ); // get telefono
		values.put(KEY_DIRECCION, direccion ); // get direccion

		db.insert(TABLE_PACIENTES, // table
				null, //nullColumnHack
				values); // key/value -> keys = column names/ values = column values

		db.close(); 
	}
	
	
	public ArrayList <Pacientes> getAllPacientes(){
		ArrayList <Pacientes> paciente = new ArrayList<Pacientes>();
		
		query = "Select * FROM "+TABLE_PACIENTES;
		
		db = this.getWritableDatabase();
		cursor = db.rawQuery(query, null);
		

		Pacientes pacientes = null;
		if(cursor.moveToFirst()){
			do{
				pacientes = new Pacientes();
				pacientes.setCedula(cursor.getString(0));
				pacientes.setNombre(cursor.getString(1));
				pacientes.setApellido1(cursor.getString(2));
				pacientes.setApellido2(cursor.getString(3));
				pacientes.setFechanac(cursor.getString(4));
				pacientes.setExpediente(cursor.getInt(5));
				pacientes.setTelefono(cursor.getString(6));
				pacientes.setDireccion(cursor.getString(7));
                paciente.add(pacientes);
			}while(cursor.moveToNext());
		}
		return paciente;	
	}

	
	public Pacientes getPaciente(String cedula){
		 
        db = this.getReadableDatabase();
 
        cursor = 
                db.query(TABLE_PACIENTES, // a. table
                COLUMNS, // b. column names
                " cedula = ?", // c. selections 
                new String[] { String.valueOf(cedula) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
 
        Pacientes pacientes = new Pacientes();
        if (cursor.moveToFirst()) {
        	
        	pacientes.setCedula(cursor.getString(0));
        	pacientes.setNombre(cursor.getString(1));
        	pacientes.setApellido1(cursor.getString(2));
        	pacientes.setApellido2(cursor.getString(3));
        	pacientes.setFechanac(cursor.getString(4));
        	pacientes.setExpediente(cursor.getInt(5));
        	pacientes.setTelefono(cursor.getString(6));
        	pacientes.setDireccion(cursor.getString(7));
        }
     
        return pacientes;
    }
	
    public void deletePaciente(String cedula) {
 
        db = this.getWritableDatabase();
 
        db.delete(TABLE_PACIENTES,
                KEY_CEDULA+" = ?",
                new String[] { String.valueOf(cedula) });
 
        db.close();
    }
    
    public int updatePaciente(String cedula,String nombre,String apellido1,String apellido2,String fechanac,Integer expediente,String telefono,String direccion) {
    	 
        db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_CEDULA, cedula); // get cedula 
		values.put(KEY_NOMBRE, nombre ); // get nombre
		values.put(KEY_APELLIDO1, apellido1 ); // get apellido1
		values.put(KEY_APELLIDO2, apellido2 ); // get apelido2
		values.put(KEY_FECHANAC, fechanac ); // get fechanac
		values.put(KEY_EXPEDIENTE, expediente ); // get expediente
		values.put(KEY_TELEFONO, telefono ); // get telefono
		values.put(KEY_DIRECCION, direccion ); // get direccion
 
        int i = db.update(TABLE_PACIENTES, //table
                values, // column/value
                KEY_CEDULA+" = ?", // selections
                new String[] { String.valueOf(cedula) }); //selection args
 
        db.close();
 
        return i;
 
    }
    
    
  //***************************************PACIENTES*******************************************************************
  //***************************************PACIENTES*******************************************************************
    
    
    

    //***************************************CATEDRAS*******************************************************************
    //***************************************CATEDRAS******************************************************************* 
    
    private static final String TABLE_CATEDRAS = "catedras";
    private static final String KEY_CODCAT = "codcat";
    private static final String KEY_NOMBRECAT = "nombre";
    private static final String[] COLUMNSCAT = {KEY_CODCAT,KEY_NOMBRECAT};
	
    
    public void addCatedra(Integer codcat,String nombrecat){
    	   
		db = this.getWritableDatabase();
		
		values = new ContentValues();
		values.put(KEY_CODCAT, codcat ); // get nombre
		values.put(KEY_NOMBRECAT, nombrecat ); // get nombre
	

		db.insert(TABLE_CATEDRAS, // table
				null, //nullColumnHack
				values); // key/value -> keys = column names/ values = column values

		db.close(); 
	}
    
    public void deleteCatedra(String codcat) {
    	 
        db = this.getWritableDatabase();
 
        db.delete(TABLE_CATEDRAS,
        		KEY_CODCAT+" = ?",
                new String[] { String.valueOf(codcat) });
 
        db.close();
    }
    
    public ArrayList <Catedras> getAllCatedras(){
		ArrayList <Catedras> catedra = new ArrayList<Catedras>();
		
		query = "Select * FROM "+TABLE_CATEDRAS+";";
		
		db = this.getWritableDatabase();
		cursor = db.rawQuery(query, null);
		
 		Catedras catedras=null;
		if(cursor.moveToFirst()){
			do{ 
				catedras = new Catedras();
				catedras.setCodCatedra(cursor.getString(0));
				catedras.setNomCatedra(cursor.getString(1));
				catedra.add(catedras);
			}while(cursor.moveToNext());
		}
		return catedra;	
	}
    
    
    public Catedras getCatedra(String codcat){
		 
        db = this.getReadableDatabase();
 
        cursor = db.query(TABLE_CATEDRAS, // a. table
                		COLUMNSCAT, // b. column names
                " codcat = ?", // c. selections 
                new String[] { String.valueOf(codcat) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
 
        
        Catedras catedras = new Catedras();
        if (cursor.moveToFirst()) {
        	catedras.setCodCatedra(cursor.getString(0));
        	catedras.setNomCatedra(cursor.getString(1));
        }
     
        return catedras;
    }
    
    //***************************************CATEDRAS*******************************************************************
    //***************************************CATEDRAS*******************************************************************
   
    
  //***************************************TURNO*******************************************************************
  //***************************************TURNO*******************************************************************
    
    private static final String TABLE_TURNOS = "turnos";
    private static final String KEY_CODTUR = "codtur";
    private static final String KEY_DIA = "dia";
    private static final String KEY_HORAI = "horaI";
    private static final String KEY_HORAF = "horaf";
    private static final String[] COLUMNSTUR = {KEY_CODTUR,KEY_DIA,KEY_HORAI,KEY_HORAF};
    

    public void addTurno(Integer codtur,String dia,String horaI, String horaF){
    	   
		db = this.getWritableDatabase();
		
		values = new ContentValues();
		values.put(KEY_CODTUR, codtur ); // get nombre
		values.put(KEY_DIA, dia ); // get nombre
		values.put(KEY_HORAI, horaI ); // get nombre
		values.put(KEY_HORAF, horaF ); // get nombre
	

		db.insert(TABLE_TURNOS, // table
				null, //nullColumnHack
				values); // key/value -> keys = column names/ values = column values

		db.close(); 
	}
    
    public void deleteTurno(int i) {
    	 
        db = this.getWritableDatabase();
 
        db.delete(TABLE_TURNOS,
        		KEY_CODTUR+" = ?",
                new String[] { String.valueOf(i) });
 
        db.close();
    }
    
    public ArrayList <Turno> getAllTurnos(){
		ArrayList <Turno> turno = new ArrayList<Turno>();
		
		query = "Select * FROM "+TABLE_TURNOS+";";
		
		db = this.getWritableDatabase();
		cursor = db.rawQuery(query, null);
		
 		Turno turnos=null;
		if(cursor.moveToFirst()){
			do{ 
				turnos = new Turno();
				turnos.setCodTurno(cursor.getInt(0));
				turnos.setDiaTurno(cursor.getString(1));
				turnos.setHoraITurno(cursor.getString(2));
				turnos.setHoraFTurno(cursor.getString(3));
				turno.add(turnos);
			
			}while(cursor.moveToNext());
		}
		return turno;	
	}
 
    
    public Turno getTurno(String codtur){
		 
        db = this.getReadableDatabase();
 
        cursor = db.query(TABLE_TURNOS, // a. table
        		COLUMNSTUR, // b. column names
                " codcat = ?", // c. selections 
                new String[] { String.valueOf(codtur) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
 
        
        Turno turno = new Turno();
        if (cursor.moveToFirst()) {
        	turno.setCodTurno(cursor.getInt(0));
        	turno.setDiaTurno(cursor.getString(1));
        	turno.setHoraITurno(cursor.getString(2));
        	turno.setHoraFTurno(cursor.getString(3));
        }
     
        return turno;
    }
    
    public int updateTurno(Integer codtur,String dia,String horaI, String horaF) {
   	 
        db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values = new ContentValues();
		values.put(KEY_CODTUR, codtur ); // get nombre
		values.put(KEY_DIA, dia ); // get nombre
		values.put(KEY_HORAI, horaI ); // get nombre
		values.put(KEY_HORAF, horaF ); // get nombre
 
        int i = db.update(TABLE_TURNOS, //table
                values, // column/value
                KEY_CODTUR+" = ?", // selections
                new String[] { String.valueOf(codtur) }); //selection args
 
        db.close();
 
        return i;
 
    }
    
    
    
  //***************************************TURNO*******************************************************************
  //***************************************TURNO*******************************************************************
        
    
    //***************************************TRATAMIENTO*******************************************************************
    //***************************************TRATAMIENTO*******************************************************************

    private static final String TABLE_TRATAMIENTO = "tratamiento";
    private static final String KEY_CODTRA = "codtra";
    private static final String KEY_NOMBRETRA = "nombre";
    private static final String KEY_NOMBRECATTRA = "nombrecattra";
    private static final String[] COLUMNSTRA = {KEY_CODTRA,KEY_NOMBRETRA,KEY_NOMBRECATTRA};
	
    
    public void addTratamiento(Integer codtra,String nombretra, String nombrecattra){
    	   
		db = this.getWritableDatabase();
		
		values = new ContentValues();
		values.put(KEY_CODTRA, codtra ); // get nombre
		values.put(KEY_NOMBRETRA, nombretra ); // get nombre
		values.put(KEY_NOMBRECATTRA, nombrecattra ); // get nombre
	

		db.insert(TABLE_TRATAMIENTO, // table
				null, //nullColumnHack
				values); // key/value -> keys = column names/ values = column values

		db.close(); 
	}
    
    public void deleteTratamiento(String codtra) {
    	 
        db = this.getWritableDatabase();
 
        db.delete(TABLE_TRATAMIENTO,
        		KEY_CODTRA+" = ?",
                new String[] { String.valueOf(codtra) });
 
        db.close();
    }
    
    public ArrayList <Tratamiento> getAllTratamientos(){
		ArrayList <Tratamiento> tratamiento = new ArrayList<Tratamiento>();
		
		query = "Select * FROM "+TABLE_TRATAMIENTO+";";
		
		db = this.getWritableDatabase();
		cursor = db.rawQuery(query, null);
		
 		Tratamiento tratamientos = null;
		if(cursor.moveToFirst()){
			do{ 
				tratamientos = new Tratamiento();
				tratamientos.setCodTratamiento(cursor.getString(0));
				tratamientos.setNomTratamiento(cursor.getString(1));
				tratamientos.setNombreCatedraTratamiento(cursor.getString(2));
				tratamiento.add(tratamientos);
			}while(cursor.moveToNext());
		}
		return tratamiento;	
	}
    
    
    public Tratamiento getTratamiento(String codtra){
		 
        db = this.getReadableDatabase();
 
        cursor = db.query(TABLE_TRATAMIENTO, // a. table
        		COLUMNSTRA, // b. column names
                " codtra = ?", // c. selections 
                new String[] { String.valueOf(codtra) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
 
        
        Tratamiento tratamiento = new Tratamiento();
        if (cursor.moveToFirst()) {
        	tratamiento.setCodTratamiento(cursor.getString(0));
        	tratamiento.setNomTratamiento(cursor.getString(1));
        	tratamiento.setNombreCatedraTratamiento(cursor.getString(2));
        }
     
        return tratamiento;
    }
    //***************************************TRATAMIENTO*******************************************************************
    //***************************************TRATAMIENTO*******************************************************************
 
    
    
    //***************************************USUARIOS*******************************************************************
    //***************************************USUARIOS*******************************************************************
 
    private static final String TABLE_USUARIOS = "usuarios";
    private static final String KEY_USUARIO = "usuario";
    private static final String KEY_PASSWORD = "password";
    private static final String[] COLUMNSUSUARIO = {KEY_USUARIO,KEY_PASSWORD};
	
    
    public void addUsuario(String usuario,String password){
    	   
		db = this.getWritableDatabase();
		
		values = new ContentValues();
		values.put(KEY_USUARIO, usuario ); // get nombre
		values.put(KEY_PASSWORD, password ); // get nombre
	

		db.insert(TABLE_USUARIOS, // table
				null, //nullColumnHack
				values); // key/value -> keys = column names/ values = column values

		db.close(); 
	}
    
    public void deleteUsuario(String usuario) {
    	 
        db = this.getWritableDatabase();
 
        db.delete(TABLE_USUARIOS,
        		KEY_USUARIO+" = ?",
                new String[] { String.valueOf(usuario) });
 
        db.close();
    }
    
    
    
    
    public boolean getUsuario(String usuario,String password){
		 
        query = "Select * FROM "+TABLE_USUARIOS+
        		" WHERE usuario="+usuario+
        		" AND password="+password+";";
        
        db = this.getReadableDatabase();
        
        cursor = db.rawQuery(query, null);
        
  
        if (cursor.moveToFirst()) {
        	 return true;
        }else{
        	return false;
        }
     
         
    }
    
    
  //***************************************USUARIOS*******************************************************************
  //***************************************USUARIOS*******************************************************************
 
    
    	
}

