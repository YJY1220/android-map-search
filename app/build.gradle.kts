import java.util.Properties

// local.properties에서 키 읽어오기
fun getApiKey(key: String): String {
    val properties = Properties()
    file("../local.properties").inputStream().use { properties.load(it) }
    return properties.getProperty(key)
}

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "campus.tech.kakao.map"
    compileSdk = 34

    defaultConfig {
        applicationId = "campus.tech.kakao.map"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //local.properties에서 읽어온 네이티브 앱 키를 strings.xml에 추가함
        resValue("string", "kakao_api_key", getApiKey("KAKAO_API_KEY"))
        //kakao rest api키를 buildconfig 클래스 추가 - buildconfig.kakao_rest_api_key로 api 접근 가능
        buildConfigField("String", "KAKAO_REST_API_KEY", "\"${getApiKey("KAKAO_REST_API_KEY")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true // 데이터 바인딩 활성화
        buildConfig = true // buildconfig 활성화
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0") //네트워크 요청 처리
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") //json -> kotlin
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0") // 네트워크 요청과 응답 로깅
    implementation("com.kakao.sdk:v2-user:2.10.0")  // Kakao SDK 추가
    implementation("androidx.activity:activity:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.fragment:fragment-ktx:1.3.6")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
}
