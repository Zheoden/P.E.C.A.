package com.example.peca;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CGSize;

import android.content.Context;

public class JuegoGolosinas {
	
	//Guarda el contexto del Activity en el que esta corriendo la app.
	private Context mContext;

	public JuegoGolosinas (Context ctx) {
		mContext = ctx;
	}
	
	public void Comenzar(CCGLSurfaceView Vista) {

		// Le indico al director que tiene que usar la vista que me enviaron
 		CCDirector.sharedDirector().attachInView(Vista);

		// Configuro la orientacion del equipo
		CCDirector.sharedDirector().setDeviceOrientation(CCDirector.kCCDeviceOrientationPortrait);

		// Configuro para que se vea el regimen de FPS que se estan usando
		// CCDirector.sharedDirector().setDisplayFPS(true);

		// Configuro la frecuencia de actualizacion de los objetos
		CCDirector.sharedDirector().setAnimationInterval(1.0f / 60);

		// Le digo al director que arranque con la escena del juego
		CCDirector.sharedDirector().runWithScene(EscenaDelJuego(mContext));

	}

	public static CCScene EscenaDelJuego(Context ctx) {

		// Declaro e instancio la escena a devolver
		CCScene EscenaADevolver;
		EscenaADevolver = CCScene.node();

		// Le adjunto una capa para el juego
		EscenaADevolver.addChild(new CapaDelJuegoGolosinas(ctx), 2);
		
//		CGSize frameSize = CCDirector.sharedDirector().winSize();
//		CCSprite background = CCSprite.sprite("fondo.jpg");
//		background.setTag(1);
//		background.setContentSize(frameSize);
//		//background.setPosition(frameSize.width/2,frameSize.height/2);
//		background.setAnchorPoint(0, 0);
//		EscenaADevolver.addChild(background);
		
		

		return EscenaADevolver;
	}
}
