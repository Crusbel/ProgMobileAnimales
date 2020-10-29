package com.crusbel.examen.repository

import androidx.lifecycle.LiveData
import com.crusbel.examen.db.dao.AnimalDao
import com.crusbel.examen.db.entity.AnimalEntity

class AnimalRepository (private val animalDao:AnimalDao){

    val animales:LiveData<List<AnimalEntity>> = animalDao.getAnimales()

    suspend fun insertAnimal(animal:AnimalEntity){
        animalDao.insertAnimal(animal)
    }
}