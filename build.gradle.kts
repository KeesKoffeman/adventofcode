plugins {
    idea
    kotlin("jvm") version "1.3.61"
}

repositories {
    jcenter() 
}

dependencies {
    implementation(kotlin("stdlib")) 
    testCompile("junit:junit:4.12")
    compile(kotlin("script-runtime"))
}
