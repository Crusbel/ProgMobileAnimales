package com.crusbel.examen.recycler

import android.content.Context
import android.text.Layout
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crusbel.examen.R


class RecyclerViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    val viewnombre: TextView
    val viewprecio: TextView
    val viewdescripcion: TextView
    val viewimagen: ImageView
    val boxDetalle: LinearLayout

    init {
        viewnombre = itemView.findViewById(R.id.txtNombre)
        viewprecio = itemView.findViewById(R.id.txtPrecio)
        viewdescripcion = itemView.findViewById(R.id.txtDescripcion)
        viewimagen = itemView.findViewById(R.id.imgProduct)
        boxDetalle = itemView.findViewById(R.id.boxDetalle)

    }
}