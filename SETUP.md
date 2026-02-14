# Quick Setup Guide for AppForge

## For Users

### Installing on Android Device

1. **Download the APK**:
   - Get the latest release from GitHub
   - Or build from source (see Developer Setup below)

2. **Enable Unknown Sources** (if needed):
   - Go to Settings → Security
   - Enable "Install from Unknown Sources" for your browser/file manager

3. **Install the APK**:
   - Open the downloaded APK file
   - Tap "Install"
   - Wait for installation to complete

4. **Launch AppForge**:
   - Open the app from your app drawer
   - Grant storage permissions when prompted
   - Start creating apps!

### First Steps

1. **Grant Permissions**: Allow storage access
2. **Create Project**: Tap "Create New Project"
3. **Enter Details**: Name your project and set package name
4. **Start Coding**: Edit MainActivity.java
5. **Save Often**: Use the save button

## For Developers

### Building from Source

#### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 24+
- JDK 8 or later
- Gradle 8.2

#### Setup Steps

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/belfastman37/007bond.git
   cd 007bond
   ```

2. **Open in Android Studio**:
   - File → Open → Select the project folder
   - Wait for Gradle sync to complete

3. **Install Dependencies**:
   - Gradle will automatically download dependencies
   - If issues occur, try: Build → Clean Project

4. **Connect Device or Emulator**:
   - Enable USB debugging on your Android device
   - Connect via USB
   - OR start an Android emulator

5. **Run the App**:
   - Click the "Run" button (green triangle)
   - Select your device
   - App installs and launches

#### Build APK

**Debug APK**:
```bash
./gradlew assembleDebug
```
Output: `app/build/outputs/apk/debug/app-debug.apk`

**Release APK** (requires signing):
```bash
./gradlew assembleRelease
```
Output: `app/build/outputs/apk/release/app-release.apk`

### Project Structure

```
007bond/
├── app/                          # Main application module
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/            # Kotlin source files
│   │   │   ├── res/             # Resources (layouts, strings, etc.)
│   │   │   └── AndroidManifest.xml
│   │   ├── test/                # Unit tests
│   │   └── androidTest/         # Instrumented tests
│   └── build.gradle             # App module build config
├── gradle/                       # Gradle wrapper
├── build.gradle                  # Project build config
├── settings.gradle              # Project settings
└── README.md                     # Documentation
```

### Development Environment

#### Required Tools
- **Android Studio**: Latest stable version
- **Android SDK**: API 24 minimum, API 34 recommended
- **Kotlin Plugin**: Included with Android Studio
- **Git**: For version control

#### Recommended Setup
- **Physical Device**: For testing mobile experience
- **Emulator**: Pixel 5 or similar (API 34)
- **RAM**: At least 8GB
- **Storage**: 10GB free space

### Running Tests

**Unit Tests**:
```bash
./gradlew test
```

**Instrumented Tests**:
```bash
./gradlew connectedAndroidTest
```

### Code Style

Follow Kotlin coding conventions:
- 4 spaces for indentation
- Meaningful variable names
- KDoc comments for public APIs
- Line length: 120 characters

### Making Changes

1. **Create a Branch**:
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make Changes**: Edit code, add features

3. **Test**: Run tests to ensure nothing breaks

4. **Commit**:
   ```bash
   git add .
   git commit -m "Add: your feature description"
   ```

5. **Push**:
   ```bash
   git push origin feature/your-feature-name
   ```

6. **Create Pull Request**: On GitHub

### Common Issues

#### Gradle Sync Failed
- **Solution**: File → Invalidate Caches / Restart
- **Solution**: Update Android Studio
- **Solution**: Check internet connection

#### SDK Not Found
- **Solution**: Tools → SDK Manager → Install required SDKs
- **Solution**: Set ANDROID_HOME environment variable

#### Build Failed
- **Solution**: Clean project: Build → Clean Project
- **Solution**: Rebuild: Build → Rebuild Project
- **Solution**: Check error messages in Build tab

#### Emulator Won't Start
- **Solution**: Enable virtualization in BIOS
- **Solution**: Update emulator in SDK Manager
- **Solution**: Use a physical device instead

### Dependencies

All dependencies are declared in `app/build.gradle`:

```gradle
// Core Android
androidx.core:core-ktx:1.12.0
androidx.appcompat:appcompat:1.6.1

// UI
com.google.android.material:material:1.11.0
androidx.constraintlayout:constraintlayout:2.1.4

// Code Editor
io.github.rosemoe.sora-editor:editor:0.23.3

// Utilities
commons-io:commons-io:2.15.1
com.google.code.gson:gson:2.10.1
```

### Contributing

We welcome contributions! Please:

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

See CONTRIBUTING.md for detailed guidelines (coming soon).

### Getting Help

- **Documentation**: See README.md, USERGUIDE.md, ARCHITECTURE.md
- **Issues**: Open an issue on GitHub
- **Discussions**: Use GitHub Discussions

### Troubleshooting Development

#### "Cannot resolve symbol"
- **Solution**: File → Sync Project with Gradle Files

#### "Manifest merger failed"
- **Solution**: Check AndroidManifest.xml for conflicts
- **Solution**: Clean and rebuild

#### "Duplicate class found"
- **Solution**: Check for duplicate dependencies
- **Solution**: Use `./gradlew app:dependencies` to inspect

### Release Process

1. Update version in `app/build.gradle`
2. Update CHANGELOG.md
3. Create signed APK
4. Tag release: `git tag v1.0.0`
5. Push tag: `git push origin v1.0.0`
6. Create GitHub release
7. Upload APK to release

### License

This project is licensed under the MIT License. See LICENSE file for details.

---

## Quick Commands Reference

```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Install on connected device
./gradlew installDebug

# Uninstall from device
./gradlew uninstallDebug

# List dependencies
./gradlew app:dependencies

# Check for updates
./gradlew dependencyUpdates
```

---

**Happy Coding! 🚀**
