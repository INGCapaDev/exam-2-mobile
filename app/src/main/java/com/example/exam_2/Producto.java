package com.example.exam_2;

import java.io.Serializable;

public class Producto implements Serializable {
    public int id;
    public String codigo;
    public String nombre;
    public String marca;
    public String precio;
    public String tipo;

    public Producto() {
        this.nombre = "";
        this.codigo = "";
        this.marca = "";
        this.precio = "";
        this.tipo = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
