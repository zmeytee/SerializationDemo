package com.example.serializationdemo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.serializationdemo.databinding.FragmentBasicBinding
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

@ExperimentalSerializationApi
class RetrofitFragment: Fragment(R.layout.fragment_basic) {

    //Ignore unknown keys true (else exception)
    private val jsonFormat = Json { ignoreUnknownKeys = true }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentBasicBinding.bind(view)

        // MediaType
        val contentType = MediaType.get("application/json")
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
                // Converter factory by Jake Wharton
            .addConverterFactory(jsonFormat.asConverterFactory(contentType))
            .build()
        val service = retrofit.create(Service::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            val response = service.fetchPosts()
            binding.textView.text = response.body()?.joinToString("\n")
        }
    }

}

interface Service {

    @GET("/posts")
    suspend fun fetchPosts(): Response<List<Post>>
}

@Serializable
data class Post(
    val id: Long,
    val title: String
)