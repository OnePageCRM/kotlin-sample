import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Cillian Myles on 28/04/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */

interface OnePageAPI {

    companion object Factory {

        private const val BASE_URL = "https://app.onepagecrm.com"
        private const val API_URL = "$BASE_URL/api/v3/"

        fun create(): OnePageAPI {

            val retrofit = Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(logger())
                    .build()

            return retrofit.create(OnePageAPI::class.java)
        }

        private fun logger(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder().addInterceptor(interceptor).build()
        }
    }

    @POST("login.json")
    fun login(@Body loginForm: LoginForm): LoginResponse

    @POST("login.json")
    fun loginAsync(@Body loginForm: LoginForm): Call<LoginResponse>

    @GET("contacts.json")
    fun contacts(): ContactsResponse

    @GET("contacts.json")
    fun contactsAsync(): Call<ContactsResponse>
}
