package com.example.exam_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.exam_2.Modelo.ProductosDb;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ListView listViewProductos;
    private ProductosDb productosDb;
    private Button btnRegresarLst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listViewProductos = findViewById(R.id.listViewProductos);
        productosDb = new ProductosDb(this);
        btnRegresarLst = findViewById(R.id.btnRegresarLst);

        List<Producto> listaProductos = productosDb.allProductos();
        ProductoAdapter adapter = new ProductoAdapter(this, listaProductos);

        listViewProductos.setAdapter(adapter);
        btnRegresarLst.setOnClickListener(v -> {
            finish();
        });
    }
}