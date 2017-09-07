package com.example.peca;

import helpers.basededatos;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PaginaPrincipal extends Activity {

	public static void hideSoftKeyboard(Activity activity) {
	    InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
	    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}
	public void setupUI(View view) {

	    //Set up touch listener for non-text box views to hide keyboard.
	    if(!(view instanceof EditText)) {

	        view.setOnTouchListener(new OnTouchListener() {

	            public boolean onTouch(View v, MotionEvent event) {
	                hideSoftKeyboard(PaginaPrincipal.this);
	                return false;
	            }

	        });
	    }

	    //If a layout container, iterate over children and seed recursion.
	    if (view instanceof ViewGroup) {

	        for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

	            View innerView = ((ViewGroup) view).getChildAt(i);

	            setupUI(innerView);
	        }
	    }
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_principal);
        setupUI(findViewById(R.id.PaginaPrincipal));
  
        final basededatos BaseDeDatos = new basededatos(this, "BaseDeDatos", null, 1);
        final SQLiteDatabase base = BaseDeDatos.getWritableDatabase();
        
        final EditText edtNombre = (EditText) findViewById (R.id.edtNombre);
        
        
       //Me lleva a juego por primera vez.
        Button JuegoPorPrimeraVez = (Button)findViewById(R.id.btnJuegoPorPrimeraVez);        
        JuegoPorPrimeraVez.setOnClickListener(new OnClickListener() { 
        	
    	public void onClick(View v) {   		

    		Intent ActividadAInvocar;
    		ActividadAInvocar = new Intent(PaginaPrincipal.this, JuegoPorPrimeraVez.class);
    		startActivity(ActividadAInvocar);

    		
    	} 
    } );
    
	    Button Ingresar = (Button)findViewById(R.id.btnIngresar);
	    Ingresar.setOnClickListener(new OnClickListener(){
	    	public void onClick(View v){
	    		
	    		String Nom  = edtNombre.getText().toString();

	 	    	Cursor c = base.rawQuery("SELECT * FROM Usuarios", null);
	 	    	
	 	    	boolean SonIguales = false;
	 	    	//Nos aseguramos de que existe al menos un registro
	 	    	if (c.moveToFirst()) {
	 	    		
	 	    	     //Recorremos el cursor hasta que no haya más registros
	 	    	     do {
	 	    	          String dato = c.getString(1);
	 	    	          //Esta funcionando bien, pero al revez.
	 	    	          if(Nom.equals(dato)){
	 	    	        	  SonIguales = true; 
	 	    	          }
	 	    	          else
	 	    	          {
	 	    	        	  SonIguales = false; 
	 	    	          }
	 	    	          
	 	    	     } while(c.moveToNext()== true && SonIguales == false);
	 	    	}
	 	    	
	 	    	if(SonIguales == true){

		    		Intent NuevaActividad;
		    		NuevaActividad = new Intent(PaginaPrincipal.this, ElegiElArea.class);
		    		startActivity(NuevaActividad);
	 	    	}
	 	    	else
	 	    	{
	 	    		Toast msg = Toast.makeText(getApplicationContext(), "Tu nombre no está registrado en el juego. Ingresalo aqui.", Toast.LENGTH_LONG); 
		    		msg.show(); 
		    		
		    		final String Nombre = edtNombre.getText().toString();;
		    		final Bundle datos = new Bundle();
		    		datos.putString("Nombre", Nombre);
		    		
	 	    		Intent ActividadAInvocar;
	 	    		ActividadAInvocar = new Intent(PaginaPrincipal.this, JuegoPorPrimeraVez.class);
	 	    		ActividadAInvocar.putExtras(datos);
	 	    		startActivity(ActividadAInvocar);
	 	    	}
	    		
	    		
    		
    	}
    	
    });
  } 
} 