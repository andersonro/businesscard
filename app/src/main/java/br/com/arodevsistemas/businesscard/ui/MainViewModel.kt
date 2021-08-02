package br.com.arodevsistemas.businesscard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.arodevsistemas.businesscard.data.BusinessCard
import br.com.arodevsistemas.businesscard.data.BusinessCardRepository
import java.lang.IllegalArgumentException

class MainViewModel(private val businessCardRepository: BusinessCardRepository): ViewModel() {

    fun insert(businessCard: BusinessCard)  = businessCardRepository.insert(businessCard)

    fun update(businessCard: BusinessCard)  = businessCardRepository.update(businessCard)

    fun getAll() : LiveData<List<BusinessCard>> {
        return  businessCardRepository.getAll()
    }

    fun getBusinessCard(id: Long) : BusinessCard = businessCardRepository.getBusinessCardById(id)
}

class MainViewModelFactory(private val repository: BusinessCardRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }else {
            throw IllegalArgumentException("Unlnown ViewModel class")
        }

    }


}