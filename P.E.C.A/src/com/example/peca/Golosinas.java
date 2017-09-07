package com.example.peca;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.sound.SoundEngine;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Golosinas extends Activity {
	// Declaro el objeto principal de la vista
	protected CCGLSurfaceView VistaPrincipal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		
		
		// Configuro para que la vista principal no tenga titulo
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Configuro para que se use pantalla completa
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Configuro para que la pantalla se mantenga encendida
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		// Instancio el objeto de la vista
		VistaPrincipal = new CCGLSurfaceView(this);

		// Apunto la vista de esta actividad a la vista instanciada
		setContentView(VistaPrincipal);
		
		
	}

	@Override
	protected void onStart() {

		super.onStart();
		// Instancio el objeto del juego e invoco a su inicio
		JuegoGolosinas MiJuego = new JuegoGolosinas(Golosinas.this);
		MiJuego.Comenzar(VistaPrincipal);

	}

	@Override
	protected void onPause() {

		CCDirector.sharedDirector().pause();
		SoundEngine.sharedEngine().pauseSound();
		super.onPause();

	}

	@Override
	protected void onResume() {

		super.onResume();
		CCDirector.sharedDirector().resume();
		SoundEngine.sharedEngine().resumeSound();

	}

	@Override
	protected void onStop() {

		CCDirector.sharedDirector().end();
		SoundEngine.sharedEngine().realesAllSounds();
		super.onStop();

	}
}
