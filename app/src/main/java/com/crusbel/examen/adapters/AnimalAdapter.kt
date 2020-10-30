package com.crusbel.examen.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.crusbel.examen.R
import com.crusbel.examen.db.entity.AnimalEntity
import com.crusbel.examen.recycler.RecyclerViewHolder
import kotlinx.android.synthetic.main.card_animales.view.*

class AnimalAdapter (val context: Context)
    :RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>(){

    var animales = emptyList<AnimalEntity>()
    var inflater:LayoutInflater = LayoutInflater.from(context)

    override fun getItemViewType(position: Int): Int {
        return R.layout.card_animales
    }
    override fun getItemCount() = animales.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return AnimalViewHolder(view)
    }
            //= AnimalViewHolder(inflater.inflate(R.layout.card_animales,parent,false))

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int)
    {
        holder.viewnombre.text = animales[position].nombre
        holder.viewtipo.text = animales[position].tipo
        holder.viewdescripcion.text = animales[position].descripcion
        Glide.with(context)
            .load("https://assets.stickpng.com/images/584ab294e583a46e8c837a30.png")
            .into(holder.viewimagen);
        holder.viewimagen.setOnClickListener {
            if (holder.boxDetalle.visibility == LinearLayout.VISIBLE){
                holder.boxDetalle.visibility = LinearLayout.INVISIBLE
            } else{
                holder.boxDetalle.visibility = LinearLayout.VISIBLE
            }
        }
        holder.bind(animales[position])
    }
            //= holder.bind(animales[position])

    internal fun setAnimales(animals:List<AnimalEntity>){
        this.animales = animals
        notifyDataSetChanged()
    }
    class AnimalViewHolder(item:View):RecyclerView.ViewHolder(item){
        val viewnombre: TextView
        val viewtipo: TextView
        val viewdescripcion: TextView
        val viewimagen: ImageView
        val boxDetalle: LinearLayout

        init {
            viewnombre = item.findViewById(R.id.txtNombre)
            viewtipo = item.findViewById(R.id.txtTipo)
            viewdescripcion = item.findViewById(R.id.txtDescripcion)
            viewimagen = item.findViewById(R.id.imgAnimal)
            boxDetalle = item.findViewById(R.id.boxDetalle)

        }
        fun bind(animal:AnimalEntity) {
            itemView.apply {
                txtNombre.text = animal.nombre
                txtTipo.text = animal.tipo
                txtDescripcion.text = animal.descripcion
            }
        }
    }
    class RecyclerViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val viewnombre: TextView
        val viewtipo: TextView
        val viewdescripcion: TextView
        val viewimagen: ImageView
        val boxDetalle: LinearLayout

        init {
            viewnombre = itemView.findViewById(R.id.txtNombre)
            viewtipo = itemView.findViewById(R.id.txtTipo)
            viewdescripcion = itemView.findViewById(R.id.txtDescripcion)
            viewimagen = itemView.findViewById(R.id.imgAnimal)
            boxDetalle = itemView.findViewById(R.id.boxDetalle)

        }
    }
}