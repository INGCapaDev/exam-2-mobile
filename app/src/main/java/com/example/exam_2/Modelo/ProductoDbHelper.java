package com.example.exam_2.Modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductoDbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ", ";

    private static final String SQL_CREATE_PRODUCTO = "CREATE TABLE " +
            DefineTable.Productos.TABLE_NAME + " ( " +
            DefineTable.Productos.COLUMN_NAME_ID + " INTEGER PRIMARY KEY, " +
            DefineTable.Productos.COLUMN_NAME_CODIGO + TEXT_TYPE + COMMA_SEP +
            DefineTable.Productos.COLUMN_NAME_NOMBRE + TEXT_TYPE + COMMA_SEP +
            DefineTable.Productos.COLUMN_NAME_MARCA + TEXT_TYPE + COMMA_SEP +
            DefineTable.Productos.COLUMN_NAME_PRECIO + TEXT_TYPE + COMMA_SEP +
            DefineTable.Productos.COLUMN_NAME_TIPO + TEXT_TYPE + " );";

    private static final String SQL_DELETE_PRODUCTO = "DROP TABLE IF EXISTS " +
            DefineTable.Productos.TABLE_NAME;

    private static final String DATABASE_NAME = "productos.db";
    private static final int DATABASE_VERSION = 1;

    public ProductoDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PRODUCTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PRODUCTO);
        onCreate(db);
    }
}
