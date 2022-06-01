package com.example.mercaaqui.adapters
import com.example.mercaaqui.models.productos

interface ProductosListener{

    fun onProductosClicked(productos: productos , position: Int)
    }