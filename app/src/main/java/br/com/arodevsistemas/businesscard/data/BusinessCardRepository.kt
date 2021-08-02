package br.com.arodevsistemas.businesscard.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BusinessCardRepository(private val dao: BusinessCardDao) {

    fun insert(businessCard: BusinessCard) : Long = dao.insert(businessCard)

    fun update(businessCard: BusinessCard) = dao.update(businessCard)

    fun getAll() = dao.getAll()

    fun getBusinessCardById(id: Long) = dao.getByBusinessCardId(id)
}