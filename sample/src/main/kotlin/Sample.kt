import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.FileInputStream
import java.io.InputStream
import java.net.HttpURLConnection.HTTP_CREATED
import java.net.HttpURLConnection.HTTP_OK
import java.util.*

/**
 * Created by Cillian Myles on 27/04/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */

const val INPUT_FILE = "sample/src/main/resources/config.properties"
const val KEY_USERNAME = "username"
const val KEY_PASSWORD = "password"

val TYPE_JSON: MediaType = MediaType.parse("application/json")!!
val client = OkHttpClient()

fun main(args: Array<String>) {

    val properties = Properties()
    val input: InputStream = FileInputStream(INPUT_FILE)
    properties.load(input)

    val username = properties.getProperty(KEY_USERNAME)
    val password = properties.getProperty(KEY_PASSWORD)

    println("username: $username\npassword: $password\n")

    val logged = doRequest(
            "https://app.onepagecrm.com/api/v3/login.json",
            "{\"login\":\"$username\",\"password\":\"$password\"}")
}

fun doRequest(url: String, body: String): Boolean? {
    val request: Request = if (body.isEmpty()) get(url) else post(url, body)
    val response = client.newCall(request).execute()

    println("********************")
    println("URL: $url")
    println("BODY: $body")
    println("********************")
    println("CODE: ${response.code()}")
    println("BODY: ${response.body()?.string()}")
    println("********************")
    println()

    return response.code() == HTTP_OK || response.code() == HTTP_CREATED
}

fun get(url: String): Request {
    return Request.Builder()
            .url(url)
            .build()
}

fun post(url: String, body: String): Request {
    return Request.Builder()
            .url(url)
            .post(RequestBody.create(TYPE_JSON, body))
            .build()
}
