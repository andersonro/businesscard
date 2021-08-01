package br.com.arodevsistemas.businesscard.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BusinessCardDao {

    @Query("SELECT * FROM business_card")
    fun getAll(): LiveData<List<BusinessCard>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(businessCard: BusinessCard) : Long
    //suspend fun insert(businessCard: BusinessCard) : Long
}