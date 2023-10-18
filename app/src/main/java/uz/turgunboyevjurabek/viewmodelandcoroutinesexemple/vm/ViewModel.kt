package uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.madelss.Clients.CLientPost
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.madelss.Clients.GetClients
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.madelss.Clients.GetClientsItem
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.network.ApiClient

class ViewModel:ViewModel() {
    val  liveDataGetClients=MutableLiveData<GetClients>()
    val liveDataPostClient=MutableLiveData<GetClientsItem>()


        val apiServis=ApiClient.getApiServis()

    fun getAllClients():MutableLiveData<GetClients>{
        GlobalScope.launch {
            val users = apiServis.getAllClients()
            liveDataGetClients.postValue(users)
        }
        return liveDataGetClients
    }
    fun postClient(getClientsItem: GetClientsItem?):MutableLiveData<GetClientsItem>{
        GlobalScope.launch {
            val postUser= getClientsItem?.let { apiServis.postClient(it) }
            liveDataPostClient.postValue(postUser)
        }
        return liveDataPostClient
    }

}