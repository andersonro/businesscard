package br.com.arodevsistemas.businesscard.ui

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.arodevsistemas.businesscard.data.BusinessCard
import br.com.arodevsistemas.businesscard.databinding.ItemBusinessCardBinding

class BusinessCardAdapter (val context : Context) : RecyclerView.Adapter<BusinessCardAdapter.ViewHolder>() {

    var list : List<BusinessCard> = emptyList()
    var listenerShare: (View) -> Unit = {}

    inner class ViewHolder(private val binding: ItemBusinessCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : BusinessCard){
            binding.tvName.text = item.nome
            binding.tvTelefone.text = item.telefone
            binding.tvEmail.text = item.email
            binding.tvEmpresa.text = item.empresa
            binding.mcvContent.setCardBackgroundColor(Color.parseColor(item.fundoColor.toString()))
            binding.mcvContent.setOnClickListener {
                listenerShare(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBusinessCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemBusinessCard: BusinessCard = list[position]
        holder.bind(itemBusinessCard)
    }

    override fun getItemCount(): Int = list.size

}
