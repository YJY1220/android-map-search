import java.util.Properties

// Function to get API key from local.properties
fun getApiKey(key: String): String {
    val properties = Properties()
    file("local.properties").inputStream().use { properties.load(it) }
    return properties.getProperty(key)
}

plugins {
    id("com.android.application") version "8.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0" apply false
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}