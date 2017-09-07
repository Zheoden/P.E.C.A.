package com.example.peca;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ElegiJuegoNivelMATE extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_elegir_juego_nivel_mate);
		
		Button btnMemoInfo = (Button)findViewById(R.id.btnMemoInfo);
		Button btnCascadaInfo = (Button)findViewById(R.id.btnCascadaInfo);
		Button btnMemo1 = (Button)findViewById(R.id.btnMemo1);
		Button btnMemo2 = (Button)findViewById(R.id.btnMemo2);
		Button btnMemo3 = (Button)findViewById(R.id.btnMemo3);
		Button btnMemo4 = (Button)findViewById(R.id.btnMemo4);
		Button btnCas1 = (Button)findViewById(R.id.btnCas1);
		Button btnCas2 = (Button)findViewById(R.id.btnCas2);
		Button btnCas3 = (Button)findViewById(R.id.btnCas3);
		Button btnCas4 = (Button)findViewById(R.id.btnCas4);
		Button Volver = (Button)findViewById(R.id.btnHisto1);
		
		Volver.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent ActAInvocar;
	    		ActAInvocar = new Intent(ElegiJuegoNivelMATE.this,ElegiElArea.class);
	    		startActivity(ActAInvocar);
			}
		});
		
		btnMemo1.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {
	    		final int Nivel = 1;
	    		final Bundle datos = new Bundle();
	    		datos.putInt("pNivel", Nivel);
	    		 
	     		Intent NuevaAct;
	     		NuevaAct = new Intent(ElegiJuegoNivelMATE.this, MemoTest.class);
	     		NuevaAct.putExtras(datos); 
	     		startActivity(NuevaAct);
	     	} 
		});
		
		btnMemo2.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {
	    		final int Nivel = 2;
	    		final Bundle datos = new Bundle();
	    		datos.putInt("pNivel", Nivel);
	    		 
	     		Intent NuevaAct;
	     		NuevaAct = new Intent(ElegiJuegoNivelMATE.this, MemoTest.class);
	     		NuevaAct.putExtras(datos); 
	     		startActivity(NuevaAct);
	     	} 
		});
		
		btnMemo3.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {
	    		final int Nivel = 3;
	    		final Bundle datos = new Bundle();
	    		datos.putInt("pNivel", Nivel);
	    		 
	     		Intent NuevaAct;
	     		NuevaAct = new Intent(ElegiJuegoNivelMATE.this, MemoTest.class);
	     		NuevaAct.putExtras(datos); 
	     		startActivity(NuevaAct);
	     	} 
		});
		
		btnMemo4.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {
	    		final int Nivel = 4;
	    		final Bundle datos = new Bundle();
	    		datos.putInt("pNivel", Nivel);
	    		 
	     		Intent NuevaAct;
	     		NuevaAct = new Intent(ElegiJuegoNivelMATE.this, MemoTest.class);
	     		NuevaAct.putExtras(datos); 
	     		startActivity(NuevaAct);
	     	} 
		});
		
		btnCas1.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {  
	    		final String Nivel = "1";
		    	final Bundle datos = new Bundle();
		    	datos.putString("pNivel", Nivel);
	    		 
	     		Intent NuevaAct;
	     		NuevaAct = new Intent(ElegiJuegoNivelMATE.this, Golosinas.class);
	    		NuevaAct.putExtras(datos); 
	     		startActivity(NuevaAct);
	     	} 
		});
		
		btnCas2.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {  
	    		final String Nivel = "2";
		    	final Bundle datos = new Bundle();
		    	datos.putString("pNivel", Nivel);
	    		 
	     		Intent NuevaAct;
	     		NuevaAct = new Intent(ElegiJuegoNivelMATE.this, Golosinas.class);
	    		NuevaAct.putExtras(datos); 
	     		startActivity(NuevaAct);
	     	} 
		});
		
		btnCas3.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {  
	    		final String Nivel = "3";
		    	final Bundle datos = new Bundle();
		    	datos.putString("pNivel", Nivel);
	    		 
	     		Intent NuevaAct;
	     		NuevaAct = new Intent(ElegiJuegoNivelMATE.this, Golosinas.class);
	    		NuevaAct.putExtras(datos); 
	     		startActivity(NuevaAct);
	     	} 
		});
		
		btnCas4.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {  
	    		final String Nivel = "4";
		    	final Bundle datos = new Bundle();
		    	datos.putString("pNivel", Nivel);
	    		 
	     		Intent NuevaAct;
	     		NuevaAct = new Intent(ElegiJuegoNivelMATE.this, Golosinas.class);
	    		NuevaAct.putExtras(datos); 
	     		startActivity(NuevaAct);
	     	} 
		});
		
		btnMemoInfo.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) { 
	    		 
	    		 CrearMensaje("El objetivo de este juego es que encuentres las dos cartas que significan lo mismo. En una de ellas encontrarás una cuenta matemática, y en la otra deberás buscar el resultado de la misma.\nSuerte!", "¿Qué hay que hacer?", "Ok");
	    		 
	     	}
		});
		
		btnCascadaInfo.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) { 
	    		 
	    		 CrearMensaje("El objetivo del juego es que logres llenar la canasta con aquellos caramelos cuyos puntajes te permitan llegar a la cifra marcada arriba.\n¡Cuidado! Si te pasas, perdés!\n¡Suerte!", "¿Qué hay que hacer?", "Ok");
	    		 
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
