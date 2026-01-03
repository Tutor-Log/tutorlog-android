# TutorLog 📚

A modern Android application for tutors to manage students, track progress, and organize tutoring sessions built with Jetpack Compose and Clean Architecture.

## 📱 Features

- **Google Sign-In Authentication** - Secure login using Google OAuth
- **Student Management** - Add, view, and organize students
- **Group Management** - Organize students into groups
- **Home Dashboard** - Overview of tutoring activities
- **Modern UI** - Built with Jetpack Compose and Material Design 3
- **Edge-to-Edge Display** - Immersive full-screen experience


### Tech Stack

- **UI Framework**: Jetpack Compose
- **Navigation**: Compose Destinations
- **Dependency Injection**: Dagger Hilt
- **State Management**: Orbit MVI
- **Networking**: Retrofit + OkHttp
- **Authentication**: Firebase Auth + Google Sign-In
- **Image Loading**: Coil
- **Async**: Kotlin Coroutines + Flow
- **Build System**: Gradle (Kotlin DSL)


## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/tutorLog.git
cd tutorLog
```

### 2. Setup Firebase

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Create a new project or use an existing one
3. Add an Android app with package name: `com.example.tutorlog`
4. Download `google-services.json` and place it in the `app/` directory
5. Enable Google Sign-In in Firebase Authentication

### 3. Build and Run

```bash
./gradlew clean build
./gradlew installDebug
```

Or simply open the project in Android Studio and click Run.

## 🎨 Key Components

### Authentication Flow

1. **Login Screen**: Google Sign-In integration
2. **User Creation**: Store user data locally and in Firebase
3. **Session Management**: Persist user session with SharedPreferences

### Home Dashboard

- User profile display
- Quick access to students
- Navigation to different sections
- Bottom navigation bar

### Student Management

- View all students
- Organize students into groups
- Add new students
- Toggle between individual and group views

## 🔧 Configuration

### Minimum SDK Requirements

- **minSdk**: 24 (Android 7.0)
- **targetSdk**: 35 (Android 15)
- **compileSdk**: 35


## 🎨 Design System

The app uses a custom design system with:

- **Color Palette**: Dark theme optimized
- **Typography**: Custom text styles
- **Components**: Reusable composable components
- **LocalColors**: Compose local provider for consistent theming



## 🚧 Known Issues & Limitations

- Backend API integration is in progress
- Some features are under development
- Limited offline support

## 🔍 Troubleshooting

### Google Sign-In NETWORK_ERROR

If you encounter `RequestTokenManager getToken() -> NETWORK_ERROR` when signing in with Google:

1. **Verify OAuth Client ID**: Ensure the `web_client_id` in `app/src/main/res/values/strings.xml` matches the Web client (client_type 3) in your `google-services.json`
2. **Check SHA-1 Certificate**: Make sure your app's SHA-1 fingerprint is registered in the Firebase Console
   - Debug SHA-1: `keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android`
   - Add this fingerprint to your Firebase project settings
3. **Enable Google Sign-In**: Verify that Google Sign-In is enabled in Firebase Authentication
4. **Check Logs**: Look for detailed error messages in logcat with the tag `GoogleSignIn`

### Getting Debug SHA-1

```bash
# For debug builds
keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android

# For release builds
keytool -list -v -keystore <path-to-your-keystore> -alias <your-key-alias>
```


## 👨‍💻 Author

**Samarth Raj**

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📞 Support

For support, email mailforsamarth@gmail.com or open an issue in the repository.

## 🙏 Acknowledgments

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Compose Destinations](https://github.com/raamcosta/compose-destinations)
- [Orbit MVI](https://github.com/orbit-mvi/orbit-mvi)
- [Firebase](https://firebase.google.com/)
- [Dagger Hilt](https://dagger.dev/hilt/)

---

Made with ❤️ using Jetpack Compose

