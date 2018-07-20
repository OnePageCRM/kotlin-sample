import java.io.FileInputStream
import java.io.InputStream
import java.util.*

/**
 * Created by Cillian Myles on 27/04/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */

const val INPUT_FILE = "sample/src/main/resources/config.properties"
const val KEY_USERNAME = "username"
const val KEY_PASSWORD = "password"

fun main(args: Array<String>) {

    val properties = Properties()
    val input: InputStream = FileInputStream(INPUT_FILE)
    properties.load(input)

    val username = properties.getProperty(KEY_USERNAME)
    val password = properties.getProperty(KEY_PASSWORD)

    println("username: $username\npassword: $password\n")

    val onePageAPI = OnePageAPI.create()
    var call = onePageAPI.loginAsync(LoginForm(username, password))
    var response = call.execute()

    var result: Any?

    result = if (response.isSuccessful) {
        response.body()
    } else {
        response.errorBody()
    }

    println("result: $result\n")
}
