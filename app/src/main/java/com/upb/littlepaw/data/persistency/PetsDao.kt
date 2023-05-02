package com.upb.littlepaw.data.persistency

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.upb.littlepaw.homescreen.adoption.models.PetCard
import kotlinx.coroutines.flow.Flow

@Dao
interface PetsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePets(newList:List<PetCard>)

    @Query("SELECT * FROM PetCard")
    fun getPets(): Flow<List<PetCard>>
}