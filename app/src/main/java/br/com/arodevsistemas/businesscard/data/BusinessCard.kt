package br.com.arodevsistemas.businesscard.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "business_card")
data class BusinessCard(
    @PrimaryKey(autoGenerate = true) var id : Long = 0,
    var nome : String = "",
    var empresa: String = "",
    var telefone: String = "",
    var email: String = "",
    @ColumnInfo(name = "fundo_color") var fundoColor: String = ""
) : Serializable