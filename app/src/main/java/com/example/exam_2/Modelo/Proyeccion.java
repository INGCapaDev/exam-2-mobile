package com.example.exam_2.Modelo;

import android.database.Cursor;

import com.example.exam_2.Producto;

import java.util.List;

public interface Proyeccion {
    public Producto getProducto(String codigo);
    public List<Producto> allProductos();
    public Producto readProducto(Cursor cursor);
}
