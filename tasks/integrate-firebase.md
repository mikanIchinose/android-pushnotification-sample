# Firebase and FCM Integration Tasks

## High Priority Setup Tasks

### 1. Firebase Project Setup
- [x] Create Firebase project at https://console.firebase.google.com/
- [x] Add Android app with package name: `io.github.mikan.sample.pushnotification`
- [x] Download google-services.json file
- [x] Place google-services.json in `app/` directory
- [x] Verify google-services.json is in .gitignore

### 2. Dependencies Configuration
- [x] Add Google services plugin to project-level build.gradle.kts
- [x] Add Firebase BOM and FCM dependencies to app-level build.gradle.kts
- [x] Apply Google services plugin in app-level build.gradle.kts
- [x] Update libs.versions.toml with Firebase versions

### 3. Android Manifest Configuration
- [x] Add INTERNET permission to AndroidManifest.xml
- [x] Add WAKE_LOCK permission (optional)
- [x] Add notification icon metadata
- [x] Register FirebaseMessagingService in manifest

## High Priority Core Implementation

### 4. Firebase Messaging Service
- [x] Create MyFirebaseMessagingService class extending FirebaseMessagingService
- [x] Implement onMessageReceived method for handling push notifications
- [x] Implement onNewToken method for token refresh handling
- [x] Add proper error handling and logging

### 5. MainActivity Integration
- [ ] Add FCM token retrieval logic to MainActivity
- [ ] Implement token display UI for testing purposes
- [ ] Add proper coroutine/async handling for token retrieval
- [ ] Add error handling for token operations

## Medium Priority Notification System

### 6. Notification Management
- [ ] Create notification channel for Android 8.0+ compatibility
- [ ] Implement NotificationManager for displaying notifications
- [ ] Add notification icons (small and large)
- [ ] Customize notification appearance and style

### 7. User Interaction Handling
- [ ] Implement PendingIntent for notification click handling
- [ ] Add proper intent flags and extras
- [ ] Handle notification actions and deep linking
- [ ] Test notification click behavior

## Medium Priority Testing & Validation

### 8. Foreground/Background Testing
- [ ] Test foreground notification handling
- [ ] Test background notification handling
- [ ] Test notification behavior when app is killed
- [ ] Verify notification display in different app states

### 9. Firebase Console Testing
- [ ] Test notification from Firebase Console
- [ ] Test different payload types (data, notification, both)
- [ ] Verify targeting by FCM token
- [ ] Test notification scheduling and delivery

## Low Priority Enhancements

### 10. Advanced Testing Setup
- [ ] Setup FCM HTTP v1 API testing with curl/Postman
- [ ] Create test scripts for automated notification sending
- [ ] Add comprehensive logging for debugging
- [ ] Implement notification analytics tracking

### 11. UI/UX Improvements
- [ ] Add token copy-to-clipboard functionality
- [ ] Create notification history display
- [ ] Add notification settings/preferences
- [ ] Implement custom notification sounds

## Final Verification Tasks

### 12. Build & Quality Checks
- [ ] Run complete project build
- [ ] Execute lint checks and fix warnings
- [ ] Test ProGuard/R8 configuration for release builds
- [ ] Verify all dependencies are properly configured

### 13. End-to-End Testing
- [ ] Test complete notification flow from Firebase Console
- [ ] Verify notification behavior across different Android versions
- [ ] Test notification permissions and user experience
- [ ] Document any known issues or limitations

## Important Files to Create/Modify

- `app/google-services.json` - Firebase configuration
- `app/build.gradle.kts` - App-level dependencies
- `build.gradle.kts` - Project-level Google services plugin
- `gradle/libs.versions.toml` - Version catalog updates
- `app/src/main/AndroidManifest.xml` - Permissions and service registration
- `app/src/main/java/io/github/mikan/sample/pushnotification/MyFirebaseMessagingService.kt` - Core FCM service
- `app/src/main/java/io/github/mikan/sample/pushnotification/MainActivity.kt` - Token handling

## Key Dependencies to Add

```kotlin
// Firebase BOM
implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
implementation("com.google.firebase:firebase-messaging-ktx")
implementation("com.google.firebase:firebase-analytics-ktx")

// Google services plugin
id("com.google.gms.google-services")
```

## Reference Links

- [Firebase Cloud Messaging Android Setup](https://firebase.google.com/docs/cloud-messaging/android/client)
- [FCM HTTP v1 API Reference](https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages)
- [Android Notification Guide](https://developer.android.com/guide/topics/ui/notifiers/notifications)
- [Firebase Console](https://console.firebase.google.com/)