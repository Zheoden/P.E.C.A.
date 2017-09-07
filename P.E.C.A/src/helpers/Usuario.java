package helpers;

public class Usuario {
	private static String _strUsuario = null;
	
	
	public static  void setUsuario(String strUsuario){
		_strUsuario = strUsuario;
	}
	
	public static String getUsuario(){
		return _strUsuario;
	}

}
