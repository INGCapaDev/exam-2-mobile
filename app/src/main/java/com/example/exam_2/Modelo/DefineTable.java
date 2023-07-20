package com.example.exam_2.Modelo;

import android.provider.BaseColumns;

public class DefineTable implements BaseColumns {
    public DefineTable(){}

    public static abstract class Productos {
        public static final String TABLE_NAME = "productos";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_CODIGO = "codigo";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_MARCA = "marca";
        public static final String COLUMN_NAME_PRECIO = "precio";
        public static final String COLUMN_NAME_TIPO = "tipo";

        public static String[] REGISTRO = new String[]{
                Productos.COLUMN_NAME_ID,
                Productos.COLUMN_NAME_CODIGO,
                Productos.COLUMN_NAME_NOMBRE,
                Productos.COLUMN_NAME_MARCA,
                Productos.COLUMN_NAME_PRECIO,
                Productos.COLUMN_NAME_TIPO,
        };
    }
}
