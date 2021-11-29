package com.example.productos_clase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Lita
/*
        val producto1 = Producto("Audi",500.0,"Hola",R.drawable.audifonos)
        val producto2 = Producto("Redmi 10pro",10000.0,"celu",R.drawable.celu)
        val producto3 = Producto("Nike",500.0,"Soy Tenis",R.drawable.tenis)
        val listaProductos= listOf(producto1,producto2,producto3)
        val adapter = ProdustosAdapter(this,listaProductos)*/
        //-----------------------------------------------------------------
        var listaProductos = emptyList<Producto>()
        var database = AppDatabase.getDatabase(this)
        database.productos().getAll().observe(this, Observer{
            listaProductos = it
            val adapter = ProdustosAdapter(this,listaProductos)
            lista.adapter=adapter
        })
        //------------------------------------------------------------------------------------------

        //lista.adapter=adapter

        //Enlazar detalle
        lista.setOnItemClickListener{ parent ,view,position,id ->
            val intent = Intent(this,ProductoActivity::class.java)
                intent.putExtra("id",listaProductos[position].idProducto)
            startActivity(intent)
        }
        //------------------------------------------------------------------------------------------
        floatingActionButton2.setOnClickListener {
            val intent = Intent(this,NuevoProductoActivity::class.java)
            startActivity(intent)
        }
    }
}