Interact
=======

API for managing custom inventory menus (in-game GUI) using **either** the Bukkit API or SpongeAPI (**one** of these must be present at runtime).

This is still largely a WIP.

Using Interact
=============

I use Maven 3 to build Interact. Simply run the `mvn` command in the project root to compile the code and run the unit tests. You'll get a complete JAR in the `target/` directory with the dependencies appropriately shaded for you.

You can depend on Interact by adding the following to your `pom.xml`

If you're familiar with Maven, you'll be able to automatically download Interact as a dependency using the following setup.

```xml
<repository>
    <id>dsh-repo</id>
    <url>http://repo.dsh105.com/</url>
</repository>
```

```xml
<dependency>
    <groupId>com.dsh105</groupId>
    <artifactId>interactapi</artifactId>
    <version>1.0.0</version>
</dependency>
```

If you are also utilising Commodus (automatically shaded in by default), you will need to configure your dependency tree further:

```xml
<dependency>
    <groupId>com.dsh105</groupId>
    <artifactId>interactapi</artifactId>
    <version>1.0.0</version>
    <exclusions>
        <exclusion>
            <groupId>com.dsh105</groupId>
            <artifactId>Commodus</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

Note that Commodus and Interact will need to be **shaded and relocated to your plugin's package** for your plugin to work correctly.

Interact as a Bukkit/Sponge plugin
====================

The project also includes a module built for use on either a Bukkit or Sponge server. This means that instead of shading Interact into your plugin, you may instead install it on your server and let it handle any plugin-related setup duties required.
