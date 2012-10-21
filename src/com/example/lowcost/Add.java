package com.example.lowcost;

import android.app.Activity;
import android.content.*;
import android.database.sqlite.*;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class Add extends Activity {
    /** Called when the activity is first created. */
    //declarando variables
    private EditText nombre,ciudad;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    //almacenando valores ingresados por el usuario
        nombre=(EditText)findViewById(R.id.edit_name);
        ciudad=(EditText)findViewById(R.id.edit_city);
    //declarando a los botones
        final Button agregar = (Button)findViewById(R.id.yes_b);
        //final Button cancelar = (Button)findViewById(R.id.btnCancelar);
    //acción cuando le dan click al boton agregar   
    agregar.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
    //se realiza el intento de enviar los datos de un activity a otro, de Registro a Salida.
            //Intent intent = new Intent(RegistroLibroActivity.this, SalidaResultado.class);
            //Bundle b = new Bundle();
    //almacenamiento temporal para poder usarlo en otro activity
            String bnombre=String.valueOf(nombre.getText());
            String bciudad=String.valueOf(ciudad.getText());
            
            nombre.setText("");
            ciudad.setText("");
                         
    //llamada al metodo para insertar los datos.
            insertPrso(bnombre,bciudad);
    //datos que seran vistos en el otro main
           // b.putString("T", btitulo);
           // b.putString("A", bautor);
           // b.putString("I", bidioma);
           // b.putString("B", baño);
           // intent.putExtras(b);
           // startActivity(intent);
            }
        });
    //acción cuando le dan click al boton agregar   
   // cancelar.setOnClickListener(new View.OnClickListener() {
     //   public void onClick(View v) {
       //     titulo.setText(" ");
         //   autor.setText(" ");
           // idioma.setText(" ");
           // año.setText(" ");
           // }
       // });
    }       
    //metodo para insertar a la tabla
    private void insertPrso(String nombre, String ciudad){
        DataBaseHelper databasehelper = new DataBaseHelper(this);
        SQLiteDatabase db = databasehelper.getWritableDatabase();
     if(db != null){            
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.NOMBRE, nombre);
        cv.put(DataBaseHelper.CIUDAD, ciudad);        
                 
        db.insert("tabla", DataBaseHelper.NOMBRE, cv);
        db.close();
     }
    }
    
    public void onPressClear(View view) {
        // Do something in response to button
    	 DataBaseHelper databasehelper = new DataBaseHelper(this);
         SQLiteDatabase db = databasehelper.getWritableDatabase();
      if(db != null){
    	    //Se elimina la versión anterior de la tabla
          db.execSQL("DROP TABLE IF EXISTS " + databasehelper.TABLA);
      //Se utiliza para poder crear la nueva base de datos
          databasehelper.onCreate(db);
      }
   }
}