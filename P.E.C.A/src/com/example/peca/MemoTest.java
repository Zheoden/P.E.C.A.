package com.example.peca;

import java.util.Random;

import com.example.peca.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MemoTest extends Activity {

	public int Prueba = 8;
	public int Seleccionado = 0;
	public int ContAciertos = 0;
	public int ContIntentos = 0;
	public int Bandera = 1;
	public String[] Validacion = CargarStrings();
	int Nivel;
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        setContentView(R.layout.memotest);  

        Button Reiniciar = (Button)findViewById(R.id.Reiniciar);        
        Reiniciar.setOnClickListener(new OnClickListener() {        	
        	public void onClick(View v) {   		

        		finish();
        		startActivity(getIntent());
        	} 
        } );
        
        Button Volver = (Button)findViewById(R.id.Volver);
        Volver.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent ActividadAInvocar;
	    		ActividadAInvocar = new Intent(MemoTest.this, ElegiJuegoNivelMATE.class);
	    		startActivity(ActividadAInvocar);
	    		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	    		
				
			}
		});
        
        final Button SiguienteNivel = (Button)findViewById(R.id.SiguienteNivelMemo);
        SiguienteNivel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (Nivel < 4) {
					Nivel += 1;
		    		final Bundle datos = new Bundle();
		    		datos.putInt("pNivel", Nivel);
		    		 
//		    		Intent slideactivity = new Intent(MemoTest.this, MemoTest.class);
//		    		slideactivity.putExtras(datos); 
//		    		startActivity(slideactivity);
//		    		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);   

		    		Intent NuevaAct;
		     		NuevaAct = new Intent(MemoTest.this, MemoTest.class);
		     		NuevaAct.putExtras(datos); 
		     		startActivity(NuevaAct);
				}
				else
				{
					Mensaje("Ya esta en el ultimo nivel del memotest, Suerte!","No hay mas niveles","Aceptar");
					SiguienteNivel.setEnabled(false);
					
				}

			}
		});
        
                	JuegoMemoTest();                	        
	} 
   
        
  		public void JuegoMemoTest(){
  		    Bundle datos = this.getIntent().getExtras();
  			Nivel = datos.getInt("pNivel");
  			final int[] Fotos = Randomizador(Validacion);
			final Button[] Botones = CargarBotones();
			final boolean[] VecBoolean = CargarVector();
  			for (int i = 0; i < 16; i++) {	                	             		
  				final int asd = i;
  				//asd es i, Repetitiva
  				Botones[i].setOnClickListener(new View.OnClickListener() {
		                public void onClick(View v) {
		                	Botones[asd].setBackgroundResource(Fotos[asd]);
		                	if(Bandera == 1)
		                	{
		                		Bandera = 2;
		                		Seleccionado = asd;
		                	} 
		                	else
		                	{
		                		Bloquear(VecBoolean, Botones);
		                		Bandera = 1;		                		
		                		if(Validacion[Seleccionado] == Validacion[asd] && Seleccionado != asd )
		                		{
		                			VecBoolean[Seleccionado] = false;
		                			VecBoolean[asd] = false;
		                			if(ContAciertos <= 7)
		                			{
		                				Desbloquear(VecBoolean, Botones);
		                				ContIntentos += 1;
		                				ContAciertos += 1;
		                				if(ContAciertos == 8)
				                		{
				                			//Mensaje("GANASTE!!!!!!!", "Muy Bien!!!", "Aceptar");
				                			final Bundle datos = new Bundle();
		                			    	datos.putInt("pNivel", Nivel);
		                			    	datos.putString("pJuego", "Memotest");
		                		    		 
		                		     		Intent NuevaAct;
		                		     		NuevaAct = new Intent(MemoTest.this, Ganaste.class);
		                		    		NuevaAct.putExtras(datos); 
		                		     		startActivity(NuevaAct);
				                		}
		                				else
		                				{
		                					if(ContAciertos == 7)
		                					{
		                						Mensaje("Bien, adivinaste!\nAhora te falta 1 mas.", "Bien!","Aceptar");
		                					}
		                					else
				                			{
				                				int asd = Prueba - ContAciertos;
				                				Mensaje("Bien, adivinaste!\nAhora te faltan " + asd + " mas.", "Bien!","Aceptar");
				                			}
		                				}
		                			}
		                		}
		                		if(Seleccionado == asd)
		                		{
		                			Bandera = 2;
		                			Desbloquear(VecBoolean, Botones);
		                		}
		                		
		                		if(Validacion[Seleccionado] != Validacion[asd])
		                		{
		                			Handler Delay = new Handler();
		                			Delay.postDelayed(new Runnable(){
		                			    public void run(){
		                			    	Botones[asd].setBackgroundResource(R.drawable.mt_fondocartas);
		                			    	Botones[Seleccionado].setBackgroundResource(R.drawable.mt_fondocartas);
				                			Desbloquear(VecBoolean, Botones);
				                			ContIntentos += 1;
		                			    }
		                			}, 1500);
		                			}
		                		}
		                		
		                }
		                });
  				}
  			}

  		public int[] CargarFotos(){
  			
  			int[] Fotos = new int[16];
  			switch (Nivel) {
			case 1:
				Fotos[0] = R.drawable.mt1_cuenta;
	  			Fotos[1] = R.drawable.mt1_resultado;
	  			Fotos[2] = R.drawable.mt1_cuenta2;
	  			Fotos[3] = R.drawable.mt1_resultado2;
	  			Fotos[4] = R.drawable.mt1_cuenta3;
	  			Fotos[5] = R.drawable.mt1_resultado3;
	  			Fotos[6] = R.drawable.mt1_cuenta4;
	  			Fotos[7] = R.drawable.mt1_resultado4;
	  			Fotos[8] = R.drawable.mt1_cuenta5;
	  			Fotos[9] = R.drawable.mt1_resultado5;
	  			Fotos[10] = R.drawable.mt1_cuenta6;
	  			Fotos[11] = R.drawable.mt1_resultado6;
	  			Fotos[12] = R.drawable.mt1_cuenta7;
	  			Fotos[13] = R.drawable.mt1_resultado7;
	  			Fotos[14] = R.drawable.mt1_cuenta8;
	  			Fotos[15] = R.drawable.mt1_resultado8;
				break;
			case 2:
				Fotos[0] = R.drawable.mt2_cuenta;
	  			Fotos[1] = R.drawable.mt2_resultado;
	  			Fotos[2] = R.drawable.mt2_cuenta2;
	  			Fotos[3] = R.drawable.mt2_resultado2;
	  			Fotos[4] = R.drawable.mt2_cuenta3;
	  			Fotos[5] = R.drawable.mt2_resultado3;
	  			Fotos[6] = R.drawable.mt2_cuenta4;
	  			Fotos[7] = R.drawable.mt2_resultado4;
	  			Fotos[8] = R.drawable.mt2_cuenta5;
	  			Fotos[9] = R.drawable.mt2_resultado5;
	  			Fotos[10] = R.drawable.mt2_cuenta6;
	  			Fotos[11] = R.drawable.mt2_resultado6;
	  			Fotos[12] = R.drawable.mt2_cuenta7;
	  			Fotos[13] = R.drawable.mt2_resultado7;
	  			Fotos[14] = R.drawable.mt2_cuenta8;
	  			Fotos[15] = R.drawable.mt2_resultado8;	
				break;
			case 3:
				Fotos[0] = R.drawable.mt3_cuenta;
	  			Fotos[1] = R.drawable.mt3_resultado;
	  			Fotos[2] = R.drawable.mt3_cuenta2;
	  			Fotos[3] = R.drawable.mt3_resultado2;
	  			Fotos[4] = R.drawable.mt3_cuenta3;
	  			Fotos[5] = R.drawable.mt3_resultado3;
	  			Fotos[6] = R.drawable.mt3_cuenta4;
	  			Fotos[7] = R.drawable.mt3_resultado4;
	  			Fotos[8] = R.drawable.mt3_cuenta5;
	  			Fotos[9] = R.drawable.mt3_resultado5;
	  			Fotos[10] = R.drawable.mt3_cuenta6;
	  			Fotos[11] = R.drawable.mt3_resultado6;
	  			Fotos[12] = R.drawable.mt3_cuenta7;
	  			Fotos[13] = R.drawable.mt3_resultado7;
	  			Fotos[14] = R.drawable.mt3_cuenta8;
	  			Fotos[15] = R.drawable.mt3_resultado8;
				
				break;
			case 4:
				Fotos[0] = R.drawable.mt4_cuenta;
	  			Fotos[1] = R.drawable.mt4_resultado;
	  			Fotos[2] = R.drawable.mt4_cuenta2;
	  			Fotos[3] = R.drawable.mt4_resultado2;
	  			Fotos[4] = R.drawable.mt4_cuenta3;
	  			Fotos[5] = R.drawable.mt4_resultado3;
	  			Fotos[6] = R.drawable.mt4_cuenta4;
	  			Fotos[7] = R.drawable.mt4_resultado4;
	  			Fotos[8] = R.drawable.mt4_cuenta5;
	  			Fotos[9] = R.drawable.mt4_resultado5;
	  			Fotos[10] = R.drawable.mt4_cuenta6;
	  			Fotos[11] = R.drawable.mt4_resultado6;
	  			Fotos[12] = R.drawable.mt4_cuenta7;
	  			Fotos[13] = R.drawable.mt4_resultado7;
	  			Fotos[14] = R.drawable.mt4_cuenta8;
	  			Fotos[15] = R.drawable.mt4_resultado8;
				break;

			default:
				Fotos[0] = R.drawable.mt1_cuenta;
	  			Fotos[1] = R.drawable.mt1_resultado;
	  			Fotos[2] = R.drawable.mt1_cuenta2;
	  			Fotos[3] = R.drawable.mt1_resultado2;
	  			Fotos[4] = R.drawable.mt1_cuenta3;
	  			Fotos[5] = R.drawable.mt1_resultado3;
	  			Fotos[6] = R.drawable.mt1_cuenta4;
	  			Fotos[7] = R.drawable.mt1_resultado4;
	  			Fotos[8] = R.drawable.mt1_cuenta5;
	  			Fotos[9] = R.drawable.mt1_resultado5;
	  			Fotos[10] = R.drawable.mt1_cuenta6;
	  			Fotos[11] = R.drawable.mt1_resultado6;
	  			Fotos[12] = R.drawable.mt1_cuenta7;
	  			Fotos[13] = R.drawable.mt1_resultado7;
	  			Fotos[14] = R.drawable.mt1_cuenta8;
	  			Fotos[15] = R.drawable.mt1_resultado8;
				break;
			}			
  			return Fotos;  			
  		}
  		
  		public String[] CargarStrings()
  		{
  			String[] Validacion = new String[16];
  			Validacion[0] = "Pareja1";
  			Validacion[1] = "Pareja1";
  			Validacion[2] = "Pareja2";
  			Validacion[3] = "Pareja2";
  			Validacion[4] = "Pareja3";
  			Validacion[5] = "Pareja3";
  			Validacion[6] = "Pareja4";
  			Validacion[7] = "Pareja4";
  			Validacion[8] = "Pareja5";
  			Validacion[9] = "Pareja5";
  			Validacion[10] = "Pareja6";
  			Validacion[11] = "Pareja6";
  			Validacion[12] = "Pareja7";
  			Validacion[13] = "Pareja7";
  			Validacion[14] = "Pareja8";
  			Validacion[15] = "Pareja8";
  			
  			return Validacion;
  		}

  		public void Mensaje(final String Mensaje, final String Titulo, final String PositiveButton ){
  	    	AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

  			dlgAlert.setMessage(Mensaje);
  			dlgAlert.setTitle(Titulo);
  			dlgAlert.setPositiveButton(PositiveButton, null);
  			dlgAlert.setCancelable(true);
  			dlgAlert.create().show();
  		}
  		
  		public boolean[] CargarVector()
  		{
  			boolean[] VecBoolean = new boolean[16];
  			for(int i = 0; i < 16; i++)
  			{
  				VecBoolean[i] = true;  				
  			}
  			return VecBoolean;
  		}
  		
        public Button[] CargarBotones() {
            Button[] b = new Button[16]; 
            b[0] = (Button) findViewById(R.id.Button00);
            b[1] = (Button) findViewById(R.id.Button01);
            b[2] = (Button) findViewById(R.id.Button02);
            b[3] = (Button) findViewById(R.id.Button03);
            b[4] = (Button) findViewById(R.id.Button04);
            b[5] = (Button) findViewById(R.id.Button05);
            b[6] = (Button) findViewById(R.id.Button06);
            b[7] = (Button) findViewById(R.id.Button07);
            b[8] = (Button) findViewById(R.id.Button08); 
            b[9] = (Button) findViewById(R.id.Button09); 
            b[10] = (Button) findViewById(R.id.Button10); 
            b[11] = (Button) findViewById(R.id.Button11); 
            b[12] = (Button) findViewById(R.id.Button12); 
            b[13] = (Button) findViewById(R.id.Button13);
            b[14] = (Button) findViewById(R.id.Button14); 
            b[15] = (Button) findViewById(R.id.Button15);         
            return b;
        }
        
        public void Bloquear(boolean[] VecBoolean, Button[] VecBotones)

        {
        	for(int i = 0; i < 16; i++)
        	{
        		if(VecBoolean[i] == true)
        		{
        			VecBotones[i].setEnabled(false);
        		}
        	}
        }
        
        public void Desbloquear(boolean[] VecBoolean , Button[] VecBotones)
        {
        	for(int i = 0; i < 16; i++)
        	{
        		if(VecBoolean[i] == true)
        		{
        			VecBotones[i].setEnabled(true);
        		}
        	}
        }
        
        public int[] Randomizador(String[] VecStrings){
        	int[] VecInt = new int[16];
        	VecInt = CargarFotos();
    		Random rnd = new Random();
    		rnd.nextInt();
        	for(int i = 0; i < VecInt.length; i++)
        	{
        		int aCambiar = i + rnd.nextInt(VecInt.length - i);
        		CambiarInt(VecInt, i, aCambiar);  
        		CambiarString(VecStrings, i, aCambiar);
        	}
            return VecInt;
        }
        
        public void CambiarInt(int[] Vector, int Pos, int Cambiar) {
            int helper = Vector[Pos];
            Vector[Pos] = Vector[Cambiar];
            Vector[Cambiar] = helper;
          }
        
        public void CambiarString(String[] Vector, int Pos, int Cambiar) {
            String helper = Vector[Pos];
            Vector[Pos] = Vector[Cambiar];
            Vector[Cambiar] = helper;
          }

}