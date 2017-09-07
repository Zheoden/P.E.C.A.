package helpers;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class basededatos  extends SQLiteOpenHelper {
	public basededatos(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	String sqlCreateUsuarios        = "CREATE TABLE [Usuarios] ([IDUsuario] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,[Nombre] TEXT NULL)";
	String sqlCreateJuegos          = "CREATE TABLE [Juegos] ([IDJuego] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,[IDArea] INTEGER NULL, [Descripcion] TEXT NULL)";
	String sqlCreateNiveles         = "CREATE TABLE [Niveles]([ID] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, [IDJuego] INTEGER  NULL, [IDUsuario] INTEGER NULL, [NumeroNivel] INTEGER NULL , [Superado] INTEGER NULL)";
	String sqlCreateAreas           = "CREATE TABLE [Areas]([IDArea] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,[Nombre] TEXT NULL,[Descripcion] TEXT NULL)";
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sqlCreateUsuarios);
		db.execSQL(sqlCreateJuegos);
		db.execSQL(sqlCreateNiveles);
		db.execSQL(sqlCreateAreas);
	
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}