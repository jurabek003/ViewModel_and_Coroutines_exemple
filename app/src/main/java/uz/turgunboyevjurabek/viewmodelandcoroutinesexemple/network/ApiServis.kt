package uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.network

import retrofit2.http.GET
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.madels.GetClients

interface ApiServis {
    @GET("clientlar/")
    suspend fun getAllClients():GetClients
}