import okhttp3.Credentials
import okhttp3.Interceptor
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

        // URLs
        private const val BASE_URL = "https://app.onepagecrm.com"
        private const val API_URL = "$BASE_URL/api/v3/"

        // Headers
        private const val USER_AGENT_HEADER = "User-Agent"
        private val SYSTEM_USER_AGENT = System.getProperty("http.agent")
        private val USER_AGENT = if (SYSTEM_USER_AGENT.isNullOrEmpty()) "Java/1.8" else SYSTEM_USER_AGENT
        private const val SOURCE_HEADER = "X-OnePageCRM-SOURCE"
        private const val SOURCE = "kotlin-sample"
        private const val AUTHORIZATION_HEADER = "Authorization"

        // Authentication
        var userId: String = ""
        var authKey: String = ""

        fun create(): OnePageAPI {
            val retrofit = Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client())
                    .build()
            return retrofit.create(OnePageAPI::class.java)
        }

        private fun client(): OkHttpClient {
            return OkHttpClient.Builder()
                    .addInterceptor(logger())
                    .addInterceptor(headers())
                    .build()
        }

        private fun logger(): Interceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }

        private fun headers(): Interceptor {
            return Interceptor {
                val request = it.request().newBuilder()
                        .addHeader(USER_AGENT_HEADER, USER_AGENT)
                        .addHeader(SOURCE_HEADER, SOURCE)
                        .addHeader(AUTHORIZATION_HEADER, authentication())
                        .build()
                println("$USER_AGENT_HEADER: $USER_AGENT")
                println("$SOURCE_HEADER: $SOURCE")
                println("$AUTHORIZATION_HEADER: ${authentication()}")
                it.proceed(request)
            }
        }

        fun setAuthData(userId: String, authKey: String) {
            this.userId = userId
            this.authKey = authKey
        }

        private fun authentication(): String {
            return if (userId.isNotEmpty() && authKey.isNotEmpty()) Credentials.basic(userId, authKey) else ""
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

    @POST("contacts.json")
    fun contacts(@Body contactForm: ContactForm): ContactResponse

    @POST("contacts.json")
    fun contactsAsync(@Body contactForm: ContactForm): Call<ContactResponse>
}
