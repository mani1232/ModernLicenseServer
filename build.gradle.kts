plugins {
    kotlin("multiplatform") version "1.9.20-RC" apply false
    kotlin("jvm") version "1.9.20-RC" apply false
    java apply true
    application apply true
}


allprojects {

}

tasks.register<Copy>("copyFile") {
    from("/kotlin-js/build/dist/js/productionExecutable/")
    include("**/*.js")
    include("**/*.js.map")
    into("/kotlin-jvm/src/rocker/js/")

    // You can specify other options, such as renaming the file if needed:
    // rename { file -> "newFileName.txt" }

    // You can add more files or directories to copy if needed:
    // from("another/source/file.txt")

    // You can include or exclude files using include and exclude methods if needed:
    // include("**/*.txt")
    // exclude("**/*.bak")
}

//tasks.build {
//    task("copyFile")
//}