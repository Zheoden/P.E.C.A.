package com.example.peca;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import com.example.peca.R;
import com.example.peca.R.raw;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class CapaDelJuegoGolosinas extends CCLayer {
	
	//Guarda el contexto del Activity en el que esta corriendo la app. 
	private Context mContext;
	private ArrayList<CCSprite> Chupetines;
	private int TagChupetin;
	private Timer RelojGeneradorDeChupetines;
	
	// Declaro las variables globales
	CCSprite Canasto;
	CGSize Pantalla;
	CCSprite UnChupetin;
	int ptje = 0;
	String[] Golosinas;
	//CCLabel lblTitulo;
	CCLabel lblPuntaje;
	CCLabel lblCuenta=null;
	boolean JuegoEnPausa;
	CGRect AreaJugador;
	String LaGolosina;
	int escol = 0;
	int Cuenta;
	int NivelGeneral = 1;
	CCMenuItemImage BotonPausa;

	String QueGolo = null;

	/* CCSprite UnCaramelo; */
	// Constructor de la clase
	public CapaDelJuegoGolosinas(Context ctx) {
		mContext = ctx;
		
		// Habilito el touch screen
		this.setIsTouchEnabled(true);
		// Obtengo el tamaño de la pantalla del dispositivo
		Pantalla = CCDirector.sharedDirector().displaySize();
		// genero array para los enemigos
		Chupetines = new ArrayList<CCSprite>();		
		//El juego arranca pausado.
		JuegoEnPausa = true;    
		TagChupetin = 0;
		// TagChoco = 0;
		// TagCaram2 = 0;
		// Pongo el canasto del jugador
		PonerCanasto();
		// Pongo el titulo
		PonerTitulos();
		// Pongo boton
		PonerBotones();

		Context context = CCDirector.sharedDirector().getActivity();
		SoundEngine.sharedEngine().playSound(context, R.raw.silly_fun, true);
		

		if (escol == 0) {
			Timer RelojVerificadorColisionEnemigosJugador = new Timer();
			RelojVerificadorColisionEnemigosJugador.schedule(
					tmrVerificadorColisionChupetinesJugador(), 0, 200);
			//200 en lugar de 500 para que detecte siempre las colisiones

		}
		
	}

	void PonerCanasto() {

		// Creo el Sprite para la nave del jugador
		Canasto = CCSprite.sprite("canasto.png");

		// La ubico en el centro de la pantalla abajo
		CGPoint PosicionJugador = new CGPoint();
		PosicionJugador.x = Pantalla.width / 2;
		PosicionJugador.y = Canasto.getContentSize().height / 2;

		Canasto.setPosition(PosicionJugador);

		// La agrego a la escena del juego
		super.addChild(Canasto);
	}

	void PonerTitulos() {

		// Creo el objeto del Label del titulo principal

		//lblTitulo = CCLabel.makeLabel("Cumpleaños Matemático", "Verdana",
				//20);

		// Ubico el centro de la pantalla en horizontal, y arriba de todo
		//CGPoint PosicionTitulo = new CGPoint();
		//PosicionTitulo.x = Pantalla.width / 2;
		//PosicionTitulo.y = Pantalla.height
			//	- lblTitulo.getContentSize().height / 2;

		// Le pongo la posicion al Label
		//lblTitulo.setPosition(PosicionTitulo);

		// Lo agrego a la capa del juego
		//super.addChild(lblTitulo);

		// Creo el Label del contador de ptje
		lblPuntaje = CCLabel.makeLabel("Total:" + ptje, "Verdana", 35);

		// Lo ubico arriba, a la izquierda
		CGPoint PosicionPuntaje = new CGPoint();
		PosicionPuntaje.x = (float) (lblPuntaje.getContentSize().width/1.5);
		//PosicionPuntaje.y = lblTitulo.getPosition().y
			//	- lblTitulo.getContentSize().height;
		
		PosicionPuntaje.y = Pantalla.height - lblPuntaje.getContentSize().height/2;
		

		// Lo ubico
		lblPuntaje.setPosition(PosicionPuntaje);

		// Lo agrego a la escena
		super.addChild(lblPuntaje);

		int ElNro = CuentasRandom();
		lblCuenta = CCLabel.makeLabel("Juntá: " + ElNro, "Verdana", 35);

		CGPoint PosicionCuenta = new CGPoint();
		PosicionCuenta.x = lblPuntaje.getContentSize().width * 4;
		//PosicionCuenta.y = lblTitulo.getPosition().y
			//	- lblTitulo.getContentSize().height;
		PosicionCuenta.y = Pantalla.height - lblCuenta.getContentSize().height/2;

		lblCuenta.setPosition(PosicionCuenta);
		super.addChild(lblCuenta);

		// lblCantidad = CCLabel.makeLabel("Cantidad:" + 0, "Verdana", 14);
		// CGPoint Posicion=new CGPoint();
		// Posicion.x=lblPuntaje.getContentSize().width*2;
		// Posicion.y=lblTitulo.getPosition().y -
		// lblTitulo.getContentSize().height;
		//
		// lblCantidad.setPosition(Posicion);
		// super.addChild(lblCantidad);

	}

	void PonerBotones() {
		// Declaro e instancio el objeto para el boton de pausar el juego
		
		BotonPausa = CCMenuItemImage.item("pinata.png","pinata2.png",this,
				"BotonPausa");

		// Ubico su posicion arriba de todo a la derecha
		CGPoint UbicacionBotonPausa = new CGPoint();
		UbicacionBotonPausa.x = Pantalla.width
				- BotonPausa.getContentSize().width / 2;
		UbicacionBotonPausa.y = Pantalla.height
				- BotonPausa.getContentSize().height / 2;
		BotonPausa.setPosition(UbicacionBotonPausa);

		// Creo el menu que va a contener los botones
		CCMenu MenuDeBotones;
		MenuDeBotones = CCMenu.menu(BotonPausa);
		MenuDeBotones.setPosition(CGPoint.make(0, 0));

		// Lo agrego a la capa principal del juego
		super.addChild(MenuDeBotones);

	}

	int CuentasRandom() {

		Random CuentaAzar = new Random();

		int RangoPosible = 20 - 1;

		Cuenta = CuentaAzar.nextInt(RangoPosible) + 1;

		return Cuenta;

	}

	void SiGano() {
		//Si el puntaje es menor, todavia tiene que seguir jugando.
		if(ptje < Cuenta) return;
		
		if(RelojGeneradorDeChupetines!= null) RelojGeneradorDeChupetines.cancel();
		
		if (ptje == Cuenta) {

			CCDirector.sharedDirector().pause();

			SoundEngine.sharedEngine().pauseSound();
			//String ganaste = "Ganaste!";
			//showAlert(ganaste);
			
			final Bundle datos = new Bundle();
	    	datos.putInt("pNivel", NivelGeneral);
	    	datos.putString("pJuego", "Cascada de Caramelos");
    		 
     		Intent NuevaAct;
     		NuevaAct = new Intent(CCDirector.sharedDirector().getActivity(), Ganaste.class);
    		NuevaAct.putExtras(datos); 
     		CCDirector.sharedDirector().getActivity().startActivity(NuevaAct);

		} else if (ptje > Cuenta) {
			CCDirector.sharedDirector().pause();

			SoundEngine.sharedEngine().pauseSound();
			String perdiste = "Seguí intentando!";
			showAlert(perdiste);
		}
	}

	String QueGolosina() {

		Golosinas = new String[10];
		Golosinas[0] = "choco_1.png";
		Golosinas[1] = "caramelo_2.png";
		Golosinas[2] = "caramelo_3.png";
		Golosinas[3] = "chupetin_4.png";
		Golosinas[4] = "ringpop_5.png";
		Golosinas[5] = "caramelo_6.png";
		Golosinas[6] = "ringpop_7.png";
		Golosinas[7] = "caramelo_8.png";
		Golosinas[8] = "caramelo_9.png";
		Golosinas[9] = "regalo_10.png";

		Random Golosina = new Random();
		int i = Golosina.nextInt(10);
		QueGolo = Golosinas[i];

		return QueGolo;

	}

	void PonerChupetin() {
		// yafuecolision = false;
		// Este método genera un chupetin nuevo, en una posición al azar

		// Creo un generador de números al azar
		Random Azar = new Random();

		// Declaro la variable que va a almacenar el Sprite del chupetin
		LaGolosina = QueGolosina();
		UnChupetin = CCSprite.sprite(LaGolosina);

		// Le asigno un tag autoincremental, y lo agrego al array de
		// chupetines

		TagChupetin++;
		UnChupetin.setTag(TagChupetin);
		Chupetines.add(UnChupetin);
		// lblCantidad.setString("Cantidad: "+TagChupetin);
		//
		// if(tipogolosina == "choco_1.png")
		// {
		// TagChoco++;
		// UnChupetin.setTag(TagChoco);
		// Chocos.add(UnChupetin);
		//
		//
		// }
		//
		// if(tipogolosina == "caramelo_2.png")
		// {
		// TagCaram2++;
		// UnChupetin.setTag(TagCaram2);
		// Caram2.add(UnChupetin);
		//
		// }

		// Le pongo su posicion inicial arriba de todo, por encima del borde
		// de la pantalla
		CGPoint PosicionInicial = new CGPoint();
		PosicionInicial.y = Pantalla.height
				+ UnChupetin.getContentSize().height / 2;

		// Determino su posicion en horizontal, al azar
		int XMinima = (int) (UnChupetin.getContentSize().width / 2);
		int XMaxima = (int) (Pantalla.width - UnChupetin.getContentSize().width / 2);
		int RangoPosible = XMaxima - XMinima;
		int PosicionX = Azar.nextInt(RangoPosible) + XMinima;
		PosicionInicial.x = PosicionX;

		// Lo ubico físicamente en la pantalla
		UnChupetin.setPosition(PosicionInicial);

		// Lo roto para que apunte hacia abajo
		// UnChupetin.runAction(CCRotateTo.action(0,0));

		// Determino su posición final
		CGPoint PosicionFinal = new CGPoint();
		PosicionFinal.x = PosicionInicial.x;
		PosicionFinal.y = -UnChupetin.getContentSize().height / 2;
		// PosicionFinal.y= Canasto.getContentSize().height;

		int minDuracion = 3;
		int maxDuracion = 5;
		int RangoDuracion = maxDuracion - minDuracion;
		int actualDuracion = Azar.nextInt(RangoDuracion) + minDuracion;
		
		// Lo agrego a la capa
		super.addChild(UnChupetin);

		// Inicio su desplazamiento
		UnChupetin
				.runAction(CCMoveTo.action(actualDuracion, PosicionFinal));

	}

	// Disparo la generación automática de chupetines

	public TimerTask tmrGeneradorDeChupetines() {
		return new TimerTask() {

			@Override
			public void run() {
				// SiGano();
				// PonerChupetin("ringpop_5.png",5);
				// PonerChupetin("chupetin_4.png",5);
				// PonerChupetin("caramelo_9.png",3);
				// PonerChupetin("caramelo_6.png",4);
	
				PonerChupetin();
				// yafuecolision = false;
				// PonerChupetin("caramelo_8.png",3);
				// PonerChupetin("caramelo_3.png",5);
				// LaGolosina = PonerChupetin("caramelo_2.png");
				// PonerChupetin("ringpop_7.png",4);
				// PonerChupetin("regalo_10.png",2);
	
			}
		};
	}

	void VerificarImpactoJugadorChupetin(int suma) {
		

		// ptje = 0;
		// yafuecolision = false;
		// Genero un rectángulo con el area del canasto del jugador
		for (CCSprite UnChupetin : Chupetines) {
			AreaJugador = CGRect.make(
					Canasto.getPosition().x
							- Canasto.getContentSize().width / 2.0f,
					Canasto.getPosition().y
							- Canasto.getContentSize().height / 2.0f,
					Canasto.getContentSize().width,
					Canasto.getContentSize().height);

			// int ptje = 0;
			// ptje++;
			// Creo un rectángulo para cada uno de los chupetines

			CGRect AreaUnChupetin = CGRect.make(
					UnChupetin.getPosition().x
							- UnChupetin.getContentSize().width / 2.0f,
					UnChupetin.getPosition().y
							- UnChupetin.getContentSize().height / 2.0f,
					UnChupetin.getContentSize().width,
					UnChupetin.getContentSize().height);

			// Verifico si hubo interseccion
			if (CGRect.intersects(AreaJugador, AreaUnChupetin)) {
				// Hay interseccion, por lo tanto, hubo impacto.
				// lblTitulo.setString("Hubo Impacto!");

				// ptje += suma;
				escol = 1;
				// lblPuntaje.setString("Total "+ptje);
				// yafuecolision= true;
			} else {
				// yafuecolision = false;
				escol = 0;
			}

		}
		SumarSiHuboColision(suma);

	}


	void SumarSiHuboColision(int suma) {
		//Por las dudas , valida que UnChupetin no sea null, para asi evitar que sume 2 veces
		if (escol == 1 && UnChupetin != null) {
			//Elimina el chupetin para que no genere mas efectos de colision.
			UnChupetin.removeSelf();
			UnChupetin = null;
			
			ptje += suma;
			lblPuntaje.setString("Total:" + ptje);
			SiGano();
			
		}
		
	}

	// Disparo el verificador de colisiones entre chupetines y el jugador
	public TimerTask tmrVerificadorColisionChupetinesJugador() {
		return new TimerTask() {
			@Override
			public void run() {
				
				if (LaGolosina == "choco_1.png") {
					VerificarImpactoJugadorChupetin(1);
				}
				if (LaGolosina == "caramelo_2.png") {
					VerificarImpactoJugadorChupetin(2);
				}
				if (LaGolosina == "caramelo_3.png") {
					VerificarImpactoJugadorChupetin(3);
				}
				if (LaGolosina == "chupetin_4.png") {
					VerificarImpactoJugadorChupetin(4);
				}
				if (LaGolosina == "ringpop_5.png") {
					VerificarImpactoJugadorChupetin(5);
				}
				if (LaGolosina == "caramelo_6.png") {
					VerificarImpactoJugadorChupetin(6);
				}
				if (LaGolosina == "ringpop_7.png") {
					VerificarImpactoJugadorChupetin(7);
				}
				if (LaGolosina == "caramelo_8.png") {
					VerificarImpactoJugadorChupetin(8);
				}
				if (LaGolosina == "caramelo_9.png") {
					VerificarImpactoJugadorChupetin(9);
				}
				if (LaGolosina == "regalo_10.png") {
					VerificarImpactoJugadorChupetin(10);
				}
			}
		};
	}
	

	// Este evento se ejecuta automáticamente cuando se toca la pantalla
	@Override
	public boolean ccTouchesMoved(MotionEvent e) {

		// Obtengo la posicion del punto donde tocó
		CGPoint PuntoTocado = CCDirector.sharedDirector().convertToGL(
				CGPoint.make(e.getX(), e.getY()));

		// Determino a qué distancia tocó del centro de la pantalla
		float MovimientoHorizontal;
		float MovimientoVertical;
		MovimientoHorizontal = PuntoTocado.x - Pantalla.width / 2;
		MovimientoVertical = PuntoTocado.y - Pantalla.height / 2;

		// Le aplico una escala al movimiento para que sea más suave
		int FactorMovimiento = 6;
		MovimientoHorizontal = MovimientoHorizontal / FactorMovimiento;
		MovimientoVertical = MovimientoVertical / FactorMovimiento;

		// Ubico al jugador en su nueva posicion
		CGPoint PosicionActualJugador = Canasto.getPosition();
		PosicionActualJugador.x = PosicionActualJugador.x
				+ MovimientoHorizontal;
		PosicionActualJugador.y = PosicionActualJugador.y
				+ MovimientoVertical;

		// Verifico que no se haya pasado del borde derecho de la pantalla
		if (PosicionActualJugador.x + Canasto.getContentSize().width / 2 > Pantalla.width) {
			// Se fue por la derecha
			PosicionActualJugador.x = Pantalla.width
					- Canasto.getContentSize().width / 2;
		}

		// Verifico que no se haya pasado del borde izquierdo de la pantalla
		if (PosicionActualJugador.x - Canasto.getContentSize().width / 2 < 0) {
			// Se fue por la izquierda
			PosicionActualJugador.x = Canasto.getContentSize().width / 2;
		}

		// Verifico que no se haya pasado del borde superior de la pantalla
		if (PosicionActualJugador.y + Canasto.getContentSize().height / 2 > Pantalla.height) {
			// Se fue por arriba
			PosicionActualJugador.y = Pantalla.height
					- Canasto.getContentSize().height / 2;
		}

		// Verifico que no se haya pasado del borde inferior de la pantalla
		if (PosicionActualJugador.y - Canasto.getContentSize().height / 2 < 0) {
			// Se fue por abajo
			PosicionActualJugador.y = Canasto.getContentSize().height / 2;
		}

		// Ubico físicamente la canasta del jugador
		Canasto.setPosition(PosicionActualJugador);

		return true;
	}

	public void BotonPausa(Object Llamador) {
		if (JuegoEnPausa) {
			JuegoEnPausa = false;
			CCDirector.sharedDirector().resume();
			SoundEngine.sharedEngine().resumeSound();
			
									
			if(RelojGeneradorDeChupetines != null) RelojGeneradorDeChupetines.cancel();
			RelojGeneradorDeChupetines = new Timer();
			RelojGeneradorDeChupetines.scheduleAtFixedRate(
					tmrGeneradorDeChupetines(), 1500, 3000);
			
			
		} else {
			JuegoEnPausa = true;
			CCDirector.sharedDirector().pause();
			SoundEngine.sharedEngine().pauseSound();
			
			RelojGeneradorDeChupetines.cancel();
			RelojGeneradorDeChupetines = null;
		}
	}
	
	/***
	 * Termina la app.
	 */
	private void endApp() {
		
		CCDirector.sharedDirector().end();
		
		//para que cierre totalmente el juego
//		Intent intent = new Intent(Intent.ACTION_MAIN);
//		intent.addCategory(Intent.CATEGORY_HOME);
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		mContext.startActivity(intent);
		
		Intent NuevaAct;
 		NuevaAct = new Intent(CCDirector.sharedDirector().getActivity(), ElegiJuegoNivelMATE.class);	 
 		CCDirector.sharedDirector().getActivity().startActivity(NuevaAct);

		
	}
	
	void showAlert(final String texto) {
		
		final CapaDelJuegoGolosinas game = this;
		
		CCDirector.sharedDirector().getActivity()
				.runOnUiThread(new Runnable() {
					public void run() {
						AlertDialog.Builder builder = new AlertDialog.Builder(
								CCDirector.sharedDirector().getActivity());
						builder.setMessage(texto)
								.setCancelable(false)
								.setPositiveButton(
										"Volver a jugar",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int id) {

												CCDirector.sharedDirector().replaceScene(JuegoGolosinas.EscenaDelJuego(mContext));

											}
										})
								.setNegativeButton(
										"Cerrar",
										new DialogInterface.OnClickListener() {

											public void onClick(
													DialogInterface dialog,
													int id) {
												dialog.cancel();
												//Termina la app
												game.endApp();
												
											}
										});
						AlertDialog alert = builder.create();
						alert.show();
					}
				});
	}

}

