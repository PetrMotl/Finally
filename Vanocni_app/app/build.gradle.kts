plugins {
    alias(libs.plugins.android.application) //alias odkazuje na definice v souboru libs.version.toml
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.vanoce" //název balíčku aplikace
    compileSdk = 35 //verze sdk

    defaultConfig {
        applicationId = "com.example.vanoce" //unikátní identifikátor aplikace
        minSdk = 24 //nejnižší verze androidu na kterém je aplikace podporována
        targetSdk = 35 //verze AN proti které je aplikace optimalizována
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes { //definuje různé typy buildů (debug, release...)
        release { //konfigurace pro produkční verzi aplikace
            isMinifyEnabled = false //nepoužívá pro guard pro změnšení a optimalizaci kodu
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8 //Verze Javy se kterou je projekt kompatibilní
        targetCompatibility = JavaVersion.VERSION_1_8 //Verze Javy  pro kterou se projekt kompiluje
    }
    kotlinOptions {
        jvmTarget = "1.8" //Nastavuje verzi jmv pro kterou se kotlin kod kompiluje
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}