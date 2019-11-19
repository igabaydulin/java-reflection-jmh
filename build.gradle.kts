plugins {
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.10.1")
    implementation("org.openjdk.jmh:jmh-core:1.22")
    annotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.22")
}

application {
    mainClassName = "org.openjdk.jmh.Main"
}

version = "1.0.1-SNAPSHOT"
