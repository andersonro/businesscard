package br.com.arodevsistemas.businesscard.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BusinessCard::class), version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun businessCardDao() : BusinessCardDao

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDataBase(context: Application) : AppDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "business_card_db"
                ).fallbackToDestructiveMigration()
                 .allowMainThreadQueries()
                 .build()

                INSTANCE = instance
                instance
            }
        }
    }
}