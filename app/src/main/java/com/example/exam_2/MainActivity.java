package com.example.exam_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exam_2.Modelo.ProductosDb;

public class MainActivity extends AppCompatActivity {
    private EditText txtCodigo, txtNombre, txtMarca, txtPrecio;
    private RadioButton rbPerecedero, rbNoPerecedero;
    private Button btnGuardar, btnLimpiar, btnProductos, btnEditar;
    private ProductosDb productosDb;
    String tipo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productosDb = new ProductosDb(this);
        iniciarComponentes();

        btnGuardar.setOnClickListener(v -> {
            if (validar()){
                String codigo = txtCodigo.getText().toString();
                String nombre = txtNombre.getText().toString();
                String marca = txtMarca.getText().toString();
                String precio = txtPrecio.getText().toString();

                Producto nuevoProducto = new Producto();
                nuevoProducto.setCodigo(codigo);
                nuevoProducto.setNombre(nombre);
                nuevoProducto.setMarca(marca);
                nuevoProducto.setTipo(tipo);
                nuevoProducto.setPrecio(precio);
                long res = productosDb.insertProducto(nuevoProducto);

                if(res > 0) {
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
private boolean validar(){
        String codigo = txtCodigo.getText().toString();
        String nombre = txtNombre.getText().toString();
        String marca = txtMarca.getText().toString();
        String precio = txtPrecio.getText().toString();

        if(codigo.equals("") || nombre.equals("") || marca.equals("") || precio.equals("") || tipo.equals("")){
            Toast.makeText(this, "Faltan datos de capturar", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
}
    private void iniciarComponentes() {
        txtCodigo = findViewById(R.id.txtCodigo);
        txtMarca = findViewById(R.id.txtMarca);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtNombre = findViewById(R.id.txtNombre);
        rbPerecedero = findViewById(R.id.perecedero);
        rbNoPerecedero = findViewById(R.id.noperecedero);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnEditar = findViewById(R.id.btnEditar);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnProductos = findViewById(R.id.btnProductos);

    }

    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        if(view.getId() == R.id.perecedero) {
            tipo = "Perecedero";
        }
        if(view.getId() == R.id.noperecedero) {
            tipo = "No Perecedero";
        }

    }
}