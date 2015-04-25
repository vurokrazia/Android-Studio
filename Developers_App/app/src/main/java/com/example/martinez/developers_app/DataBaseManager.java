package com.example.martinez.developers_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by martinez on 16/04/15.
 */
public class DataBaseManager {
    public static final String TABLE_NAME_CURSO = "curso";
    public static final String CN_ID = "_id";
    public static final String CN_NOMBRE = "nombre";
    public static final String CN_FECHA = "fecha";
    public static final String CN_ = "_";

    public static final String CREATE_TABLE_CURSO = "create table "+TABLE_NAME_CURSO + " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_NOMBRE + " text not null,"
            + CN_FECHA + " text not null);";

    private DbHelper helper;
    private SQLiteDatabase bd;

    public DataBaseManager(Context contexto) {
         helper =new DbHelper(contexto);
         bd = helper.getWritableDatabase();
    }
    public ContentValues generarContentValues(String nombre,String fecha) {
        ContentValues valores = new ContentValues();
        valores.put(CN_NOMBRE, nombre);
        valores.put(CN_FECHA, fecha);
        return valores;
    }
    public void insertar(String nombre,String fecha){
         bd.insert(TABLE_NAME_CURSO,null,generarContentValues(nombre,fecha));
    }
}
