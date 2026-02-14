# Architecture Documentation

## AppForge Architecture Overview

AppForge is built using modern Android development practices with a focus on simplicity and maintainability.

## Technology Stack

### Core Technologies
- **Language**: Kotlin
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)
- **Build System**: Gradle 8.2

### Key Libraries

1. **Sora Editor** (v0.23.3)
   - Purpose: Professional code editing
   - Features: Syntax highlighting, line numbers, auto-completion support
   - Used in: CodeEditorActivity

2. **Material Components** (v1.11.0)
   - Purpose: Modern UI components
   - Features: Material Design 3, cards, buttons, FABs
   - Used throughout: All activities

3. **AndroidX Libraries**
   - Core KTX
   - AppCompat
   - RecyclerView
   - ConstraintLayout
   - Lifecycle components

4. **Apache Commons IO** (v2.15.1)
   - Purpose: File operations
   - Features: Robust file handling

5. **Gson** (v2.10.1)
   - Purpose: JSON serialization
   - Features: Project metadata storage

## Project Structure

```
com.appforge.mobile/
├── MainActivity.kt          # App entry point, project listing
├── CodeEditorActivity.kt    # Code editing interface
├── ProjectListActivity.kt   # All projects view
├── ProjectManager.kt        # Project CRUD operations
└── ProjectAdapter.kt        # RecyclerView adapter for projects
```

## Component Architecture

### 1. MainActivity

**Responsibility**: Landing page and project management

**Key Features**:
- Display recent projects
- Create new projects dialog
- Storage permission handling
- Navigation to editor and project list

**User Flows**:
```
User opens app → MainActivity
  ↓
  ├─ Create Project → Dialog → ProjectManager.createProject() → CodeEditorActivity
  ├─ Open Project → ProjectListActivity → CodeEditorActivity
  └─ Tap Recent Project → CodeEditorActivity
```

### 2. CodeEditorActivity

**Responsibility**: Code editing and file management

**Key Features**:
- Sora Editor integration
- Multiple file tabs
- File operations (open, save, create, close)
- Build and run actions
- Menu with undo/redo

**Components**:
- CodeEditor widget (Sora)
- Tab container (HorizontalScrollView)
- Bottom action buttons
- File menu FAB

**File Management Flow**:
```
Open File → File Browser → Select File → Load Content → Display in Editor
Create File → File Name Dialog → Create in Project → Open in Editor
Save File → Get Editor Content → Write to Disk → Update Timestamp
```

### 3. ProjectManager

**Responsibility**: Business logic for project operations

**Core Methods**:
```kotlin
createProject(name: String, packageName: String): Project
getAllProjects(): List<Project>
getRecentProjects(limit: Int): List<Project>
updateProjectLastModified(path: String)
```

**Data Storage**:
- Projects list: `AppForge/projects.json` (Gson serialization)
- Project files: `AppForge/projects/{ProjectName}/`

**Project Template Generation**:
- Creates Android project structure
- Generates MainActivity.java
- Creates layout XML
- Sets up AndroidManifest.xml
- Creates build.gradle

### 4. Project Data Model

```kotlin
data class Project(
    val name: String,
    val packageName: String,
    val path: String,
    val createdDate: Long,
    val lastModified: Long
)
```

## File System Organization

### Storage Location
```
Internal Storage/
└── Android/data/com.appforge.mobile/files/
    └── AppForge/
        ├── projects.json              # Project index
        └── projects/
            ├── Project1/
            │   ├── src/main/
            │   │   ├── java/
            │   │   ├── res/
            │   │   └── AndroidManifest.xml
            │   └── build.gradle
            └── Project2/
                └── ...
```

### Generated Project Structure
```
ProjectName/
├── src/main/
│   ├── java/
│   │   └── [package.path]/
│   │       └── MainActivity.java
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml
│   │   └── values/
│   │       └── strings.xml
│   └── AndroidManifest.xml
└── build.gradle
```

## Code Editor Integration

### Sora Editor Configuration

```kotlin
codeEditor.apply {
    setEditorLanguage(JavaLanguage())
    colorScheme = EditorColorScheme.DARCULA
    isLineNumberEnabled = true
    typefaceText = Typeface.MONOSPACE
    setTextSize(14f)
}
```

