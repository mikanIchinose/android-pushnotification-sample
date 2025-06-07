# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this
repository.

## General

- Please answer in Japanese
- follow @docs/git.md when use git

## Project Overview

This is an Android push notification sample application built with Kotlin and Jetpack Compose. It
uses modern Android development practices with Material 3 design and targets API level 24+ (Android
7.0).

**Key Technologies:**

- Kotlin with Jetpack Compose for UI
- Material 3 theming with dynamic color support
- Gradle with version catalogs for dependency management
- Google Services (google-services.json present for Firebase integration)

**Package Structure:** `io.github.mikan.sample.pushnotification`

## Build Commands

**Build the project:**

```bash
./gradlew build
```

**Run unit tests:**

```bash
./gradlew test
```

**Run instrumentation tests:**

```bash
./gradlew connectedAndroidTest
```

**Install debug APK:**

```bash
./gradlew installDebug
```

**Clean build:**

```bash
./gradlew clean
```

**Run a single test class:**

```bash
./gradlew test --tests "io.github.mikan.sample.pushnotification.ExampleUnitTest"
```

## Architecture Notes

- **Single Activity Architecture**: Uses `MainActivity` with Jetpack Compose
- **Theme System**: Material 3 with dynamic color support (Android 12+) in `ui/theme/`
- **Version Catalog**: Dependencies managed in `gradle/libs.versions.toml`
- **Firebase Ready**: `google-services.json` is present (but not committed) for Firebase/FCM
  integration
- **Compose Integration**: Full Jetpack Compose setup with BOM for version alignment

**Main Entry Point:** `MainActivity.kt` - Sets up the Compose content with edge-to-edge display and
Material 3 theming.

**Testing Strategy:**

- Unit tests: JUnit5 + power-assert
- Compose testing: UI test framework included for Compose components
