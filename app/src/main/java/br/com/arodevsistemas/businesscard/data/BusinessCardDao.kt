package br.com.arodevsistemas.businesscard.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BusinessCardDao {

    @Query("SELECT * FROM business_card")
    fun getAll(): LiveData<List<BusinessCard>>

    @Query("SELECT * FROM business_card WHERE id = :id")
    fun getByBusinessCardId(id : Long): BusinessCard

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(businessCard: BusinessCard) : Long

    @Update
    fun update(businessCard: BusinessCard)
}