package com.kirt.finalbibliouut.Databases.API

import com.kirt.finalbibliouut.Databases.BookCategory
import com.kirt.finalbibliouut.Databases.Bookdata
import com.kirt.finalbibliouut.Databases.Consulte
import com.kirt.finalbibliouut.Databases.Telecharge
import com.kirt.finalbibliouut.Databases.These
import com.kirt.finalbibliouut.Databases.Users
import com.kirt.finalbibliouut.Databases.access
import com.kirt.finalbibliouut.Databases.key
import com.kirt.finalbibliouut.Databases.refresh
import com.kirt.finalbibliouut.Databases.user
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("apis/token/")
    suspend fun Login(@Body user: user): Response<key>
    @GET("apis/categories/")
    suspend fun getCategories(@Header("Authorization")access : String): Response<MutableList<BookCategory>>
    @GET("apis/livres/")
    suspend fun getLivres(@Header("Authorization")access : String): Response<MutableList<Bookdata>>

    @POST("apis/token/refresh/")
    suspend fun refreshToken(@Body refresh: refresh):Response<access>
    @GET("apis/singleuser/")
    suspend fun getCurrentUser(@Header("Authorization")access : String): Response<MutableList<Users>>
    @GET("apis/consulted/")
    suspend fun getConsultes(@Header("Authorization")access : String ) : Response<MutableList<Consulte>>
    @POST("apis/consultes/")
    suspend fun postConsultes(@Header("Authorization")access : String ,@Body consulte: Consulte) : Response<Consulte>

    @GET("apis/theses/")
    suspend fun getTheses(@Header("Authorization")access : String) : Response<MutableList<These>>
    @POST("apis/telecharges/")
    suspend fun postTelecharges(@Header("Authorization")access : String, @Body telecharge : Telecharge) : Response<Telecharge>
    @GET("apis/telecharged/")
    suspend fun getTelecharges(@Header("Authorization")access : String) : Response<MutableList<Telecharge>>
}