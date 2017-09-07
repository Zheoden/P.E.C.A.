package com.example.peca;



import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Ganaste extends Activity {
	
	
//	public static void hideSoftKeyboard(Activity activity) {
//	    InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
//	    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
//	}
//	public void setupUI(View view) {
//
//	    //Set up touch listener for non-text box views to hide keyboard.
//	    if(!(view instanceof EditText)) {
//
//	        view.setOnTouchListener(new OnTouchListener() {
//
//	            public boolean onTouch(View v, MotionEvent event) {
//	                hideSoftKeyboard(Ganaste.this);
//	                return false;
//	            }
//
//	        });
//	    }
//
//	    //If a layout container, iterate over children and seed recursion.
//	    if (view instanceof ViewGroup) {
//
//	        for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
//
//	            View innerView = ((ViewGroup) view).getChildAt(i);
//
//	            setupUI(innerView);
//	        }
//	    }
//	}
	
	
	
	int Nivel;
	String Juego;
	String Mensaje;
	
	
	
	public SoundPool sp;
	int aplausos = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ganaste);
//		setupUI(findViewById(R.id.GanasteVista));
		
		final Bundle datos = this.getIntent().getExtras(); 
		Nivel = datos.getInt("pNivel");
		Juego = datos.getString("pJuego");
		
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		aplausos = sp.load(this, R.raw.aplausos, 1);	
		
		
		TextView txtGanasteElNivel =(TextView)findViewById(R.id.txtGanasteElNivel);
		Button btnVolver = (Button) findViewById(R.id.btnVolver);
		

		btnVolver.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent ActividadAInvocar;
	    		ActividadAInvocar = new Intent(Ganaste.this, ElegiElArea.class);
//	    		ActividadAInvocar.putExtras(datos); 
				startActivity(ActividadAInvocar);
	    		
				
			}
		});	
		
		Mensaje= "Felicitaciones!\n\nGanaste el nivel " + Nivel + " del juego: " + Juego;
		
		txtGanasteElNivel.setText(Mensaje);
		sp.play(aplausos, 1, 1, 0, 0, 1);
	}
}
