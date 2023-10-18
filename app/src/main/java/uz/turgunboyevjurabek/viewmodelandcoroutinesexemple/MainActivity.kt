package uz.turgunboyevjurabek.viewmodelandcoroutinesexemple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.adapter.RvAdapter
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.databinding.ActivityMainBinding
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.databinding.DialogPostBinding
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.madelss.Clients.PostClientItem
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.vm.ViewModel
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var rvAdapter: RvAdapter
    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

         viewModel=ViewModelProvider(this).get(ViewModel::class.java)

        getAllClients()

        binding.btnFloating.setOnClickListener {
            dialogShow()
        }

    }

    private fun getAllClients() {

        viewModel.getAllClients().observe(this){it->
            rvAdapter=RvAdapter(it)
            binding.rv.adapter=rvAdapter
            rvAdapter.notifyDataSetChanged()
        }
    }

    private fun dialogShow() {
        val itemDialog=DialogPostBinding.inflate(layoutInflater)
        val dialog=MaterialAlertDialogBuilder(this).create()
        dialog.setView(itemDialog.root)

        dialog.show()
        itemDialog.btnDialogSave.setOnClickListener {

            if (!itemDialog.edtDialogName.text.isNullOrEmpty() && !itemDialog.edtDialogLastName.text.isNullOrEmpty()
                && !itemDialog.edtDialogManzil.text.isNullOrEmpty() && !itemDialog.edtDialogTel.text.isNullOrEmpty()
                && !itemDialog.edtDialogSumma.text.isNullOrEmpty())
            {
                val postClientItem=PostClientItem(
                    itemDialog.edtDialogLastName.text.toString(),itemDialog.edtDialogName.text.toString(),
                    itemDialog.edtDialogManzil.text.toString(),itemDialog.edtDialogTel.text.toString(),
                    itemDialog.edtDialogSumma.text.toString().toInt())

                postClientFun(postClientItem)
            }
            dialog.cancel()
        }
    }

    private fun postClientFun(postClientItem: PostClientItem) {
        viewModel.postClient(postClientItem).observe(this){it->
            Toast.makeText(this, "Saqlandi", Toast.LENGTH_SHORT).show()
            getAllClients()

        }
    }

}

