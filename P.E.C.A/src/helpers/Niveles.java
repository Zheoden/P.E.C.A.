package helpers;

import java.sql.Array;
import java.util.ArrayList;


public class Niveles {
	
	public static boolean NivelAhorcado1 = true;
	public static boolean NivelAhorcado2 = false;
	public static boolean NivelAhorcado3 = false;
	public static boolean NivelAhorcado4 = false;
	public static boolean NivelCuentos1 = false;
	public static boolean NivelCuentos2 = false;
	public static boolean NivelCuentos3 = false;
	public static boolean NivelCuentos4 = false;
	
	public static boolean NivelMemotest1 = false;
	public static boolean NivelMemotest2 = false;
	public static boolean NivelMemotest3 = false;
	public static boolean NivelMemotest4 = false;
	public static boolean NivelCascada1 = false;
	public static boolean NivelCascada2 = false;
	public static boolean NivelCascada3 = false;
	public static boolean NivelCascada4 = false;

	public static Boolean[] GetNivelesAhorcado() {
		
		Boolean[] VectorAhorcado = new Boolean[4];
		
		VectorAhorcado[0] = NivelAhorcado1;
		VectorAhorcado[1] = NivelAhorcado2;
		VectorAhorcado[2] = NivelAhorcado3;
		VectorAhorcado[3] = NivelAhorcado4;
		
		return VectorAhorcado;
	}

	public static Boolean[] GetNivelesCuentos() {

		Boolean[] VectorAhorcado = new Boolean[4];

		VectorAhorcado[0] = NivelCuentos1;
		VectorAhorcado[1] = NivelCuentos2;
		VectorAhorcado[2] = NivelCuentos3;
		VectorAhorcado[3] = NivelCuentos4;

		return VectorAhorcado;
	}

	public static Boolean[] GetNivelesMemotest() {

		Boolean[] VectorAhorcado = new Boolean[4];

		VectorAhorcado[0] = NivelMemotest1;
		VectorAhorcado[1] = NivelMemotest2;
		VectorAhorcado[2] = NivelMemotest3;
		VectorAhorcado[3] = NivelMemotest4;

		return VectorAhorcado;
	}

	public static Boolean[] GetNivelesCascada() {

		Boolean[] VectorAhorcado = new Boolean[4];

		VectorAhorcado[0] = NivelCascada1;
		VectorAhorcado[1] = NivelCascada2;
		VectorAhorcado[2] = NivelCascada3;
		VectorAhorcado[3] = NivelCascada4;

		return VectorAhorcado;
	}

}

