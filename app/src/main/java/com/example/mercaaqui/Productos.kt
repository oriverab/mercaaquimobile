package com.example.mercaaqui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONArray
import org.json.JSONObject
import kotlinx.android.synthetic.main.fragment_productos.*


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
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha:View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlProductsList: RelativeLayout
    private lateinit var productsList: ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ll = inflater.inflate(R.layout.fragment_productos, container, false)

        val url = "http://192.168.3.218/mercaaqui/app/Http/listaproductos.php"

        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            this.productsList = ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    productsList.add(jsonArray[i] as Product)
                    i++
                }
            } catch (e: JSONException) {
            }
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.products_recycler)
        this.viewAlpha = ll.findViewById(R.id.view_productsList)
        this.pgbar = ll.findViewById(R.id.pgbar_productsList)
        this.rlProductsList = ll.findViewById(R.id.rl_ProductsList)

        return ll;
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