package co.id.git.data.api

import co.id.git.data.vo.DetailUser
import co.id.git.data.vo.ResponseUser
import co.id.git.data.vo.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    @Headers("Authorization:")
    fun getSearchData(
        @Query("q") query: String
    ) : Call<ResponseUser>

    @GET("users/{username}")
    @Headers("Authorization:")
    fun getDetailUser(
        @Path("username") username : String
    ) : Call<DetailUser>

    @GET("users/{username}/followers")
    @Headers("Authorization:")
    fun getFollowersData(
        @Path("username") username : String,
        @Query("page") page : String
    ) : Call<List<User>>

    @GET("users/{username}/following")
    @Headers("Authorization:")
    fun getFollowingData(
        @Path("username") username : String,
        @Query("page") page : String
    ) : Call<List<User>>
}