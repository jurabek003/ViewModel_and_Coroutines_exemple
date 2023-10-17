package uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.madels.GetClients
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.network.ApiClient

class ViewModel:ViewModel() {
    val  liveData=MutableLiveData<GetClients>()
    init {
        GlobalScope.launch {
            val apiServis=ApiClient.getApiServis()
            val users = apiServis.getAllClients()
            liveData.postValue(users)
        }

    }

    fun getAllClients():MutableLiveData<GetClients>{
        return liveData
    }

}