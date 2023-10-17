package uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val BASE_URL="https://api111.pythonanywhere.com/"


    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
    fun getApiServis():ApiServis{
        return getRetrofit().create(ApiServis::class.java)
    }

}