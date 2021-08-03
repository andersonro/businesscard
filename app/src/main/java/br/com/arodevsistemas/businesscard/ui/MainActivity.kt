package br.com.arodevsistemas.businesscard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import br.com.arodevsistemas.businesscard.App
import br.com.arodevsistemas.businesscard.R
import br.com.arodevsistemas.businesscard.databinding.ActivityMainBinding
import br.com.arodevsistemas.businesscard.util.Image


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    private val adapter by lazy { BusinessCardAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvList.adapter = adapter
        getAllBusinessCard()
        insertListener()
    }

    private fun insertListener(){
        binding.fbNewCard.setOnClickListener {
            var intent = Intent(this, AddBusinessCardActivity::class.java)
            startActivity(intent)
        }

        adapter.listenerShare = { card->
            card.findViewById<ImageButton>(R.id.btn_edit).isVisible = false
            Image.share(this, card)
            card.findViewById<ImageButton>(R.id.btn_edit).isVisible = true
        }
    }

    private fun getAllBusinessCard() {

        mainViewModel.getAll().observe(this, Observer {
            adapter.list = it
            adapter.notifyDataSetChanged()
        })

    }
}