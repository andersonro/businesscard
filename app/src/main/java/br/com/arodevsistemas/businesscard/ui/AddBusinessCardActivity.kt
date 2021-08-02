package br.com.arodevsistemas.businesscard.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import br.com.arodevsistemas.businesscard.App
import br.com.arodevsistemas.businesscard.data.BusinessCard
import br.com.arodevsistemas.businesscard.databinding.ActivityAddBusinessCardBinding
import br.com.arodevsistemas.businesscard.util.text
import com.nvt.color.ColorPickerDialog

class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private var intExtra: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(intent.hasExtra(CARD_ID)){
            intExtra = intent.getLongExtra(CARD_ID, 0)
            Log.e("BUSINESS_ID", intExtra.toString())
            getBusinessCard(intExtra)
        }
        insertListener()
    }

    private fun insertListener(){
        binding.btnClose.setOnClickListener {
            finish()
        }
        binding.btnCardSave.setOnClickListener {

            val businessCard = BusinessCard(
                id = intExtra,
                nome = binding.tilName.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                telefone = binding.tilTelephone.editText?.text.toString(),
                empresa = binding.tilBusiness.editText?.text.toString(),
                fundoColor = binding.tilColor.editText?.text.toString().toUpperCase(),
            )

            if (intExtra > 0) {
                mainViewModel.update(businessCard)
            }else {
                mainViewModel.insert(businessCard)
            }

            finish()
        }

        binding.btnColors.setOnClickListener {
            openColorPicker()
        }
    }

    private fun getBusinessCard(id: Long){
        mainViewModel.getBusinessCard(intExtra)?.let {

            Log.e("BUSINESS", it.toString())

            binding.tilName.text = it.nome
            binding.tilTelephone.text = it.telefone
            binding.tilEmail.text = it.email
            binding.tilBusiness.text = it.empresa
            binding.tilColor.text = it.fundoColor
        }

    }

    fun openColorPicker() {
        val colorPicker = ColorPickerDialog(
            this,
            Color.BLACK, // color init
            true, // true is show alpha
            object : ColorPickerDialog.OnColorPickerListener {
                override fun onCancel(dialog: ColorPickerDialog?) {
                    // handle click button Cancel
                }

                override fun onOk(dialog: ColorPickerDialog?, colorPicker: Int) {
                    // handle click button OK
                    Log.e("OK", colorPicker.toString())
                }
            })
        colorPicker.show()
    }

    companion object {
        const val CARD_ID = "card_id"
    }
}