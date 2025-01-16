plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.semestralka" //Unikátní identifikátor appky
    compileSdk = 35 //verze SDK, proti které se projekt kompiluje

    defaultConfig {
        applicationId = "com.example.semestralka" //jedinenčný identifikátor appky
        minSdk = 24 //Nejnižší verze androidu na které aplikace poběží
        targetSdk = 34  //verze kodu na terou je aplikace optimalizována
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false //vypnutí proguard pro optimalizaci kodu
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions { //nastavení kompatibility s Javou
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8" //kotlin kod je kompilován pro jvm verzi 1.8
    }

    buildFeatures {
        viewBinding = true //aktivuje ViewBinding, který usnadňuje práci s prvky layoutu bez nutnosti findViewByID (není potřeba)
    }
}

dependencies {
    val room_version = "2.6.1" //knihovna pro práci s dtb pomocí Room

    implementation("androidx.room:room-runtime:$room_version") //základní knihovna room

    // If this project uses any Kotlin source, use Kotlin Symbol Processing (KSP)
    // See [Add the KSP plugin to your project](https://developer.android.com/build/migrate-to-ksp#add-ksp)
    ksp("androidx.room:room-compiler:$room_version") //Kompilační  podpora pro Room přes KSP

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version") //Rozšíření pro kotlin

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}