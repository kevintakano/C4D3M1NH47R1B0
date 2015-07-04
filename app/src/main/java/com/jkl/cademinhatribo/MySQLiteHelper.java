package com.jkl.cademinhatribo;

/**
 * Created by kevintakano on 02/07/15.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String MYDATABASE = "bancodedadados";
    private static final int VERSION = 1;

    public static final String TABELA_USUARIO = "Usuario";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_EMAIL = "email";
    public static final String COLUNA_SENHA = "senha";
    public static final String COLUNA_LATITUDE = "latitude";
    public static final String COLUNA_LONGITUDE = "longitude";



    /*public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }*/

    public MySQLiteHelper(Context connection) {
        super(connection, MYDATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        /*database.execSQL(DATABASE_CREATE);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        /*db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAIS);*/
        onCreate(db);
    }

}