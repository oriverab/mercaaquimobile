package com.example.mercaaqui.ui.productos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mercaaqui.R
import com.example.mercaaqui.adapters.ProductosListener
import com.example.mercaaqui.adapters.productosAdapter
import org.json.JSONException
import org.json.JSONArray
import org.json.JSONObject


class Productos : Fragment(), ProductosListener {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var rlProductsList: RelativeLayout
    private lateinit var pgb:ProgressBar
    private lateinit var productsList: ArrayList<JSONObject>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ll = inflater.inflate(R.layout.fragment_productos, container, false)




        val url = "https://mercaaqui.tk/ListaProductos"

        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            this.productsList = ArrayList()

            try {
                var i = 0
                val l = jsonArray.length()
                Log.d("menL", l.toString())
                while (i < l) {
                    productsList.add(jsonArray[i] as JSONObject)
                    i++
                    Log.d("jsonArray", this.productsList.toString())
                }
                this.recycler.adapter =productosAdapter(this.productsList, this)
                this.viewAlpha.visibility= View.INVISIBLE
                this.pgb.visibility =View.INVISIBLE
            } catch (e: JSONException) {
            }

        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.products_recycler)
        this.viewAlpha = ll.findViewById(R.id.view_productsList)
        this.rlProductsList = ll.findViewById(R.id.rl_ProductsList)
        this.pgb=ll.findViewById(R.id.pgbar_productsList)

        return ll;
    }

    override fun onProductosClicked(Productos: JSONObject, position: Int) {
        TODO("Not yet implemented")
    }
}


