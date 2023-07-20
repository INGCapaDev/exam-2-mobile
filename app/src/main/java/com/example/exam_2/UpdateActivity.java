package com.example.exam_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.exam_2.Modelo.Persistencia;
import com.example.exam_2.Modelo.ProductosDb;

public class UpdateActivity extends AppCompatActivity {
    private EditText txtCodigo, txtNombre, txtMarca, txtPrecio;
    private RadioButton rbPerecedero, rbNoPerecedero;
    private Button btnActualizar, btnRegresarUpdate, btnBorrar, btnBuscar;
    private ProductosDb productosDb;
    String tipo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        productosDb = new ProductosDb(this);
        iniciarComponentes();

        btnBuscar.setOnClickListener(v -> {
            String codigo = txtCodigo.getText().toString();
            Producto producto = productosDb.getProducto(codigo);

            if(producto == null){
               Toast.makeText(this, "No se encontro el producto", Toast.LENGTH_SHORT).show();
               return;
            }
            if(producto.getTipo().toString().equals("Perecedero")){
                tipo="Perecedero";
                rbNoPerecedero.setChecked(false);
                rbPerecedero.setChecked(true);
            }

            if(producto.getTipo().toString().equals("No Perecedero")){
                tipo="No Perecedero";
                rbPerecedero.setChecked(false);
                rbNoPerecedero.setChecked(true);
            }




                txtNombre.setText(producto.getNombre().toString());
                txtMarca.setText(producto.getMarca().toString());
                txtPrecio.setText(producto.getPrecio().toString());

        });
        btnActualizar.setOnClickListener(v -> {
            if (validar()){
                String codigo = txtCodigo.getText().toString();
                String nombre = txtNombre.getText().toString();
                String marca = txtMarca.getText().toString();
                String precio = txtPrecio.getText().toString();

                Producto producto = productosDb.getProducto(codigo);
                if(producto == null){
                    Toast.makeText(this, "No se encontro el producto", Toast.LENGTH_SHORT).show();
                    return;
                }

                Producto nuevoProducto = new Producto();
                nuevoProducto.setId(producto.getId());
                nuevoProducto.setCodigo(codigo);
                nuevoProducto.setNombre(nombre);
                nuevoProducto.setMarca(marca);
                nuevoProducto.setPrecio(precio);
                nuevoProducto.setTipo(tipo);
                long res = productosDb.updateProducto(nuevoProducto);

                if(res > 0) {
                    Toast.makeText(this, "Registro Actualizado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRegresarUpdate.setOnClickListener(v -> {
            finish();
        });

        btnBorrar.setOnClickListener(v -> {
            if(!txtCodigo.getText().toString().equals("")){
                String codigo = txtCodigo.getText().toString();
                Producto producto = productosDb.getProducto(codigo);
                if(producto == null) {
                    Toast.makeText(this, "No se encontro el producto", Toast.LENGTH_SHORT).show();
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Eliminar Producto");
                builder.setMessage("¿Estás seguro de eliminar el producto?");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        productosDb.deleteProducto(producto.getId());
                        Toast.makeText(UpdateActivity.this, "Producto Eliminado", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                Toast.makeText(this, "Ingresa el codigo", Toast.LENGTH_SHORT).show();

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
        txtCodigo = findViewById(R.id.txtCodigoUpdate);
        txtMarca = findViewById(R.id.txtMarcaUpdate);
        txtPrecio = findViewById(R.id.txtPrecioUpdate);
        txtNombre = findViewById(R.id.txtNombreUpdate);
        rbPerecedero = findViewById(R.id.perecederoUpdate);
        rbNoPerecedero = findViewById(R.id.noperecederoUpdate);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnRegresarUpdate = findViewById(R.id.btnRegresarUpdate);
        btnBorrar = findViewById(R.id.btnBorrar);
    }

    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        if(view.getId() == R.id.perecederoUpdate) {
            tipo = "Perecedero";
        }
        if(view.getId() == R.id.noperecederoUpdate) {
            tipo = "No Perecedero";
        }

    }
}