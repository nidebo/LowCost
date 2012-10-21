package com.example.lowcost;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DataBaseHelper extends SQLiteOpenHelper{
//campos de la base de datos que se creararan en la tabla
    private static final String DATABASE_NAME = "registro.db";
    public static final String TABLA = "tabla";
    public static final String NOMBRE = "nombre";
    public static final String CIUDAD = "ciudad";
     
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        }
    @Override
    public void onCreate(SQLiteDatabase db) {
    //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL("CREATE TABLE " + TABLA + " (_id  INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, ciudad TEXT);");
    }    
    @Override
    public void onUpgrade(SQLiteDatabase db, int OldVersion, int NewVersion) {
    /* NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de eliminar la tabla anterior y crearla
     * de nuevo vacía con el nuevo formato. Sin embargo lo normal será que haya que migrar datos de la tabla antigua
     * a la nueva, por lo que este método debería ser más elaborado.*/
        android.util.Log.v("tabla","Upgrading database, which will destroy all old data");        
    //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS " + TABLA);
    //Se utiliza para poder crear la nueva base de datos
        onCreate(db);
    }
}