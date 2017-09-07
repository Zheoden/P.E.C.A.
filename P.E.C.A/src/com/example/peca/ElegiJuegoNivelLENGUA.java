package com.example.peca;

import helpers.Niveles;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ElegiJuegoNivelLENGUA extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_elegi_juego_nivel_lengu);
		
		
		Button btnCuentosInfo = (Button)findViewById(R.id.btnCuentosInfo);
		Button btnAhorcadoInfo = (Button)findViewById(R.id.btnAhorcadoInfo);
		
		Button btnAhor1 = (Button)findViewById(R.id.btnAhor1);
		Button btnAhor2 = (Button)findViewById(R.id.btnAhor2);
		Button btnAhor3 = (Button)findViewById(R.id.btnAhor3);
		Button btnAhor4 = (Button)findViewById(R.id.btnAhor4);
		Button btnHisto1 = (Button)findViewById(R.id.btnHisto1);
		Button btnHisto2 = (Button)findViewById(R.id.btnHisto2);
		Button btnHisto3 = (Button)findViewById(R.id.btnHisto3);
		Button btnHisto4 = (Button)findViewById(R.id.btnHisto4);
		Button Volver = (Button)findViewById(R.id.btnVolverAtras);
		
		Volver.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent ActividadAInvocar;
	    		ActividadAInvocar = new Intent(ElegiJuegoNivelLENGUA.this,ElegiElArea.class);
	    		startActivity(ActividadAInvocar);
			}
		});
		
		btnAhor1.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {
	    		final int Nivel = 1;
	    		final Bundle datos = new Bundle();
	    		datos.putInt("pNivel", Nivel);
	    		 
	     		Intent NuevaAct;
	     		NuevaAct = new Intent(ElegiJuegoNivelLENGUA.this, Ahorcado.class);
	     		NuevaAct.putExtras(datos); 
	     		startActivity(NuevaAct);
	     	} 
		});
		
		btnAhor2.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {
	    		final int Nivel = 2;
	    		final Bundle datos = new Bundle();
	    		datos.putInt("pNivel", Nivel);
	    		 
	     		Intent NuevaAct;
	     		NuevaAct = new Intent(ElegiJuegoNivelLENGUA.this, Ahorcado.class);
	     		NuevaAct.putExtras(datos); 
	     		startActivity(NuevaAct);
	     	} 
		});
		
		btnAhor3.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {
	    		final int Nivel = 3;
	    		final Bundle datos = new Bundle();
	    		datos.putInt("pNivel", Nivel);
	    		 
	     		Intent NuevaAct;
	     		NuevaAct = new Intent(ElegiJuegoNivelLENGUA.this, Ahorcado.class);
	     		NuevaAct.putExtras(datos); 
	     		startActivity(NuevaAct);
	     	} 
		});
		
		btnAhor4.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {
	    		final int Nivel = 4;
	    		final Bundle datos = new Bundle();
	    		datos.putInt("pNivel", Nivel);
	    		 
	     		Intent NuevaAct;
	     		NuevaAct = new Intent(ElegiJuegoNivelLENGUA.this, Ahorcado.class);
	     		NuevaAct.putExtras(datos); 
	     		startActivity(NuevaAct);
	     	} 
		});
		
		btnHisto1.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {  
	    		final int Nivel = 1;
		    	final Bundle datos = new Bundle();
		    	datos.putInt("pNivel", Nivel);
	    		 
	     		Intent NuevaAct;
	     		NuevaAct = new Intent(ElegiJuegoNivelLENGUA.this, Cuentos.class);
	    		NuevaAct.putExtras(datos); 
	     		startActivity(NuevaAct);
	     	} 
		});
		
		btnHisto2.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {  
		    		final int Nivel = 2;
			    	final Bundle datos = new Bundle();
			    	datos.putInt("pNivel", Nivel);
		    		 
		     		Intent NuevaAct;
		     		NuevaAct = new Intent(ElegiJuegoNivelLENGUA.this, Cuentos.class);
		    		NuevaAct.putExtras(datos); 
		     		startActivity(NuevaAct);
	     	} 
		});
		
		btnHisto3.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {  
		    		final int Nivel = 3;
			    	final Bundle datos = new Bundle();
			    	datos.putInt("pNivel", Nivel);
		    		 
		     		Intent NuevaAct;
		     		NuevaAct = new Intent(ElegiJuegoNivelLENGUA.this, Cuentos.class);
		    		NuevaAct.putExtras(datos); 
		     		startActivity(NuevaAct);
	     	} 
		});
		
		btnHisto4.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {  
		    		final int Nivel = 4;
			    	final Bundle datos = new Bundle();
			    	datos.putInt("pNivel", Nivel);
		    		 
		     		Intent NuevaAct;
		     		NuevaAct = new Intent(ElegiJuegoNivelLENGUA.this, Cuentos.class);
		    		NuevaAct.putExtras(datos); 
		     		startActivity(NuevaAct);
	     	} 
		});
		
		btnAhorcadoInfo.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) { 
	    		 
	    		 CrearMensaje("El objetivo del juego es adivinar la palabra que se encuentra escondida arriesgando letras. Si ellas son correctas, se irán agregando en la palabra. Si no lo son, se sumará una parte del cuerpo del hombre.\nCuando te equivocás varias veces, aparecerá una pista que te ayudará a adivinar. Una vez que esté completo, si te vuelves a equivocar, él será ahorcado. Pero si adivinás la palabra antes, ganarás el juego!\n\nEl nivel 1 cuenta con sustantivos comunes, el nivel 2 con verbos, el nivel 3 con adjetivos, y el nivel 4 es para practicar de todo lo anterior.", "¿Qué hay que hacer?", "Ok");
	    		 
	     	}
		});
		
		btnCuentosInfo.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) { 
	    		 
	    		 CrearMensaje("El objetivo de este juego es que puedas armar tu propia historia, eligiendo las opciones que se te dan.\nDos de estas opciones son correctas, y las otras dos incorrectas.\nTendrás que descubirlo vos mismo!", "¿Qué hay que hacer?", "Ok");
	    		 
	     	}
		});
	}
	
	public void CrearMensaje(final String Mensaje, final String strTitulo, final String PositiveButton){

		AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

		dlgAlert.setMessage(Mensaje);
		dlgAlert.setTitle(strTitulo);
		dlgAlert.setPositiveButton(PositiveButton, null);
		dlgAlert.create();
		dlgAlert.show();  
	}
		
}
