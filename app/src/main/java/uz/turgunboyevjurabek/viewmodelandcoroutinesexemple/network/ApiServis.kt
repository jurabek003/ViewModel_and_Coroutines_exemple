package uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.madelss.Clients.CLientPost
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.madelss.Clients.GetClients
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.madelss.Clients.GetClientsItem
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.madelss.Clients.PostClientItem

interface ApiServis {
    @GET("clientlar/")
    suspend fun getAllClients(): GetClients

    @POST("clientlar/")
    suspend fun postClient(@Body postClientItem: PostClientItem):PostClientItem
}