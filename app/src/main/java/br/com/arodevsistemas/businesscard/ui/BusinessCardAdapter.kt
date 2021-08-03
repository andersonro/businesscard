package br.com.arodevsistemas.businesscard.ui

import android.content.Context
import android.content.Intent

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import br.com.arodevsistemas.businesscard.R
import br.com.arodevsistemas.businesscard.data.BusinessCard
import br.com.arodevsistemas.businesscard.databinding.ItemBusinessCardBinding
import br.com.arodevsistemas.businesscard.ui.AddBusinessCardActivity.Companion.CARD_ID

class BusinessCardAdapter (val context : Context) : RecyclerView.Adapter<BusinessCardAdapter.ViewHolder>() {

    var list : List<BusinessCard> = emptyList()
    var listenerShare: (View) -> Unit = {}
    var listenerEdit: (View) -> Unit = {}

    inner class ViewHolder(private val binding: ItemBusinessCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : BusinessCard){
            binding.tvName.text = item.nome
            binding.tvName.setTextColor(Color.parseColor(item.fonteColor.toString()))

            binding.tvTelefone.text = item.telefone
            binding.tvTelefone.setTextColor(Color.parseColor(item.fonteColor.toString()))

            binding.tvEmail.text = item.email
            binding.tvEmail.setTextColor(Color.parseColor(item.fonteColor.toString()))

            binding.tvEmpresa.text = item.empresa
            binding.tvEmpresa.setTextColor(Color.parseColor(item.fonteColor.toString()))

            binding.mcvContent.setCardBackgroundColor(Color.parseColor(item.fundoColor.toString()))

            binding.mcvContent.setOnClickListener {
                listenerShare(it)
            }

            binding.btnEdit.setOnClickListener {
                val intent = Intent(context, AddBusinessCardActivity::class.java)
                intent.putExtra(CARD_ID, item.id)
                context.startActivity(intent)
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
