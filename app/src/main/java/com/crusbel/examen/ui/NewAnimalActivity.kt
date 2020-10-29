package com.crusbel.examen.ui

import com.crusbel.examen.R
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new_animal.*

class NewAnimalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_animal)

        btSave.setOnClickListener {
            val replyIntent = Intent()

            val nombre = etNombre.text.toString()
            val tipo = etTipo.text.toString()
            val descripcion = etDescripcion.text.toString()

            replyIntent.putExtra("nombre",nombre)
            replyIntent.putExtra("tipo",tipo)
            replyIntent.putExtra("descripcion",descripcion)

            setResult(Activity.RESULT_OK,replyIntent)

            finish()
        }
    }
}

