package com.example.peca;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class Cuentos extends Activity {

	public int Cont = 0;
	public int Nivel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		setContentView(R.layout.cuentos);
		final Button Volver = (Button) findViewById(R.id.btnVolver);

		Volver.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent ActividadAInvocar;
				ActividadAInvocar = new Intent(Cuentos.this, ElegiJuegoNivelLENGUA.class);
				startActivity(ActividadAInvocar);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			}
		});

		final Button SiguienteNivel = (Button)findViewById(R.id.SiguienteNivelCuentos);
		SiguienteNivel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (Nivel < 4) {
					Nivel += 1;
					final Bundle datos = new Bundle();
					datos.putInt("pNivel", Nivel);

					Intent NuevaAct;
					NuevaAct = new Intent(Cuentos.this, Cuentos.class);
					NuevaAct.putExtras(datos); 
					startActivity(NuevaAct);
				}
				else
				{
					Mensaje("Ya esta en el ultimo nivel del juego, Suerte!","No hay mas niveles","Aceptar");
					SiguienteNivel.setEnabled(false);

				}

			}
		});
		Bundle datos = this.getIntent().getExtras();
		Nivel = datos.getInt("pNivel");		
		JuegoCuentos();
	}

	public void JuegoCuentos()
	{
		switch (Nivel) {
		case 1:
			NivelUno();

			break;
		case 2:
			NivelDos();
			break;
		case 3:
			NivelTres();
			break;
		case 4:
			NivelCuatro();
			break;
		}

	}
	public void NivelUno()
	{
		final Button btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
		final RadioButton[] Opciones = CargarOpciones();
		TextView Texto = (TextView) findViewById(R.id.HistoriaPrincipal);
		Texto.setText("Había una vez una");

		final String[] OpcionesSeleccionas = new String[8];
		DarValorOpciones("MARIPOSA", "PALOMA", "AUTO", "PARAGUAS");
		Cont = Cont + 1;
		btnSiguiente.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(Opciones[0].isChecked() == true || Opciones[1].isChecked() == true || Opciones[2].isChecked() == true || Opciones[3].isChecked() == true)
				{
					String Seleccionado = null;

					boolean Op1 = Opciones[0].isChecked();
					if (Op1){
						Seleccionado = Opciones[0].getText().toString();
					}				
					boolean Op2 = Opciones[1].isChecked();
					if (Op2){
						Seleccionado = Opciones[1].getText().toString();
					}
					boolean Op3 = Opciones[2].isChecked();
					if (Op3){
						Seleccionado = Opciones[2].getText().toString();
					}	
					boolean Op4 = Opciones[3].isChecked();
					if (Op4){
						Seleccionado = Opciones[3].getText().toString();
					}
					OpcionesSeleccionas[Cont] = Seleccionado;

					if(Seleccionado == Opciones[2].getText().toString()|| Seleccionado == Opciones[3].getText().toString())
					{
						Mensaje("La opción que elegiste es incorrecta. Elegí otra.", "Te confundiste", "Aceptar");
					}

					else
					{

						if(Cont == 7){
							Inicializar(Seleccionado, " juntas.");
							btnSiguiente.setEnabled(false);
							final Bundle datos = new Bundle();
							datos.putInt("pNivel", Nivel);
							datos.putString("pJuego", "Cuentos");

							Intent NuevaAct;
							NuevaAct = new Intent(Cuentos.this, Ganaste.class);
							NuevaAct.putExtras(datos); 
							startActivity(NuevaAct);

						}
						else
						{

							switch (Cont) {
							case 1:
								Inicializar(Seleccionado, " que estaba volando por el cielo muy felizmente. Ella era muy");
								DarValorOpciones("LINDA", "COLORIDA", "VOLABA", "PINTANDO");
								break;
							case 2:
								Inicializar(Seleccionado, " y le gustaba mucho");
								DarValorOpciones("BAILAR", "CANTAR", "PINTADO", "ROTO");
								break;
							case 3:
								Inicializar(Seleccionado,". De pronto, se encontró con una nube que le pregunto cuántos");
								DarValorOpciones("HERMANOS", "AMIGOS", "COMPUTADORAS", "CELULARES");
								break;
							case 4:
								Inicializar(Seleccionado, " tenia. Ella dijo 10 y le conto sobre cada uno. Más tarde, la nube le empezó a contar sobre sus");
								DarValorOpciones("PAPÁS", "PRIMOS", "JUGAR", "LINDOS");
								break;
							case 5:
								Inicializar(Seleccionado, " y se hicieron muy amigas. A partir de eso, todos los");
								DarValorOpciones("VIERNES", "DÍAS", "PLANTAS", "AMIGAS");
								break;
							case 6:
								Inicializar(Seleccionado , " empezaron a verse y a ");
								DarValorOpciones("CHARLAR", "JUGAR", "NUEVO", "FELIZ");
								break;
								//						case 7:
								//							Inicializar(Seleccionado);
								//							DarValorOpciones("Opcion1", "Opcion2", "Opcion3", "Sheii");
								//						break;
								//						default:
								//							Inicializar(Seleccionado);
								//							DarValorOpciones("Error", "Error", "Error", "Error");
								//							break;
							}

						}
						Cont += 1;

					}




				}
				else
				{
					Mensaje("Por favor seleccione una opcion","Opcion vacia", "Ok");
				}
			}
		});

	}


	public void NivelDos()
	{
		final Button btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
		final RadioButton[] Opciones = CargarOpciones();

		final String[] OpcionesSeleccionas = new String[8];
		DarValorOpciones("Opcion1", "Flor", "Opcion3", "Opcion4");
		btnSiguiente.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(Opciones[0].isChecked() == true || Opciones[1].isChecked() == true || Opciones[2].isChecked() == true || Opciones[3].isChecked() == true)
				{
					String Seleccionado = null;

					boolean Op1 = Opciones[0].isChecked();
					if (Op1){
						Seleccionado = Opciones[0].getText().toString();
					}				
					boolean Op2 = Opciones[1].isChecked();
					if (Op2){
						Seleccionado = Opciones[1].getText().toString();
					}
					boolean Op3 = Opciones[2].isChecked();
					if (Op3){
						Seleccionado = Opciones[2].getText().toString();
					}	
					boolean Op4 = Opciones[3].isChecked();
					if (Op4){
						Seleccionado = Opciones[3].getText().toString();
					}
					OpcionesSeleccionas[Cont] = Seleccionado;					
					Cont += 1;
					if(Cont == 8){
						btnSiguiente.setEnabled(false);	
					}
					else
					{

						switch (Cont) {
						case 1:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Opcion1", "Flor", "Opcion3", "Opcion4");
							break;
						case 2:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Opcion1", "Opcion2", "Flor", "Opcion4");
							break;
						case 3:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Opcion1", "Opcion2", "Opcion3", "Flor");
							break;
						case 4:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Sheii", "Opcion2", "Opcion3", "Opcion4");
							break;
						case 5:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Opcion1", "Sheii", "Opcion3", "Opcion4");
							break;
						case 6:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Opcion1", "Opcion2", "Sheii", "Opcion4");
							break;
						case 7:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Opcion1", "Opcion2", "Opcion3", "Sheii");
							break;
						default:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Error", "Error", "Error", "Error");
							break;
						}
					}
				}
				else
				{
					Mensaje("Por favor seleccione una opcion","Opcion vacia", "Ok");
				}
			}
		});

	}

	public void NivelTres()
	{
		final Button btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
		final RadioButton[] Opciones = CargarOpciones();

		final String[] OpcionesSeleccionas = new String[8];
		DarValorOpciones("Opcion1", "Opcion2", "Flor", "Opcion4");
		btnSiguiente.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(Opciones[0].isChecked() == true || Opciones[1].isChecked() == true || Opciones[2].isChecked() == true || Opciones[3].isChecked() == true)
				{
					String Seleccionado = null;

					boolean Op1 = Opciones[0].isChecked();
					if (Op1){
						Seleccionado = Opciones[0].getText().toString();
					}				
					boolean Op2 = Opciones[1].isChecked();
					if (Op2){
						Seleccionado = Opciones[1].getText().toString();
					}
					boolean Op3 = Opciones[2].isChecked();
					if (Op3){
						Seleccionado = Opciones[2].getText().toString();
					}	
					boolean Op4 = Opciones[3].isChecked();
					if (Op4){
						Seleccionado = Opciones[3].getText().toString();
					}
					OpcionesSeleccionas[Cont] = Seleccionado;
					Cont += 1;
					if(Cont == 8){
						btnSiguiente.setEnabled(false);	
					}
					else
					{

						switch (Cont) {
						case 1:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Opcion1", "Flor", "Opcion3", "Opcion4");
							break;
						case 2:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Opcion1", "Opcion2", "Flor", "Opcion4");
							break;
						case 3:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Opcion1", "Opcion2", "Opcion3", "Flor");
							break;
						case 4:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Sheii", "Opcion2", "Opcion3", "Opcion4");
							break;
						case 5:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Opcion1", "Sheii", "Opcion3", "Opcion4");
							break;
						case 6:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Opcion1", "Opcion2", "Sheii", "Opcion4");
							break;
						case 7:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Opcion1", "Opcion2", "Opcion3", "Sheii");
							break;
						default:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Error", "Error", "Error", "Error");
							break;
						}
					}
				}
				else
				{
					Mensaje("Por favor seleccione una opcion","Opcion vacia", "Ok");
				}
			}
		});

	}

	public void NivelCuatro()
	{
		final Button btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
		final RadioButton[] Opciones = CargarOpciones();

		final String[] OpcionesSeleccionas = new String[8];
		DarValorOpciones("Opcion1", "Opcion2", "Opcion3", "Flor");
		btnSiguiente.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(Opciones[0].isChecked() == true || Opciones[1].isChecked() == true || Opciones[2].isChecked() == true || Opciones[3].isChecked() == true)
				{
					String Seleccionado = null;

					boolean Op1 = Opciones[0].isChecked();
					if (Op1){
						Seleccionado = Opciones[0].getText().toString();
					}				
					boolean Op2 = Opciones[1].isChecked();
					if (Op2){
						Seleccionado = Opciones[1].getText().toString();
					}
					boolean Op3 = Opciones[2].isChecked();
					if (Op3){
						Seleccionado = Opciones[2].getText().toString();
					}	
					boolean Op4 = Opciones[3].isChecked();
					if (Op4){
						Seleccionado = Opciones[3].getText().toString();
					}
					OpcionesSeleccionas[Cont] = Seleccionado;
					Cont += 1;
					if(Cont == 8){
						btnSiguiente.setEnabled(false);	
					}
					else
					{

						switch (Cont) {
						case 1:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Opcion1", "Flor", "Opcion3", "Opcion4");
							break;
						case 2:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Opcion1", "Opcion2", "Flor", "Opcion4");
							break;
						case 3:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Opcion1", "Opcion2", "Opcion3", "Flor");
							break;
						case 4:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Sheii", "Opcion2", "Opcion3", "Opcion4");
							break;
						case 5:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Opcion1", "Sheii", "Opcion3", "Opcion4");
							break;
						case 6:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Opcion1", "Opcion2", "Sheii", "Opcion4");
							break;
						case 7:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Opcion1", "Opcion2", "Opcion3", "Sheii");
							break;
						default:
							Inicializar(Seleccionado,"");
							DarValorOpciones("Error", "Error", "Error", "Error");
							break;
						}
					}
				}
				else
				{
					Mensaje("Por favor seleccione una opcion","Opcion vacia", "Ok");
				}
			}
		});

	}

	public void Inicializar(String Seleccionado, String TextoaAgregar)
	{
		TextView Texto = (TextView) findViewById(R.id.HistoriaPrincipal);
		String TextoAnterior = Texto.getText().toString();
		Texto.setText(TextoAnterior + " " + Seleccionado + TextoaAgregar);
	}

	public void DarValorOpciones(String Opcion1, String Opcion2, String Opcion3, String Opcion4)
	{
		RadioButton[] Opciones = new RadioButton[4];
		Opciones = CargarOpciones();
		Opciones[0].setText(Opcion1);
		Opciones[1].setText(Opcion2);
		Opciones[2].setText(Opcion3);
		Opciones[3].setText(Opcion4);
	}

	public RadioButton[] CargarOpciones()
	{
		RadioButton[] Prueba = new RadioButton[4];
		Prueba[0] = (RadioButton) findViewById (R.id.Opcion1);
		Prueba[1] = (RadioButton) findViewById (R.id.Opcion2);
		Prueba[2] = (RadioButton) findViewById (R.id.Opcion3);
		Prueba[3] = (RadioButton) findViewById (R.id.Opcion4);

		return Prueba;
	}

	public void Mensaje(final String Mensaje, final String Titulo, final String PositiveButton ){
		AlertDialog.Builder Mensajes  = new AlertDialog.Builder(this);
		Mensajes.setMessage(Mensaje);
		Mensajes.setTitle(Titulo);
		Mensajes.setPositiveButton(PositiveButton, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {


				dialog.dismiss();
			}
		});
		Mensajes.setCancelable(true);
		Mensajes.create().show();
	}

	public int Randomizador()
	{
		Random r = new Random();
		int i1  = r.nextInt(29 - 0) + 0;
		Log.d("numero", String.valueOf(i1));

		return i1;
	}
}




