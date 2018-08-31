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
    val loginCall = onePageAPI.loginAsync(LoginForm(username, password))
    val loginResponse = loginCall.execute()

    if (!loginResponse.isSuccessful) {
        println("error: ${loginResponse.errorBody()}\n")
        return
    }

    val loginData = loginResponse.body()
    val loggedUser = User(
            loginData!!.data.userId,
            loginData.data.authKey,
            loginData.data.loggedUser.user.first,
            loginData.data.loggedUser.user.last,
            loginData.data.loggedUser.user.company)

    println("loggedUser: $loggedUser\n")
}
