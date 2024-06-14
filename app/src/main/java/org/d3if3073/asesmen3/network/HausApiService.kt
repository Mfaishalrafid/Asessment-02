package org.d3if3073.asesmen3.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3073.asesmen3.model.Minuman2
import org.d3if3073.asesmen3.model.OpStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

private const val BASE_URL = "https://faishalrafid.my.id/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MinumanApiService {
    @GET("json.php")
    suspend fun getMinuman(
        @Query("auth") userId: String
    ): List<Minuman2>

    @Multipart
    @POST("json.php")
    suspend fun postMinuman(
        @Part("auth") userId: String,
        @Part("nama") nama: RequestBody,
        @Part("harga") harga: RequestBody,
        @Part("jenis") jenis: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("json.php")
    suspend fun deleteMinuman(
        @Query("auth") userId: String,
        @Query("id") id: String
    ): OpStatus
}

object MinumanApi {
    val service: MinumanApiService by lazy {
        retrofit.create(MinumanApiService::class.java)
    }
    fun getMinumanUrl(gambar: String): String {
        return "$BASE_URL$gambar"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }