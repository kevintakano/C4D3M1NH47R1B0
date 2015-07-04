package com.jkl.cademinhatribo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity{

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] colunasUsuario = { "nome",
			"email", "senha", "latitude","longitude"};

	public DatabaseActivity(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public Usuario insereUsuarioDB(Usuario usr) {

		ContentValues values = new ContentValues();
		values.put(dbHelper.COLUNA_NOME, usr.getNome()); // adiciona o nome do País
		values.put(dbHelper.COLUNA_EMAIL, usr.getEmail());
		values.put(dbHelper.COLUNA_SENHA, usr.getSenha());
		values.put(dbHelper.COLUNA_LATITUDE, usr.getLatitude());
		values.put(dbHelper.COLUNA_LONGITUDE, usr.getLongitude());

		long insertId = database.insert(MySQLiteHelper.COLUNA_NOME, null,
				values);
		Cursor cursor = database.query(MySQLiteHelper.TABELA_USUARIO,
				colunasUsuario, MySQLiteHelper.COLUNA_EMAIL + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Usuario newUsr = cursorToUsuario(cursor);

		cursor.close();

		return newUsr;
	}

	public Usuario getUsuario(String email)
	{
		Cursor cursor = database.query(MySQLiteHelper.TABELA_USUARIO, colunasUsuario , "email = " + email, null, null, null, null);
		Usuario newUsr = cursorToUsuario(cursor);
		return newUsr;
	}
	/*
	public void deletePais(Pais pais) {
		long id = pais.getGroupId();
		System.out.println("Pais deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_PAIS, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}

	public List<Pais> getAllPais() {
		List<Pais> pais_list = new ArrayList<Pais>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_PAIS,
				allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Pais pais = cursorToPais(cursor);
			pais_list.add(pais);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return pais_list;
	}


	public List<String> getAllPaisNames() {
		List<String> pais_list = new ArrayList<String>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_PAIS,
				allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Pais pais = cursorToPais(cursor);

			pais_list.add(pais.getName());
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return pais_list;
	}
*/
	private Usuario cursorToUsuario(Cursor cursor) {
		Usuario usr = new Usuario();

		usr.setNome(cursor.getString(cursor.getColumnIndex(dbHelper.COLUNA_NOME)));
		usr.setEmail(cursor.getString(cursor.getColumnIndex(dbHelper.COLUNA_EMAIL)));
		usr.setSenha(cursor.getString(cursor.getColumnIndex(dbHelper.COLUNA_SENHA)));
		usr.setLatitude(cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUNA_LATITUDE)));
		usr.setLongitude( cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUNA_LONGITUDE)));

		return usr;
	}
}