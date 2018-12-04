plugins {
    idea
    kotlin("jvm") version "1.3.10" 
}

repositories {
    jcenter() 
}

dependencies {
    implementation(kotlin("stdlib")) 
    testCompile("junit:junit:4.12")
}