**Features Enabled**:
- Java syntax highlighting
- Dark color scheme (Darcula)
- Line numbers
- Monospace font
- Undo/Redo support

## UI/UX Design

### Design Principles
1. **Mobile-First**: Touch-friendly controls
2. **Material Design**: Consistent with Android guidelines
3. **Minimalist**: Clean, focused interface
4. **Intuitive**: Clear navigation paths

### Color Scheme
```xml
Primary: #2196F3 (Blue)
Primary Dark: #1976D2
Accent: #4CAF50 (Green)
Background: #F5F5F5
Editor: #1E1E1E (Dark)
```

### Layout Strategy
- **ConstraintLayout**: For complex UIs
- **LinearLayout**: For simple stacks
- **CoordinatorLayout**: For FABs and AppBar
- **RecyclerView**: For project lists

## Permissions and Security

### Required Permissions
```xml
WRITE_EXTERNAL_STORAGE (SDK ≤ 32)
READ_EXTERNAL_STORAGE (SDK ≤ 32)
MANAGE_EXTERNAL_STORAGE (SDK ≥ 30)
INTERNET (future features)
```

### Permission Handling
- Runtime permission requests
- Graceful degradation without permissions
- Clear user messaging

### Security Considerations
- Scoped storage compliance (Android 11+)
- FileProvider for sharing files
- No network operations (yet)
- Local-only data storage

## Build System (Conceptual)

### Current Implementation
The app demonstrates build UI and flow but doesn't perform actual compilation.

### Future Implementation Plan
```
1. Integrate Android SDK tools
2. ECJ (Eclipse Compiler for Java) for compilation
3. AAPT2 for resource packaging
4. D8 for DEX generation
5. ZIP/APK signing
```

### Build Flow (Planned)
```
Source Files → ECJ Compile → .class files
Resources → AAPT2 → resources.arsc
.class files → D8 → classes.dex
Combine → ZIP → Sign → .apk
```

## Performance Considerations

### Optimizations
1. **Lazy Loading**: Projects loaded on-demand
2. **RecyclerView**: Efficient list rendering
3. **ViewBinding**: Efficient view access
4. **Coroutines**: Async operations (not yet implemented)

### Potential Issues
- Large files may slow editor
- Many open tabs consume memory
- Deep project hierarchies affect browsing

### Solutions
- File size warnings (planned)
- Tab limit (planned)
- Pagination for large lists (planned)

## Testing Strategy

### Unit Tests
Location: `app/src/test/java/`
- ProjectManager logic
- File operations
- Data model validation

### Instrumented Tests
Location: `app/src/androidTest/java/`
- UI interactions
- File system operations
- Permission flows

## Extension Points

### Adding New Features

**New File Type Support**:
1. Add language to Sora Editor
2. Update file extension handling
3. Add appropriate templates

**New Templates**:
1. Create template generator method
2. Add to ProjectManager
3. Update UI with selection

**Build Integration**:
1. Implement compiler integration
2. Add progress tracking
3. Error reporting

## Dependencies Graph

```
MainActivity
  ↓
  ├─→ ProjectManager
  │     ├─→ Gson
  │     └─→ File I/O
  └─→ ProjectAdapter
        └─→ RecyclerView

CodeEditorActivity
  ↓
  ├─→ SoraEditor
  ├─→ ProjectManager
  └─→ File I/O

ProjectListActivity
  ↓
  ├─→ ProjectManager
  └─→ ProjectAdapter
```

## Code Quality

### Style Guide
- Kotlin coding conventions
- 4-space indentation
- Meaningful variable names
- KDoc comments for public APIs

### Best Practices
- Single Responsibility Principle
- Don't Repeat Yourself (DRY)
- Defensive programming
- Error handling

## Future Architecture Improvements

### Planned Enhancements
1. **MVVM Architecture**: Separate UI and business logic
2. **Repository Pattern**: Abstract data sources
3. **Dependency Injection**: Hilt/Dagger
4. **Coroutines**: Async/await patterns
5. **Room Database**: Project metadata
6. **WorkManager**: Background builds
7. **Navigation Component**: Structured navigation

### Scalability
- Plugin architecture for extensibility
- Cloud sync infrastructure
- Multi-user support
- Version control integration

---

This architecture provides a solid foundation for a mobile IDE while remaining simple and maintainable.
