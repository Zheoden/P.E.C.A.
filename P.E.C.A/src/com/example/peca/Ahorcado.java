package com.example.peca;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast; 

public class Ahorcado extends Activity {
	
	
	public static void hideSoftKeyboard(Activity activity) {
	    InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
	    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}
	public void setupUI(View view) {

	    //Set up touch listener for non-text box views to hide keyboard.
	    if(!(view instanceof EditText)) {

	        view.setOnTouchListener(new OnTouchListener() {

	            public boolean onTouch(View v, MotionEvent event) {
	                hideSoftKeyboard(Ahorcado.this);
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
	
	

	int CantIntentos;
	int EstanMal = 0;
	int EstanBien = 0;
	int ContRepetidasMal;
	int ContRepetidasBien;
	int NumRandom = Randomizador();
	int vidas = 3;
	int CantLetras;
	int NivelGeneral;
	
	final String[] Letras = new String[10];
	final String[] LetrasQueNoVan = new String[26];
	final String[] LetrasSI = new String[26];	
	
	 @Override
	 public boolean dispatchKeyEvent(KeyEvent event) {
		 if(event.getKeyCode() == 66)
		 {
			 final EditText etLetraIngresada = (EditText)findViewById(R.id.etLetra);
			 final TextView etPista = (TextView)findViewById(R.id.txtPista);
			 final TextView txtLetrasQueNoVan = (TextView)findViewById(R.id.txtLetrasQueNoVan);
			 final TextView txtIntentos = (TextView)findViewById(R.id.txtIntentos);
			 final TextView[] TodosTxt = InicializarTextView();
			 final Button btnArriesgar = (Button)findViewById(R.id.btnArriesgar);
			 final ImageView[] Pictures = CargarPics();


			 int band = 0;
			 boolean LetraMal;
			 boolean LetraBien;

			 String LetraIngresada = etLetraIngresada.getText().toString();
			 LetraIngresada = LetraIngresada.toLowerCase();

			 if(LetraIngresada.length() > 1)
			 {
				 Context context = getApplicationContext();
				 CharSequence text = "No se puede ingresar mas de una letra. Ingrese nuevamente";
				 int duration = Toast.LENGTH_SHORT;
				 Toast toast = Toast.makeText(context, text, duration);
				 toast.show();

				 etLetraIngresada.setText("");
			 }


			 if(LetraIngresada.length() == 1)
			 {
				 char CharLetra = LetraIngresada.charAt(0);
				 if(Character.isLetter(CharLetra))
				 {
					 for (int i = 0; i < CantLetras; i++) {	
						 if(LetraIngresada.equals(Letras[i]))
						 {					
							 TodosTxt[i].setText(Letras[i] + " ");
							 if(band == 0)
							 {							
								 band = 1;
							 }

							 LetraBien = ConsultarLetraBien(etLetraIngresada.getText().toString(), LetrasSI);
							 if(LetraBien == false)
							 {
								 ContRepetidasBien = ContRepetidasBien + 1;
								 LetrasSI[ContRepetidasBien] = LetraIngresada;


								 for (int j = 0; j < CantLetras; j++) {	
									 if(LetraIngresada.equals(Letras[j]))
										 EstanBien = EstanBien + 1;
									 {
									 }
								 }
								 CantIntentos = CantIntentos + 1;
							 }
						 }
					 }

					 if(band == 0)
					 {

						 if(EstanMal == 4)
						 {
							 String Pista = DevolverPista(NumRandom);
							 etPista.setText("Pista: " + Pista);
							 etPista.setVisibility(View.VISIBLE);
						 }

						 if(EstanMal == 6)
						 {
							 Context context = getApplicationContext();
							 CharSequence text = "Perdiste el juego!! Intentalo otra vez apretando 'Reiniciar!'";
							 int duration = Toast.LENGTH_SHORT;
							 Toast toast = Toast.makeText(context, text, duration);
							 toast.show();

							 etLetraIngresada.setEnabled(false);
							 btnArriesgar.setEnabled(false);

						 }


						 LetraMal = ConsultarLetraMal(etLetraIngresada.getText().toString(), LetrasQueNoVan);
						 if(LetraMal == false)
						 {
							 EstanMal = EstanMal + 1;
							 ContRepetidasMal = ContRepetidasMal + 1;
							 CantIntentos = CantIntentos + 1;
							 LetrasQueNoVan[ContRepetidasMal] = LetraIngresada;
							 String LoQueYaEstaba = txtLetrasQueNoVan.getText().toString();
							 txtLetrasQueNoVan.setText(LoQueYaEstaba + LetraIngresada + ", ");
							 Pictures[EstanMal].setVisibility(View.VISIBLE);
						 }

					 }

					 if(EstanBien >= CantLetras)
					 {

						 final Bundle datos = new Bundle();
						 datos.putInt("pNivel", NivelGeneral);
						 datos.putString("pJuego", "Ahorcado");
						 
						 Intent NuevaAct;
						 NuevaAct = new Intent(Ahorcado.this, Ganaste.class);
						 NuevaAct.putExtras(datos); 
						 startActivity(NuevaAct);

						 etLetraIngresada.setEnabled(false);
						 btnArriesgar.setEnabled(false);
					 }

					 txtIntentos.setText(String.valueOf(CantIntentos));
					 etLetraIngresada.setText("");
				 }
				 else
				 {
					 Context context = getApplicationContext();
					 CharSequence text = "Solo es posible ingresar letras al juego.";
					 int duration = Toast.LENGTH_SHORT;
					 Toast toast = Toast.makeText(context, text, duration);
					 toast.show();
				 }
			 }
			 Handler mHandler= new Handler();
			 mHandler.post(new Runnable() {      
				 public void run() {
					 InputMethodManager inputMethodManager =  (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
					 inputMethodManager.toggleSoftInputFromWindow(etLetraIngresada.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
					 etLetraIngresada.requestFocus();
				 }
			 });
		 } 
		 return super.dispatchKeyEvent(event);
	 }

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		 setContentView(R.layout.ahorcado);
		 setupUI(findViewById(R.id.AhorcadoView));
		 getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

		 final EditText etLetraIngresada = (EditText)findViewById(R.id.etLetra);
		 final TextView[] TodosTxt = InicializarTextView();

		 final String Palabra = DevolverPalabra(NumRandom);
		 CantLetras = Palabra.length();

		 //		Context context = getApplicationContext();
		 //		CharSequence text = "La palabra tiene " + CantLetras + " letras.";
		 //		int duration = Toast.LENGTH_SHORT;
		 //		Toast toast = Toast.makeText(context, text, duration);
		 //		toast.show();


		 for (int i = 0; i < CantLetras; i++) {	
			 Letras[i] = Palabra.substring(i, i+1);
			 TodosTxt[i].setVisibility(View.VISIBLE);
		 }
		 final Button btnArriesgar = (Button)findViewById(R.id.btnArriesgar);  

		 etLetraIngresada.setOnFocusChangeListener(new OnFocusChangeListener() {
			 @Override
			 public void onFocusChange(View v, boolean hasFocus) {
				 etLetraIngresada.post(new Runnable() {
					 @Override
					 public void run() {
						 InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
						 imm.showSoftInput(etLetraIngresada, InputMethodManager.SHOW_IMPLICIT);
					 }
				 });
			 }
		 });
		 etLetraIngresada.requestFocus();


		 btnArriesgar.setOnClickListener(new OnClickListener() {
			 public void onClick(View v) {

				 AlertDialog.Builder alertDialog = new AlertDialog.Builder(Ahorcado.this);
				 alertDialog.setTitle("ARRIESGAR");
				 alertDialog.setMessage("Ingrese la palabra correcta");
				 final EditText PalabraFinal = new EditText(Ahorcado.this);  
				 LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
						 LinearLayout.LayoutParams.MATCH_PARENT,
						 LinearLayout.LayoutParams.MATCH_PARENT);
				 PalabraFinal.setLayoutParams(lp);
				 alertDialog.setView(PalabraFinal);
				 alertDialog.setPositiveButton("Aceptar",
						 new DialogInterface.OnClickListener() {
					 public void onClick(DialogInterface dialog,int which) {

						 int band = 0;

						 String PalabraDialog = PalabraFinal.getText().toString();
						 PalabraDialog = PalabraDialog.toLowerCase();

						 if(PalabraDialog.equals(""))
						 {
							 Context context = getApplicationContext();
							 CharSequence text ="Ingresar texto";
							 int duration = Toast.LENGTH_SHORT;
							 Toast toast = Toast.makeText(context, text, duration);
							 toast.show();
						 }
						 else
						 {
							 if(Palabra.equals(PalabraDialog))
							 {
								 band = 1;
							 }

							 if(band == 1)
							 {
								 
								 Context context = getApplicationContext();
								 for (int i = 0; i < CantLetras; i++)
								 {					
									 TodosTxt[i].setText(Letras[i] + " ");
								 }
								 final Bundle datos = new Bundle();
								 datos.putInt("pNivel", NivelGeneral);
								 datos.putString("pJuego", "Ahorcado");

								 Intent NuevaAct;
								 NuevaAct = new Intent(Ahorcado.this, Ganaste.class);
								 NuevaAct.putExtras(datos); 
								 startActivity(NuevaAct);
								 
								

								 etLetraIngresada.setEnabled(false);
								 btnArriesgar.setEnabled(false);
							 }
							 else
							 {
								 vidas = vidas - 1;

								 if(vidas == 0)
								 {
									 Context context = getApplicationContext();
									 CharSequence text = "Perdiste el juego!! Intentalo otra vez apretando 'Reiniciar!'";
									 int duration = Toast.LENGTH_SHORT;
									 Toast toast = Toast.makeText(context, text, duration);
									 toast.show();

									 etLetraIngresada.setEnabled(false);
									 btnArriesgar.setEnabled(false);
								 }
								 else
								 {
									 Context context = getApplicationContext();
									 CharSequence text ="La palabra es incorrecta, segui intentando! Te quedan " + vidas  + " vidas." ;
									 int duration = Toast.LENGTH_SHORT;
									 Toast toast = Toast.makeText(context, text, duration);
									 toast.show();

									 etLetraIngresada.setText("");
								 }

							 }
						 }
					 }
				 });
				 alertDialog.setNegativeButton("Cancelar",
						 new DialogInterface.OnClickListener() {
					 public void onClick(DialogInterface dialog, int which) {
						 dialog.cancel();
					 }
				 });
				 alertDialog.show();
			 }
		 });

		 Button btnVolver = (Button)findViewById(R.id.btnVolver);
		 btnVolver.setOnClickListener(new OnClickListener() {
			 public void onClick(View v) {
				 Intent ActividadAInvocar;
				 ActividadAInvocar = new Intent(Ahorcado.this, ElegiJuegoNivelLENGUA.class);
				 startActivity(ActividadAInvocar);
				 overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

			 }
		 });

		 Button btnReiniciar = (Button)findViewById(R.id.btnReiniciar); 
		 btnReiniciar.setOnClickListener(new OnClickListener() {
			 public void onClick(View v) {

				 finish();
				 startActivity(getIntent());

			 }
		 });
	 }

	 public TextView[] InicializarTextView()
	 {
		 TextView[] TodosTxt = new TextView[10];

		 TodosTxt[0] = (TextView)findViewById(R.id.txt0);
		 TodosTxt[1] = (TextView)findViewById(R.id.txt1);
		 TodosTxt[2] = (TextView)findViewById(R.id.txt2);
		 TodosTxt[3] = (TextView)findViewById(R.id.txt3);
		 TodosTxt[4] = (TextView)findViewById(R.id.txt4);
		 TodosTxt[5] = (TextView)findViewById(R.id.txt5);
		 TodosTxt[6] = (TextView)findViewById(R.id.txt6);
		 TodosTxt[7] = (TextView)findViewById(R.id.txt7);
		 TodosTxt[8] = (TextView)findViewById(R.id.txt8);
		 TodosTxt[9] = (TextView)findViewById(R.id.txt9);

		 return TodosTxt;

	 }
	 public boolean ConsultarLetraMal(String etLetraIngresada, String[] LetrasQueNoVan)
	 {
		 boolean YaEsta = false;

		 for (int i = 0; i < 26; i++) {	
			 if(etLetraIngresada.equals(LetrasQueNoVan[i]))
			 {
				 YaEsta = true;	
			 }	
		 }

		 return YaEsta;
	 }

	 public boolean ConsultarLetraBien(String etLetraIngresada, String[] LetrasQueYaEstan)
	 {
		 boolean YaEsta = false;

		 for (int i = 0; i < 26; i++) {	
			 if(etLetraIngresada.equals(LetrasQueYaEstan[i]))
			 {
				 YaEsta = true;
			 }	
		 }

		 return YaEsta;
	 }


	 public ImageView[] CargarPics()
	 {
		 ImageView[] Pictures = new ImageView[8];

		 Pictures[0] = (ImageView)findViewById(R.id.picture0);
		 Pictures[1] = (ImageView)findViewById(R.id.picture1);
		 Pictures[2] = (ImageView)findViewById(R.id.picture2);
		 Pictures[3] = (ImageView)findViewById(R.id.picture3);
		 Pictures[4] = (ImageView)findViewById(R.id.picture4);
		 Pictures[5] = (ImageView)findViewById(R.id.picture5);
		 Pictures[6] = (ImageView)findViewById(R.id.picture6);
		 Pictures[7] = (ImageView)findViewById(R.id.picture7);
		 //		
		 //		Pictures[0].setVisibility(View.VISIBLE);
		 //		Pictures[1].setVisibility(View.INVISIBLE);
		 //		Pictures[2].setVisibility(View.INVISIBLE);
		 //		Pictures[3].setVisibility(View.INVISIBLE);
		 //		Pictures[4].setVisibility(View.INVISIBLE);
		 //		Pictures[5].setVisibility(View.INVISIBLE);
		 //		Pictures[6].setVisibility(View.INVISIBLE);
		 //		Pictures[7].setVisibility(View.INVISIBLE);

		 return Pictures;

	 }

	 public String DevolverPalabra(int NumRandom)
	 {
		 //En este nivel los objetos solamente son sustantivos.

		 String Palabra = null;
		 String[] VecPalabras = new String[30];

		 Bundle datos = this.getIntent().getExtras();
		 final int Nivel = datos.getInt("pNivel");
		 NivelGeneral = Nivel;


		 //		int Nivel = 1;

		 if(Nivel == 1)
		 {
			 VecPalabras = getResources().getStringArray(R.array.palabra1); 
		 }
		 if(Nivel == 2)
		 {
			 VecPalabras = getResources().getStringArray(R.array.palabra2); 
		 }
		 if(Nivel == 3)
		 {
			 VecPalabras = getResources().getStringArray(R.array.palabra3); 
		 }
		 if(Nivel == 4)
		 {
			 VecPalabras = getResources().getStringArray(R.array.palabra4); 
		 }



		 //		VecPalabras[0] = "auto";
		 //		VecPalabras[1] = "casa";
		 //		VecPalabras[2] = "caramelo";
		 //		VecPalabras[3] = "perro";
		 //		VecPalabras[4] = "campera";
		 //		VecPalabras[5] = "elefante";
		 //		VecPalabras[6] = "musica";
		 //		VecPalabras[7] = "celular";
		 //		VecPalabras[8] = "remera";
		 //		VecPalabras[9] = "mochila";
		 //		VecPalabras[10] = "bicicleta";
		 //		VecPalabras[11] = "puerta";
		 //		VecPalabras[12] = "pulcera";
		 //		VecPalabras[13] = "martillo";
		 //		VecPalabras[14] = "maestra";
		 //		VecPalabras[15] = "billetera";
		 //		VecPalabras[16] = "peca";
		 //		VecPalabras[17] = "milanesa";
		 //		VecPalabras[18] = "carta";
		 //		VecPalabras[19] = "anillo";


		 Palabra = VecPalabras[NumRandom];

		 return Palabra;

	 }

	 public String DevolverPista(int NumRandom)
	 {
		 String Pista = null;
		 String[] VecPista = new String[30];

		 Bundle datos = this.getIntent().getExtras();
		 final int Nivel = datos.getInt("pNivel");

		 //		String Nivel = "1";

		 if(Nivel == 1)
		 {
			 VecPista = getResources().getStringArray(R.array.pista1); 
		 }
		 if(Nivel == 2)
		 {
			 VecPista = getResources().getStringArray(R.array.pista2); 
		 }
		 if(Nivel == 3)
		 {
			 VecPista = getResources().getStringArray(R.array.pista3); 
		 }
		 if(Nivel == 4)
		 {
			 VecPista = getResources().getStringArray(R.array.pista4); 
		 }

		 //		
		 //		VecPista[0] = "Vehiculo que hay en las calles";
		 //		VecPista[1] = "Lugar en donde todos vivimos";
		 //		VecPista[2] = "Pequeño dulce que comen los mas chicos";
		 //		VecPista[3] = "Mascota que muchos tenemos en nuestra casa";
		 //		VecPista[4] = "Abrigo que nos ponemos cuando hace frio";
		 //		VecPista[5] = "Animal muy grande que tiene una trompa";
		 //		VecPista[6] = "Las canciones que escuchamos son...";
		 //		VecPista[7] = "Telefono movil";
		 //		VecPista[8] = "Lo que nos ponemos abajo del buzo";
		 //		VecPista[9] = "Lo que usamos para guardar las cosas de la escuela";
		 //		VecPista[10] = "Vehiculo que tiene dos ruedas y pedales";
		 //		VecPista[11] = "Aquello que tenemos que abrir para entrar";
		 //		VecPista[12] = "Objetos que nos ponemos en las muñecas";
		 //		VecPista[13] = "Se utiliza para martillar";
		 //		VecPista[14] = "La mujer que nos enseña en la escuela";
		 //		VecPista[15] = "Donde se guarda toda la plata";
		 //		VecPista[16] = "El nombre de este juego";
		 //		VecPista[17] = "Aquello que solemos comer con papas fritas";
		 //		VecPista[18] = "Algo que se envia por correo";
		 //		VecPista[19] = "Lo que uno se pone el el dedo al casarse";
		 //		

		 Pista = VecPista[NumRandom];

		 return Pista;

	 }

	 public String DevolverPalabra2(int NumRandom)
	 {
		 //En este nivel los objetos son sustantivos y verbos.

		 String Palabra = null;
		 String[] VecPalabras = new String[30];


		 Palabra = VecPalabras[NumRandom];

		 return Palabra;

	 }
	 public int Randomizador()
	 {
		 Random r = new Random();
		 int i1  = r.nextInt(29 - 0) + 0;
		 Log.d("numero", String.valueOf(i1));

		 return i1;
	 }
}




