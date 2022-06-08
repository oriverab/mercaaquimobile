package com.example.mercaaqui.adapters
import com.example.mercaaqui.models.productos

interface ProductosListener{

    fun onProductosClicked(Productos: productos, position: Int)

    }