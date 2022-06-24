package com.example.mercaaqui.ui.productos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mercaaqui.R
import kotlinx.android.synthetic.main.fragment_productodialog.*
import com.example.mercaaqui.models.productos
import org.json.JSONObject
import androidx.fragment.app.DialogFragment


class productodialog : DialogFragment() {

    private lateinit var tb_detalles_info : Toolbar
    private lateinit var tituloproyecto : TextView
    private lateinit var cantidadprecio : TextView
    private lateinit var stockpk : TextView
    private lateinit var imageproyecto : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_productodialog, container, false)
        this.tb_detalles_info = ll.findViewById(R.id.tb_detalles_info)

        this.tb_detalles_info = ll.findViewById(R.id.tb_detalles_info)
        this.tituloproyecto = ll.findViewById(R.id.tituloproyecto)
        this.cantidadprecio = ll.findViewById(R.id.cantidadprecio)
        this.stockpk = ll.findViewById(R.id.stockpk)
        this.imageproyecto = ll.findViewById(R.id.imageproyecto)
        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.tb_detalles_info.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_baseline_cancel_24)
        this.tb_detalles_info.setNavigationOnClickListener{
            dismiss()
        }

        val product = JSONObject(arguments?.getString("productos"))

        this.tb_detalles_info.title = product.getString("nombre")
        this.tituloproyecto.text = product.getString("nombre")
        this.cantidadprecio.text = product.getString("precio")
        this.stockpk.text = product.getString("stock")

        Glide.with(this)
            .load(product.getString("img"))
            .into(this.imageproyecto)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}