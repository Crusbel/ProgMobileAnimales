package com.crusbel.examen.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crusbel.examen.R
import com.crusbel.examen.adapters.AnimalAdapter
import com.crusbel.examen.adapters.RvAdapter
import com.crusbel.examen.model.AnimalViewModel
import com.crusbel.examen.model.Model
import kotlinx.android.synthetic.main.fragment_mamiferos.*


class MamiferoFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private lateinit var animalViewModel: AnimalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_mamiferos, container, false)
        val dataList = ArrayList<Model>()
        dataList.add(Model("SONY EQUIPO DE SONIDO HCDEX30", "","Sistema de audio en casa de alta potencia con tecnología Bluetooth y luces LED."))

        // Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.setLayoutManager(LinearLayoutManager(view.context))
        recyclerView!!.setAdapter(AnimalAdapter(view.context))
        animalViewModel = ViewModelProvider(this).get(AnimalViewModel::class.java)
        animalViewModel.animales.observe(viewLifecycleOwner, Observer { animales ->
            animales?.let { AnimalAdapter(view.context).setAnimales(animales) }
        })
        print(animalViewModel.animales)
        /*desde aqui*/

        //val recycler = recyclerview as RecyclerView
        //val adapter = AnimalAdapter(view.context)
        //recyclerView!!.adapter = adapter
        //recyclerView!!.layoutManager = LinearLayoutManager(view.context)
/*
        animalViewModel = ViewModelProvider(this).get(AnimalViewModel::class.java)
        animalViewModel.animales.observe(viewLifecycleOwner, Observer { animales ->
            animales?.let { adapter.setAnimales(animales) }
        })*/

        /*fin*/
        return view
    }

}