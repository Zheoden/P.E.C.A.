package com.example.peca;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ElegiElArea extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.elegi_el_area);
		
		Button Matematica = (Button)findViewById(R.id.btnMatematica);
		Button Lengua = (Button)findViewById(R.id.btnLengua);
		Button SobreLasAreas = (Button)findViewById(R.id.btnSobreLasAreas);
		
		SobreLasAreas.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {   		
	     		CrearMensaje("Los contenidos de Lengua y Matemática presentados fueron especialmente seleccionados para lograr optimizar individualmente el desarrollo intelectual por medio de la estimulación del potencial creativo. Cada uno de estos módulos, presenta diferentes juegos.\nEn el módulo de matemática, se puede seleccionar un memotest, o un juego de golosinas. En cada uno de ellos se trabajan las operaciones como suma, y resta con distintos niveles de complejidad.\nEl memotest en lugar de ser el clásico que todos conocen, cuenta con la particularidad de que los pares no son fichas iguales, sino que son operaciones junto con sus respectivos resultados.\nEn la “Cascada de caramelos”, el segundo juego, se trabaja también con operaciones matemáticas pero mediante una piñata de cumpleaños, permitiendo que los chicos junten caramelos con el fin de llegar a un resultado específico.\nEn la sección de lengua, se presentan juegos que permiten trabajar sobre la comprensión lectora y la ortografía.\nEl primero consiste en la creación de un cuento seleccionando las opciones posibles, según la coherencia.\nTambién será posible jugar a un ahorcado para practicar la escritura de distintas palabras. Además,  éste cuenta con el adicional de presentar una pista cuando el chico se equivoca varias veces.", "Sobre las áreas", "Aceptar");
	     	} 
		});
		
		final Button Volver = (Button) findViewById(R.id.btnVolver);

		Volver.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent ActividadAInvocar;
				ActividadAInvocar = new Intent(ElegiElArea.this, PaginaPrincipal.class);
				startActivity(ActividadAInvocar);
				
			}
		});
		
		Matematica.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {   		
	     		Intent ActividadAInvocar;
	     		ActividadAInvocar = new Intent(ElegiElArea.this, ElegiJuegoNivelMATE.class);
	     		startActivity(ActividadAInvocar);
	     	} 
		});
		Lengua.setOnClickListener(new OnClickListener() {
	    	 public void onClick(View v) {   		
	     		Intent NuevaAct;
	     		NuevaAct = new Intent(ElegiElArea.this, ElegiJuegoNivelLENGUA.class);
	     		startActivity(NuevaAct);
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
