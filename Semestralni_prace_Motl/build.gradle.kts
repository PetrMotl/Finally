// Top-level build file
plugins {
    alias(libs.plugins.android.application) apply false //díky false není okamžitě plugin aktivován, ale je možné jej využít v průběhu projektu
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false  // OPRAVA
}

