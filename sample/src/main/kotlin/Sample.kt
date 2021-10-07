import java.io.FileInputStream
import java.io.InputStream
import java.util.*

/**
 * Created by Cillian Myles on 27/04/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */

const val INPUT_FILE = "sample/src/main/resources/config.properties"
const val KEY_USER_ID = "userId"
const val KEY_AUTH_KEY = "authKey"

fun main(args: Array<String>) {

    val properties = Properties()
    val input: InputStream = FileInputStream(INPUT_FILE)
    properties.load(input)

    val userId = properties.getProperty(KEY_USER_ID)
    val authKey = properties.getProperty(KEY_AUTH_KEY)

    println("userId: $userId\nauthKey: $authKey\n")

    // Initialize
    val onePageAPI = OnePageAPI.create()
    // Set up data for authentication for future requests
    OnePageAPI.setAuthData(userId, authKey)

    // List contacts
    val contactsResponse = onePageAPI.contactsAsync().execute()
    println("contacts: ${contactsResponse.body()}\n")

    // Save a new contact
    val contactForm = ContactForm("Kotlin", "Sample", "OnePageCRM")
    val contactResponse = onePageAPI.contactsAsync(contactForm).execute()
    println("contact: ${contactResponse.body()}\n")
}
