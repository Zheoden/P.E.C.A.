package com.example.peca;
import helpers.Usuario;
import helpers.basededatos;

import com.example.peca.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class JuegoPorPrimeraVez extends Activity {
	
	public static void hideSoftKeyboard(Activity activity) {
	    InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
	    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}
	public void setupUI(View view) {

	    //Set up touch listener for non-text box views to hide keyboard.
	    if(!(view instanceof EditText)) {

	        view.setOnTouchListener(new OnTouchListener() {

	            public boolean onTouch(View v, MotionEvent event) {
	                hideSoftKeyboard(JuegoPorPrimeraVez.this);
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
		setContentView(R.layout.juego_por_primera_vez_2);
		setupUI(findViewById(R.id.JuegoPorPrimeraVez));
		
		
		//Creo variables y preparo la base de datos.
        final basededatos BaseDeDatos = new basededatos(this, "BaseDeDatos", null, 1);
        final SQLiteDatabase base = BaseDeDatos.getWritableDatabase();
        
        final EditText etNombre = (EditText) findViewById (R.id.etNombre);
        

	    Button VolverAtras = (Button)findViewById(R.id.btnVolverAtras); 
	    Button YaTermine = (Button)findViewById(R.id.btnYaTermine);
	    try {
	    	
		    Bundle datos = this.getIntent().getExtras();
			String Nombre = datos.getString("Nombre");
			etNombre.setText(Nombre);
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	        
	    YaTermine.setOnClickListener(new OnClickListener() { 
	    public void onClick(View v) { 
	    	
	    	//String[] args = new String[] {"Nom"};
	    	//Cursor c = base.rawQuery("SELECT nombre FROM Usuarios WHERE nombre= ?", args);
	    	
	        String Nom  = etNombre.getText().toString();
			Toast msg2 = Toast.makeText(getApplicationContext(), Nom, Toast.LENGTH_SHORT); 
	    	
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

	    		Toast msg = Toast.makeText(getApplicationContext(), "Tu nombre ya está registrado en el juego. Ingresá otro nombre", Toast.LENGTH_SHORT); 
	    		msg.show(); 
	    	}
	    	else
	    	{
	    		base.execSQL("INSERT INTO Usuarios (nombre) VALUES ('" + Nom + "')");
	    		Intent ActividadAInvocar1;
	    	    
	    		ActividadAInvocar1 = new Intent(JuegoPorPrimeraVez.this, ElegiElArea.class);
	    	
	    		startActivity(ActividadAInvocar1);
	    	}

    		
    		}
	    });
	    
	 
	    VolverAtras.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {   		
	     		Intent ActividadAInvocar;
	     		ActividadAInvocar = new Intent(JuegoPorPrimeraVez.this, PaginaPrincipal.class);
	     		startActivity(ActividadAInvocar);
	     	} 
		});
  } 
} 