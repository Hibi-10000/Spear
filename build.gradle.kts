import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    java
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

group = "com.github.hibi_10000.plugins"
version = "2.4.1"
java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    compileOnly("org.spigotmc", "spigot-api", "1.13.2-R0.1-SNAPSHOT")
}

bukkit {
    description = "Throw spears!"
    main = "com.github.hibi_10000.plugins.spear.Main"
    apiVersion = "1.13"

    commands {
        register("givespear") {
            description = "Give a player a spear."
        }
    }
    permissions {
        register("spear.give") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("spear.use.*") {
            default = BukkitPluginDescription.Permission.Default.TRUE
            children = listOf(
                "spear.use.regular_spear",
                "spear.use.fire_spear",
                "spear.use.explosive_spear",
                "spear.use.zeus_spear",
                "spear.use.teleport_spear",
                "spear.use.mob_spear"
            )
        }
        register("spear.use.regular_spear")
        register("spear.use.fire_spear")
        register("spear.use.explosive_spear")
        register("spear.use.zeus_spear")
        register("spear.use.teleport_spear")
        register("spear.use.mob_spear")
    }
}
