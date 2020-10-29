package com.crusbel.examen.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.crusbel.examen.db.AppRoomDatabase
import com.crusbel.examen.db.entity.AnimalEntity
import com.crusbel.examen.repository.AnimalRepository
import kotlinx.coroutines.launch

class AnimalViewModel(application: Application):AndroidViewModel(application){

    val animales:LiveData<List<AnimalEntity>>
    private val animalRepo:AnimalRepository

    init {
        val animalDao = AppRoomDatabase.getDatabase(application).animalDao()
        animalRepo = AnimalRepository(animalDao)
        animales = animalRepo.animales
    }

    fun insertAnimal(animal:AnimalEntity) = viewModelScope.launch {
        animalRepo.insertAnimal(animal)
    }

}