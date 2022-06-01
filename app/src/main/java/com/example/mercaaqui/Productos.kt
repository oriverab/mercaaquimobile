package com.example.mercaaqui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONArray
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Productos.newInstance] factory method to
 * create an instance of this fragment.
 */
class Productos : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val url = "http://192.168.3.218/mercaaqui/app/Http/listaproductos.php"
        val queue = Volley.newRequestQueue(this)
        val tvNombre = findViewById<TextView>(R.id.tvnombre)
        // val tvDescripcion= findViewById<TextView>(R.id.tVdescripcionp)
        val tvPrecio =findViewById<TextView>(R.id.tvprecio)
        val iVProducto =findViewById<ImageView>(R.id.ivimg)
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener { response ->
            val jsonArray = JSONArray(response)
            for (i in 0 until jsonArray.length()){
                Log.w("log", jsonArray.toString())
                val jsonObject = JSONObject(jsonArray.getString(0))

                var text =jsonObject.get("nombre")
                tvNombre.text =jsonObject.get("nombre").toString()
                // tvDescripcion.text =jsonObject.get("descripcionP").toString()
                tvPrecio.text =jsonObject.get("precio").toString()
                Glide.with(this).load(jsonObject.get("img").toString()).into(iVProducto)
            }
        }, Response.ErrorListener { error->
            Log.w("errorLog", error)
        })
        queue.add(stringRequest)

        return inflater.inflate(R.layout.fragment_productos, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Productos.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Productos().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}