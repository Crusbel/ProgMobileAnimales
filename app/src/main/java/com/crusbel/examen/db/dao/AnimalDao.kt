package com.crusbel.examen.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crusbel.examen.db.entity.AnimalEntity

@Dao
interface AnimalDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAnimal(animal: AnimalEntity)

    @Query("SELECT * FROM animal")
    fun getAnimales():LiveData<List<AnimalEntity>>

    @Query("DELETE FROM animal")
    fun deleteAnimales()
}