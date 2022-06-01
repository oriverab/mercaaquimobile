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
import com.example.mercaaqui.models.productos

class productosAdapter(private val productsList: ArrayList<productos>, private val productsListener: ProductosListener) : RecyclerView.Adapter<productosAdapter.ViewHolder>() {

        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            var nombre: TextView = view.findViewById(R.id.tvnombre)
            var stock: TextView = view.findViewById(R.id.tvstock)
            var precio: TextView = view.findViewById(R.id.tvprecio)
            var img: ImageView = view.findViewById(R.id.ivimg)

            fun bind(product: productos) {
                nombre.text = product.nombre
                precio.text = product.precio.toString()
                stock.text = product.stock.toString()

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.fragment_productos, parent, false)
        )

        override fun getItemCount() = this.productsList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val product = productsList[position]
            try {
                Glide.with(holder.itemView.context)
                    .load(product.img)
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
