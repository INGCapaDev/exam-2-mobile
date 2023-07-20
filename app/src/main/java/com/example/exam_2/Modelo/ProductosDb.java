package com.example.exam_2.Modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.exam_2.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductosDb implements Persistencia, Proyeccion{

    private Context context;
    private ProductoDbHelper helper;
    private SQLiteDatabase db;

    public ProductosDb(Context context, ProductoDbHelper helper){
        this.context = context;
        this.helper = helper;
    }

    public ProductosDb(Context context){
        this.context = context;
        this.helper = new ProductoDbHelper(this.context);
    }

    @Override
    public void openDataBase() {
        db = helper.getWritableDatabase();
    }

    @Override
    public void closeDataBase() {
        helper.close();
    }

    @Override
    public long insertProducto(Producto producto) {
        ContentValues values = new ContentValues();
        values.put(DefineTable.Productos.COLUMN_NAME_CODIGO, producto.getCodigo());
        values.put(DefineTable.Productos.COLUMN_NAME_NOMBRE, producto.getNombre());
        values.put(DefineTable.Productos.COLUMN_NAME_MARCA, producto.getMarca());
        values.put(DefineTable.Productos.COLUMN_NAME_PRECIO, producto.getPrecio());
        values.put(DefineTable.Productos.COLUMN_NAME_TIPO, producto.getTipo());

        this.openDataBase();
        long num = db.insert(DefineTable.Productos.TABLE_NAME, null, values);
        return num;
    }

    @Override
    public long updateProducto(Producto producto) {
        ContentValues values = new ContentValues();
        values.put(DefineTable.Productos.COLUMN_NAME_ID, producto.getId());
        values.put(DefineTable.Productos.COLUMN_NAME_CODIGO, producto.getCodigo());
        values.put(DefineTable.Productos.COLUMN_NAME_NOMBRE, producto.getNombre());
        values.put(DefineTable.Productos.COLUMN_NAME_MARCA, producto.getMarca());
        values.put(DefineTable.Productos.COLUMN_NAME_PRECIO, producto.getPrecio());
        values.put(DefineTable.Productos.COLUMN_NAME_TIPO, producto.getTipo());

        this.openDataBase();
        long num = db.update(
                DefineTable.Productos.TABLE_NAME,
                values,
                DefineTable.Productos.COLUMN_NAME_ID + " = " + producto.getId(),
                null);

        return num;
    }

    @Override
    public void deleteProducto(int id) {
        this.openDataBase();
        db.delete(
                DefineTable.Productos.TABLE_NAME,
                DefineTable.Productos.COLUMN_NAME_ID + "=?",
                new String[] {String.valueOf(id)}
        );

    }

    @Override
    public Producto getProducto(String codigo) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.query(
                DefineTable.Productos.TABLE_NAME,
                DefineTable.Productos.REGISTRO,
                DefineTable.Productos.COLUMN_NAME_CODIGO + "=?",
                new String[]{codigo},
                null, null, null

        );

        if (cursor.moveToFirst()) {
            Producto producto = readProducto(cursor);
            cursor.close();
            return producto;
        } else {
            cursor.close();
            return null;
        }
    }

    @Override
    public List<Producto> allProductos() {
        db = helper.getWritableDatabase();
        Cursor cursor = db.query(
                DefineTable.Productos.TABLE_NAME,
                DefineTable.Productos.REGISTRO,
                null, null, null, null, null
        );
        List<Producto> productos = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Producto producto = readProducto(cursor);
                productos.add(producto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return productos;
    }

    @Override
    public Producto readProducto(Cursor cursor) {
        Producto producto = new Producto();
        producto.setId(cursor.getInt(0));
        producto.setCodigo(cursor.getString(1));
        producto.setNombre(cursor.getString(2));
        producto.setMarca(cursor.getString(3));
        producto.setPrecio(cursor.getString(4));
        producto.setTipo(cursor.getString(5));

        return producto;
    }
}
