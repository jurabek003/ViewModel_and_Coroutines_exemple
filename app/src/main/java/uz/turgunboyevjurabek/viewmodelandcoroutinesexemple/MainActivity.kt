package uz.turgunboyevjurabek.viewmodelandcoroutinesexemple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.adapter.RvAdapter
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.databinding.ActivityMainBinding
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.vm.ViewModel

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var rvAdapter: RvAdapter
    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

         viewModel=ViewModelProvider(this).get(ViewModel::class.java)
         viewModel.getAllClients().observe(this){it->
             rvAdapter=RvAdapter(it)
             binding.rv.adapter=rvAdapter
             rvAdapter.notifyDataSetChanged()
        }

    }
}