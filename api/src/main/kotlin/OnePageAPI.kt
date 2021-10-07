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

    @GET("contacts.json")
    fun contacts(): ContactsResponse

    @GET("contacts.json")
    fun contactsAsync(): Call<ContactsResponse>

    @POST("contacts.json")
    fun contacts(@Body contactForm: ContactForm): ContactResponse

    @POST("contacts.json")
    fun contactsAsync(@Body contactForm: ContactForm): Call<ContactResponse>

    companion object Factory {

        // URLs
        private const val BASE_URL = "https://app.onepagecrm.com"
        private const val API_URL = "$BASE_URL/api/v3/"

        // Headers
        private const val USER_AGENT_HEADER = "User-Agent"
        private const val SOURCE_HEADER = "X-OnePageCRM-SOURCE"
        private const val SOURCE = "kotlin-sample"
        private const val AUTHORIZATION_HEADER = "Authorization"

        // Authentication
        var userId: String = ""
        var authKey: String = ""

        // Singleton
        fun create(): OnePageAPI {
            val retrofit = Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client())
                    .build()
            return retrofit.create(OnePageAPI::class.java)
        }

        fun setAuthData(userId: String, authKey: String) {
            this.userId = userId
            this.authKey = authKey
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
                        .addHeader(USER_AGENT_HEADER, userAgent())
                        .addHeader(SOURCE_HEADER, SOURCE)
                        .addHeader(AUTHORIZATION_HEADER, authentication())
                        .build()
                println("$USER_AGENT_HEADER: ${userAgent()}")
                println("$SOURCE_HEADER: $SOURCE")
                println("$AUTHORIZATION_HEADER: ${authentication()}")
                it.proceed(request)
            }
        }

        private fun userAgent(): String {
            val systemAgent = System.getProperty("http.agent")
            return if (systemAgent.isNullOrEmpty()) "Java/1.8" else systemAgent
        }

        private fun authentication(): String {
            return if (userId.isNotEmpty() && authKey.isNotEmpty()) Credentials.basic(userId, authKey) else ""
        }
    }
}
