package com.crusbel.examen.ui

import com.crusbel.examen.R
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.crusbel.examen.adapters.AnimalAdapter
import com.crusbel.examen.db.entity.AnimalEntity
import com.crusbel.examen.model.AnimalViewModel
import kotlinx.android.synthetic.main.activity_new_animal.*

class NewAnimalActivity : AppCompatActivity() {

    private lateinit var animalViewModel: AnimalViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_animal)

        animalViewModel = ViewModelProvider(this).get(AnimalViewModel::class.java)
        animalViewModel.animales.observe(this, Observer { animales ->
            animales?.let { AnimalAdapter(this).setAnimales(animales) }
        })

        btSave.setOnClickListener {
            val replyIntent = Intent()

            val nombre = etNombre.text.toString()
            val tipo = etTipo.text.toString()
            val descripcion = etDescripcion.text.toString()

            replyIntent.putExtra("nombre",nombre)
            replyIntent.putExtra("tipo",tipo)
            replyIntent.putExtra("descripcion",descripcion)
            print(nombre);
            print(tipo);
            print(descripcion);
            Log.e(nombre)

            animalViewModel.insertAnimal(AnimalEntity(nombre = nombre,tipo = tipo,descripcion = descripcion))

            setResult(Activity.RESULT_OK,replyIntent)

            finish()
        }
    }
}

