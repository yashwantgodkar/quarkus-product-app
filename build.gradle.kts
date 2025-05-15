plugins {
    id("io.quarkus") version "3.10.0"
    java
}

group = "com.example"
version = "1.0.0"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(enforcedPlatform("io.quarkus:quarkus-bom:3.10.0"))
    implementation("io.quarkus:quarkus-resteasy-reactive")
    implementation("io.quarkus:quarkus-hibernate-reactive-panache")
    implementation("io.quarkus:quarkus-reactive-pg-client")
    implementation("io.quarkus:quarkus-jdbc-postgresql")
    implementation("io.smallrye.reactive:mutiny")
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")

    testImplementation("io.quarkus:quarkus-test-hibernate-reactive-panache")
    testImplementation("org.mockito:mockito-core")
    testImplementation("io.quarkus:quarkus-junit5-mockito")
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")

}

tasks.test {
    useJUnitPlatform()
}
