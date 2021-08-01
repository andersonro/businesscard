package br.com.arodevsistemas.businesscard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import br.com.arodevsistemas.businesscard.App
import br.com.arodevsistemas.businesscard.R
import br.com.arodevsistemas.businesscard.data.BusinessCard
import br.com.arodevsistemas.businesscard.databinding.ActivityAddBusinessCardBinding
import br.com.arodevsistemas.businesscard.databinding.ActivityMainBinding

class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        insertListener()
    }

    private fun insertListener(){
        binding.btnClose.setOnClickListener {
            finish()
        }
        binding.btnCardSave.setOnClickListener {

            val businessCard = BusinessCard(
                id = 0,
                nome = binding.tilName.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                telefone = binding.tilTelephone.editText?.text.toString(),
                empresa = binding.tilBusiness.editText?.text.toString(),
                fundoColor = binding.tilColor.editText?.text.toString().toUpperCase(),
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, getString(R.string.label_save_card_success), Toast.LENGTH_LONG).show()

            finish()
        }
    }
}