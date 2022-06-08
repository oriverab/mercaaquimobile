package com.example.mercaaqui.adapters
import com.example.mercaaqui.models.productos
import org.json.JSONObject

interface ProductosListener{

    fun onProductosClicked(Productos: JSONObject, position: Int)

    }