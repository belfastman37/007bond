# AppForge - Mobile App Builder

## Build Android Apps on Your Phone - No PC Required!

AppForge is a powerful mobile IDE that allows you to create Android applications directly on your Android device without needing a computer. Write code, manage projects, and build apps all from your phone or tablet.

## Features

### 🚀 Core Features
- **Full Code Editor**: Professional code editor with syntax highlighting for Java
- **Project Management**: Create, open, and manage multiple Android projects
- **File Browser**: Navigate and edit all project files
- **Real-time Editing**: Edit code with line numbers and syntax highlighting
- **Multiple File Tabs**: Work on multiple files simultaneously
- **Auto-save**: Automatic project saving

### 📱 Mobile-Optimized
- **Touch-friendly Interface**: Designed for mobile devices
- **Responsive Layout**: Works on phones and tablets
- **Material Design**: Modern, intuitive user interface
- **Dark Theme Editor**: Easy on the eyes for extended coding sessions

### 🛠️ Project Templates
- **Blank Activity**: Start with a basic Android activity
- **Pre-configured Structure**: Automatic project structure creation
- **Ready-to-code**: Generated files include MainActivity, layouts, and manifests

## Getting Started

### Installation
1. Download and install the AppForge APK on your Android device
2. Grant storage permissions when prompted (required for saving projects)
3. Launch the app

### Creating Your First Project
1. Tap "Create New Project" on the home screen
2. Enter your project name (e.g., "MyFirstApp")
3. Enter a package name (e.g., "com.example.myfirstapp")
4. Tap "Create"
5. Start coding!

### Editing Code
1. The code editor opens automatically with MainActivity.java
2. Edit your code using the built-in editor
3. Tap "Save" to save your changes
4. Use the file menu (bottom-right FAB) to:
   - Open other files
   - Create new files
   - Browse the file structure

### Building Your App
1. Tap the "Build" button in the editor
2. Wait for the build process to complete
3. The APK will be generated (full functionality requires Android build tools)

## Project Structure

Each project includes:
```
MyProject/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/myapp/
│       │       └── MainActivity.java
│       ├── res/
│       │   ├── layout/
│       │   │   └── activity_main.xml
│       │   └── values/
│       │       └── strings.xml
│       └── AndroidManifest.xml
└── build.gradle
```

## Technical Details

### Requirements
- Android 7.0 (API 24) or higher
- 100MB free storage space
- Storage permissions

### Technologies Used
- **Kotlin**: Primary language for the IDE
- **Sora Editor**: Professional code editor component
- **Material Components**: Modern UI design
- **AndroidX**: Latest Android libraries

### Permissions
- **Storage**: Required to save and load projects
- **Internet**: For future features (updates, templates)

## Limitations

This is a demonstration mobile IDE with the following limitations:

1. **Build System**: Full APK compilation requires Android build tools and SDK to be installed on the device
2. **Complex Projects**: Best suited for simple to moderate complexity apps
3. **Debugging**: Limited debugging capabilities compared to desktop IDEs
4. **Performance**: Large projects may be slower on mobile devices

## Future Features (Roadmap)

- [ ] Full build system integration with on-device compilation
- [ ] Visual UI designer (drag-and-drop)
- [ ] Git integration for version control
- [ ] Code completion and IntelliSense
- [ ] More project templates
- [ ] Plugin system
- [ ] Multi-language support (Kotlin, XML, etc.)
- [ ] APK signing and deployment
- [ ] Integrated emulator/preview
- [ ] Cloud synchronization
- [ ] Collaborative coding

## Tips for Mobile Development

1. **Save Frequently**: Use the save button regularly
2. **Small Files**: Keep files small for better mobile performance
3. **Simple Projects**: Start with simple apps and grow from there
4. **External Keyboard**: Consider using a Bluetooth keyboard for extended coding
5. **Tablet Recommended**: Larger screens provide better coding experience

## Troubleshooting

### Can't Create Projects
- Ensure storage permissions are granted
- Check available storage space
- Try restarting the app

### Files Not Saving
- Verify storage permissions
- Check that the device is not in low storage mode
- Ensure the project directory is accessible

### Build Failed
- Note: Full build functionality requires Android SDK
- This demo version shows the build interface
- For actual compilation, consider using cloud build services

## Contributing

This is an open-source project. Contributions are welcome!

## License

This project is open source and available under the MIT License.

## Support

For issues, questions, or suggestions, please open an issue on the GitHub repository.

## Acknowledgments

- Sora Editor library for the code editor component
- Material Components for the beautiful UI
- The Android open source community

---

**Made with ❤️ for mobile developers who want to code anywhere, anytime!**