[![Spring Boot TDD Online Course](https://rieckpil.de/wp-content/uploads/2023/07/tdd-with-spring-boot-done-right-final-featured-image.png)](https://rieckpil.de/tdd-with-spring-boot-done-right/)

# TDD with Spring Boot Done Right Online Course

[![](https://img.shields.io/badge/Spring%20Boot%20Version-3.1.X-orange)](/pom.xml)
[![](https://img.shields.io/badge/Java%20Version-17-orange)](/pom.xml)
[![](https://img.shields.io/badge/Enroll-Now-orange)](https://rieckpil.de/tdd-with-spring-boot-done-right/)

- `main` branch: [![Build & Test Maven Project](https://github.com/rieckpil/tdd-with-spring-boot-done-right/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/rieckpil/tdd-with-spring-boot-done-right/actions/workflows/maven.yml)
- `code-aling` branch: [![Build & Test Maven Project](https://github.com/rieckpil/tdd-with-spring-boot-done-right/actions/workflows/maven.yml/badge.svg?branch=code-along)](https://github.com/rieckpil/tdd-with-spring-boot-done-right/actions/workflows/maven.yml)

## Introduction

Boost your productivity with TDD (Test-Driven Development) and Spring Boot to accelerate your development. Build better, faster, and safer.

- **No Fluff:** We don't want to religiously convince you. Take this course and see it for yourself. Apply what works for you and avoid cargo-cult.
- **Beyond The Basics:** Test fails, test passes, refactoring? But how to do TDD for legacy code? How to do it remotely within a team? We'll answer that.
- **Real-World Examples**: TDD for a Game or Calculator is simple. That's playground and doesn't prepare you. We go for real world hands-on examples.
- **Productivity Boost:** Write more maintainable code. Get confidence by adding a covering test suite for your project to sleep better at night.

The TDD with Spring Boot Done Right Online Courses focuses on teaching the TDD with Java and Spring Boot.

We won't cover all technical details of testing with Spring Boot, but rather focus on the TDD methodology and how to apply it in.

If you're new to testing with Java and Spring Boot, we highly recommend to first work through the [Testing Spring Boot Applications Masterclass](https://rieckpil.de/testing-spring-boot-applications-masterclass/) before taking this course.

» Enroll [here](https://rieckpil.de/tdd-with-spring-boot-done-right/) for the TDD with Spring Boot Done Right Online Course.

## Further Resources and Links

* [Course Landing Page](https://rieckpil.de/tdd-with-spring-boot-done-right/)
* [Course Overview](https://rieckpil.de/courses/tdd-with-spring-boot-done-right/)
* [Course Login](https://rieckpil.de/wp-login.php)
* [Password Reset](https://rieckpil.de/wp-login.php?action=lostpassword)

# Local Project Setup

## Requirements

Mandatory requirements:

* Java 17 (JDK flavour (OpenJDK/Azul/Oracle) does not matter). For the correct Java version setup I can recommend [JEnv](https://www.youtube.com/watch?v=9FVZyeFDXo0) (Mac/Linux) and the [Maven Toolchains Plugin](https://maven.apache.org/plugins/maven-toolchains-plugin/toolchains/jdk.html) (Windows)

```
$ java -version
openjdk version "17" 2021-09-14 LTS
OpenJDK Runtime Environment Zulu17.28+13-CA (build 17+35-LTS)
OpenJDK 64-Bit Server VM Zulu17.28+13-CA (build 17+35-LTS, mixed mode, sharing)
```

* Docker Engine (Community Edition is enough):

```
$ docker version
Client: Docker Engine - Community
 Version:           20.10.6
 API version:       1.41
 Go version:        go1.13.15
 Git commit:        370c289
 Built:             Fri Apr  9 22:47:17 2021
 OS/Arch:           linux/amd64
 Context:           default
 Experimental:      true

Server: Docker Engine - Community
 Engine:
  Version:          20.10.6
  API version:      1.41 (minimum version 1.12)
  Go version:       go1.13.15
  Git commit:       8728dd2
  Built:            Fri Apr  9 22:45:28 2021
  OS/Arch:          linux/amd64
  Experimental:     false
```

Optional requirements:

* Maven >= 3.6 (the project also includes the Maven Wrapper).

When using Maven from the command line, make sure `./mvnw -version` reports the correct Java version:

```
$ ./mvnw -version

Apache Maven 3.8.4 (9b656c72d54e5bacbed989b64718c159fe39b537)
Maven home: /home/rieckpil/.m2/wrapper/dists/apache-maven-3.8.4-bin/52ccbt68d252mdldqsfsn03jlf/apache-maven-3.8.4
Java version: 17.0.1, vendor: Eclipse Adoptium, runtime: /usr/lib/jvm/jdk-17.0.1+12
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "5.4.0-92-generic", arch: "amd64", family: "unix"
```

* IntelliJ IDEA or any IDE/Code Editor (Eclipse, NetBeans, Code, Atom, etc.)
