# AppForge User Guide

## Table of Contents
1. [Getting Started](#getting-started)
2. [Creating Projects](#creating-projects)
3. [Using the Code Editor](#using-the-code-editor)
4. [Project Management](#project-management)
5. [Building Apps](#building-apps)
6. [Tips & Tricks](#tips--tricks)

## Getting Started

### First Time Setup

When you launch AppForge for the first time:

1. **Grant Permissions**: AppForge needs storage permission to save your projects
   - Tap "Grant Permission" when prompted
   - Enable storage access in your device settings
   
2. **Welcome Screen**: You'll see the main screen with options to:
   - Create New Project
   - Open Project
   - View Recent Projects

### Understanding the Interface

**Main Screen**:
- Welcome card with app information
- "Create New Project" button
- "Open Project" button
- Recent projects list
- Floating action button (FAB) for quick project creation

## Creating Projects

### Step-by-Step Project Creation

1. **Tap "Create New Project"**
   - Either use the button or the FAB (floating action button)

2. **Enter Project Details**:
   - **Project Name**: Choose a meaningful name (e.g., "MyWeatherApp")
   - **Package Name**: Use reverse domain notation (e.g., "com.myname.weatherapp")
     - Must start with lowercase letter
     - Use dots to separate segments
     - Only use lowercase letters, numbers, and underscores

3. **Tap "Create"**
   - The app creates your project structure
   - Automatically opens the code editor
   - MainActivity.java is opened by default

### What Gets Created

Your new project includes:

```
ProjectName/
├── src/main/
│   ├── java/
│   │   └── [your.package.name]/
│   │       └── MainActivity.java
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml
│   │   └── values/
│   │       └── strings.xml
│   └── AndroidManifest.xml
└── build.gradle
```

## Using the Code Editor

### Editor Features

**Code Editing**:
- Syntax highlighting for Java
- Line numbers
- Auto-indentation
- Undo/Redo functionality
- Monospace font for better code readability

**Editor Controls**:
- **Save Button**: Save your current file
- **Run Button**: Quick build and run
- **Build Button**: Build your project
- **File Menu (FAB)**: Access file operations

### Working with Files

**Opening Files**:
1. Tap the file menu FAB (bottom-right)
2. Select "File Browser"
3. Choose a file from the list
4. File opens in the editor

**Creating New Files**:
1. Tap the file menu FAB
2. Select "New File"
3. Enter the filename (e.g., "Utils.java")
4. Tap "Create"
5. Start coding!

**Multiple Files**:
- Open files appear as tabs at the top
- Tap a tab to switch between files
- Current file is highlighted

**Closing Files**:
1. Tap the file menu FAB
2. Select "Close File"
3. The current file tab closes

### Editor Shortcuts

- **Save**: Top toolbar save icon or bottom save button
- **Undo**: Top toolbar undo icon
- **Redo**: Top toolbar redo icon
- **Auto-save**: Files auto-save when switching or exiting

## Project Management

### Opening Existing Projects

**From Main Screen**:
1. Tap "Open Project"
2. Select from the list of all projects
3. Project opens in the editor

**From Recent Projects**:
1. Recent projects appear on the main screen
2. Tap any project card to open it

### Project Information

Each project card shows:
- Project name
- Package name
- Last modified date

### Managing Multiple Projects

- Create unlimited projects
- Switch between projects anytime
- All projects saved in app storage
- Projects persist across app restarts

## Building Apps

### Build Process

1. **Prepare Your Code**:
   - Ensure all files are saved
   - Check for syntax errors

2. **Tap "Build"**:
   - Build process starts
   - Progress shown in notification
   - Success/failure message displayed

3. **Understanding Build Results**:
   - **Success**: APK is ready
   - **Failure**: Check error messages

### Running Your App

1. **Tap "Run"**:
   - Saves all files
   - Shows run dialog

2. **Choose Action**:
   - "Build & Run": Builds then attempts to run
   - "Cancel": Return to editing

### Important Notes

⚠️ **Build Limitations**:
- Full APK compilation requires Android SDK
- This version demonstrates the IDE interface
- For actual compilation, consider:
  - Cloud build services
  - Installing Android SDK on device (advanced)
  - Transferring project to desktop computer

## Tips & Tricks

### Coding Tips

1. **Start Simple**: Begin with small projects to learn the interface
2. **Save Often**: Use the save button regularly
3. **Use Templates**: Leverage the generated code as starting points
4. **File Organization**: Keep related code in separate files

### Performance Tips

1. **Small Files**: Keep individual files under 500 lines
2. **Regular Saves**: Don't accumulate too many unsaved changes
3. **Close Unused Tabs**: Close files you're not actively editing
4. **Restart if Slow**: Exit and reopen the app if it slows down

### Hardware Tips

1. **Use a Keyboard**: Bluetooth keyboard greatly improves coding speed
2. **Tablet Recommended**: Larger screen = better experience
3. **Good Lighting**: Reduce eye strain
4. **Comfortable Position**: Take breaks when coding on mobile

### Best Practices

1. **Meaningful Names**: Use descriptive variable and class names
2. **Comments**: Add comments to explain complex code
3. **Test Regularly**: Build frequently to catch errors early
4. **Backup**: Keep important projects backed up externally

## Troubleshooting

### Common Issues

**Problem**: Can't create projects
- **Solution**: Check storage permissions in device settings
- **Solution**: Ensure sufficient storage space (at least 100MB free)

**Problem**: Files not saving
- **Solution**: Verify storage permissions
- **Solution**: Check that storage is not full
- **Solution**: Restart the app

**Problem**: Editor is slow
- **Solution**: Close unused file tabs
- **Solution**: Restart the app
- **Solution**: Clear app cache in device settings

**Problem**: Can't find my projects
- **Solution**: Projects are in: Internal Storage/Android/data/com.appforge.mobile/files/AppForge/projects
- **Solution**: Use the "Open Project" feature in the app

### Getting Help

If you encounter issues:

1. Check this guide
2. Review the README.md file
3. Open an issue on GitHub
4. Contact support

## Advanced Features

### Project Structure Understanding

Learn the Android project structure:
- `src/main/java`: Your Java code
- `src/main/res`: Resources (layouts, strings, etc.)
- `AndroidManifest.xml`: App configuration
- `build.gradle`: Build configuration

### Editing Resources

**Layout Files** (XML):
- Located in `res/layout/`
- Define UI structure
- Edit manually or use visual preview (planned feature)

**Strings** (XML):
- Located in `res/values/strings.xml`
- Store text resources
- Support internationalization

### Understanding Build Configuration

**build.gradle**:
- Defines app dependencies
- Sets SDK versions
- Configures build options
- Edit with caution

## Keyboard Shortcuts (with External Keyboard)

When using a Bluetooth keyboard:

- **Ctrl+S**: Save file
- **Ctrl+Z**: Undo
- **Ctrl+Y**: Redo
- **Tab**: Indent
- **Shift+Tab**: Un-indent

## Future Features

Coming soon:
- Visual UI designer
- Code completion
- Git integration
- More templates
- Multi-language support
- Cloud sync

---

**Happy Coding on Mobile! 📱💻**
