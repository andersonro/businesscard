package br.com.arodevsistemas.businesscard

import android.app.Application
import br.com.arodevsistemas.businesscard.data.AppDatabase
import br.com.arodevsistemas.businesscard.data.BusinessCardRepository

class App : Application() {

    val database by lazy { AppDatabase.getDataBase(this) }
    val repository by lazy { BusinessCardRepository(database.businessCardDao()) }
}