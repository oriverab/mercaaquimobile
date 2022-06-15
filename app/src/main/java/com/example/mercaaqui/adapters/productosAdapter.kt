package com.example.mercaaqui.adapters
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mercaaqui.R
import org.json.JSONObject

class productosAdapter(private val productsList: ArrayList<JSONObject>, private val productsListener: ProductosListener) : RecyclerView.Adapter<productosAdapter.ViewHolder>() {

        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            var nombre: TextView = view.findViewById(R.id.tvnombre)
            var stock: TextView = view.findViewById(R.id.tvstock)
            var precio: TextView = view.findViewById(R.id.tvprecio)
            var img: ImageView = view.findViewById(R.id.ivimg)

            fun bind(product: JSONObject) {
                nombre.text = product.getString("nombre")
                precio.text = product.getString("precio")
                stock.text = product.getString("stock")

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_productos, parent, false)
        )

        override fun getItemCount() = this.productsList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val product = productsList[position]
            try {
                Glide.with(holder.itemView.context)
                    .load(product.get("img"))
                    .into(holder.img)
                holder.bind(product)

                holder.itemView.setOnClickListener{
                    productsListener.onProductosClicked(product, position)
                }

            }catch (e: Exception){
                Log.w("ProductImagen", "No cargó la imagen")
            }

        }
    }
