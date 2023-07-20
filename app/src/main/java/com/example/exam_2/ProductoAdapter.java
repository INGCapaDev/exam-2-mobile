package com.example.exam_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.exam_2.Modelo.ProductosDb;

import java.util.List;

public class ProductoAdapter extends BaseAdapter {
    Context context;
    List<Producto> lst;

    public ProductoAdapter(Context context, List<Producto> lst){
        this.context = context;
        this.lst = lst;
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView TextViewNombre, TextViewMarca, TextViewPrecio;

        Producto c = lst.get(position);

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.producto_item, null);
        }

        TextViewNombre = convertView.findViewById(R.id.txtViewNombre);
        TextViewMarca = convertView.findViewById(R.id.txtViewMarca);
        TextViewPrecio = convertView.findViewById(R.id.txtViewPrecio);

        TextViewNombre.setText(c.nombre);
        TextViewMarca.setText(c.marca);
        TextViewPrecio.setText(c.precio);

        return convertView;
    }
}
