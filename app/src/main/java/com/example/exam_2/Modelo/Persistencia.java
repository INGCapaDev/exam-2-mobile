package com.example.exam_2.Modelo;

import com.example.exam_2.Producto;

public interface Persistencia {
    public void openDataBase();
    public void closeDataBase();
    public long insertProducto(Producto producto);
    public long updateProducto(Producto producto);
    public void deleteProducto(int id);
}
