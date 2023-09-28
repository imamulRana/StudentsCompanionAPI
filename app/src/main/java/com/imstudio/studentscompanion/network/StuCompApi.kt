package com.imstudio.studentscompanion.network

import com.imstudio.studentscompanion.model.Batch
import com.imstudio.studentscompanion.model.Bus
import com.imstudio.studentscompanion.model.Classes
import com.imstudio.studentscompanion.model.Department
import com.imstudio.studentscompanion.model.Section
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.File


const val BASE_URL = "https://studentcomp.onrender.com/api/"

val cacheSize = (5 * 1024 * 1024).toLong()

interface StuCompApi {
    @GET("department")
    suspend fun getAllDept(): List<Department>

    @GET("department/{department_id}/batch")
    suspend fun getAllBatch(@Path("department_id") departmentId: Int): List<Batch>

    @GET("department/{department_id}/batch/{batch_id}/section")
    suspend fun getAllSection(
        @Path("department_id") departmentId: Int,
        @Path("batch_id") batchId: Int
    ): List<Section>

    @GET("department/{department_id}/batch/{batch_id}/section/{section_id}/class")
    suspend fun getAllClass(
        @Path("department_id") departmentId: Int,
        @Path("batch_id") batchId: Int,
        @Path("section_id") sectionId: Int,
    ): List<Classes>

    @GET("bus/1/route")
    suspend fun getUpRouteData(): List<Bus>

    @GET("bus/2/route")
    suspend fun getDownRouteData(): List<Bus>
}

object RetrofitInstance {
    private val okHttpClient =
        OkHttpClient.Builder()
            .cache(Cache(File.createTempFile("StuCompResponse", null), cacheSize))
            .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val retrofitService: StuCompApi by lazy {
        retrofit.create(StuCompApi::class.java)
    }
}
