package com.example.mercaaqui

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.mercaaqui.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.fragment_productos.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = "http://192.168.43.135/mercaaqui/app/Http/listaproductos.php"
        val queue = Volley.newRequestQueue(this)
        val tvNombre = findViewById<TextView>(R.id.tvnombre)
       // val tvDescripcion= findViewById<TextView>(R.id.tVdescripcionp)
        val tvPrecio =findViewById<TextView>(R.id.tvprecio)
        val iVProducto =findViewById<ImageView>(R.id.ivimg)
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener { response ->
            val jsonArray =JSONArray(response)
            for (i in 0 until jsonArray.length()){
                Log.w("log", jsonArray.toString())
                val jsonObject = JSONObject(jsonArray.getString(0))

                var text =jsonObject.get("nombre")
                tvNombre.text =jsonObject.get("nombre").toString()
              // tvDescripcion.text =jsonObject.get("descripcionP").toString()
               tvPrecio.text =jsonObject.get("precio").toString()
                Glide.with(this).load(jsonObject.get("img").toString()).into(iVProducto)
            }
        },Response.ErrorListener { error->
            Log.w("errorLog", error)
        })
        queue.add(stringRequest)



       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val navView: BottomNavigationView = binding.navView


        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.productos
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}