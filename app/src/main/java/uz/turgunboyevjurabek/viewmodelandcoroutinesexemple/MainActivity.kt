package uz.turgunboyevjurabek.viewmodelandcoroutinesexemple

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.view.View
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

        aboutNetwork()

        binding.imgRefresh.setOnClickListener{
            aboutNetwork()
        }

    }

    private fun aboutNetwork() {
        binding.progressCircular.visibility=View.VISIBLE
        if (isNetworkAvailable(this)) {

            setipFabButtons()
           network()

        }else{
            binding.thtFail.visibility=View.VISIBLE
            binding.btnFloating.visibility=View.GONE
            binding.imgRefresh.visibility=View.VISIBLE
            binding.progressCircular.visibility=View.GONE
        }
    }

    private fun setipFabButtons() {
        binding.btnFloating.shrink()

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
    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
    private fun network(){
        binding.thtFail.visibility=View.GONE
        binding.imgRefresh.visibility=View.GONE
        binding.progressCircular.visibility=View.GONE

        viewModel=ViewModelProvider(this).get(ViewModel::class.java)
        getAllClients()

        binding.btnFloating.setOnClickListener {

            if (binding.btnFloating.isExtended){
                binding.btnFloating.shrink()
            }else{
                binding.btnFloating.extend()
            }

            dialogShow()
        }
    }
}


