import net.ormr.userskripter.plugin.ScriptEngine.TAMPER_MONKEY
import java.awt.Desktop

plugins {
    kotlin("js") version "1.7.21"
    id("net.ormr.userskripter.plugin") version "0.2.1"
}

group = "net.ormr.userskripter"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.ormr.userskripter:userskripter:0.2.1")
}

userskript {
    scriptEngine = TAMPER_MONKEY
    metadata {
        matchHostName("example.com")
        grant("GM_notification")
    }
}

kotlin {
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled = true
                }
            }
        }
    }
}

tasks {
    afterEvaluate {
        val generateUserFile = named("generateUserFile")
        val generateUserscript = named("generateUserscript")
        create("openUserscriptDirectory") {
            group = "userskripter"
            dependsOn(generateUserscript)
            doLast {
                val userFile = generateUserFile.get().outputs.files.singleFile
                Desktop.getDesktop().open(userFile.parentFile)
            }
        }
    }
}