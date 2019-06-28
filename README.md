JWT Service For Authentication Gateway
======================================== 

Authentication Gateway 용 JWT 발급 서비스

- Multi Module Gradle Project
- RS256 Algorithm Key(Private/Public)
- Kong Admin API(Create[Consumer/Credential], Delete[Consumer])
- JWT Create/Response

# Versions

- JWT Service 0.1.0
- JDK 12

# Overview

To Build the project, use:

```bash
./gradlew[.bat] clean build
```

To see what commands are available to the build script, run:

```bash
./gradle[.bat] tasks
```

# Configuration

- The specifics of the build are controlled using the gradle.properties file.

## See all dependencies

To collect the list of all project modules and dependencies:

```bash
./gradle[.bat] allDependencies
```

### Clear Gradle Cache

If you need to, on Linux/Unix systems, you can delete all the existing artifacts (artifacts and metadata) Gradle has downloaded using:

```bash
rm -rf $HOME/.gradle/caches/
```

Same strategy applies to Windows too, provided you switch `$HOME` to its equivalent in the above command.

# Deploy
 
## Executable WAR

Run the JWT service as an executable WAR:

```bash
./gradle[.bat] run
```

Debug the JWT service as an executable WAR:

```bash
./gradle[.bat] debug
```

## Explode WAR

Explode the JWT service as an WAR:

```bash
./gradle[.bat] explodeWar
```

## External

Deploy the binary JWT service file jwt.war after a successful build to servlet container of choice.

## Docker

The following strategies outline how to build and deploy JWT service Docker Images

### Jib

The overlay embraces the [Jib Gradle Plugin](https://github.com/GoogleContainerTools/jib) to provide easy-to-use out-of-the-box tooling for building JWT service docker images. Jib is an open-source Java containerizer from Google that lets Java developers build containers using the tools they know. It is a container image builder that handles all the steps of packaging your application into a container image. It does not require you to write a Dockerfile or have Docker installed, and it is directly integrated into the overlay.

```bash
./gradlew clean build jibDockerBuild
```

### Dockerfile

You can also use the native Docker tooling and the provided `Dockerfile` to build and run JWT service.

```bash
chmod +x *.sh
./docker-build.sh
./docker-run.sh
```
