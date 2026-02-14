# AppForge Quick Reference

## 🚀 Quick Start

```
1. Install AppForge → Grant Permissions
2. Tap "Create New Project"
3. Enter: Name + Package
4. Start Coding!
```

## 📱 Main Screen

| Button | Action |
|--------|--------|
| Create New Project | Start a new app project |
| Open Project | Browse all projects |
| FAB (+) | Quick create |
| Project Card | Open existing project |

## ✏️ Code Editor

### Toolbar Actions
- 💾 **Save** - Save current file
- ↶ **Undo** - Undo last change
- ↷ **Redo** - Redo last change

### Bottom Actions
- 💾 **Save** - Save current file
- ▶️ **Run** - Build and run project
- 🔨 **Build** - Build project

### File Menu (FAB)
- 📂 **Open File** - Browse project files
- ➕ **New File** - Create new file
- ❌ **Close File** - Close current file
- 📁 **File Browser** - Full file tree

## ⌨️ Editor Features

| Feature | Description |
|---------|-------------|
| Syntax Highlighting | Java code colors |
| Line Numbers | Easy navigation |
| Auto-indent | Proper formatting |
| Undo/Redo | Mistake recovery |
| Dark Theme | Eye-friendly |
| Multiple Tabs | Multi-file editing |

## 📁 Project Structure

```
MyProject/
├── src/main/
│   ├── java/
│   │   └── [package]/
│   │       └── MainActivity.java
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml
│   │   └── values/
│   │       └── strings.xml
│   └── AndroidManifest.xml
└── build.gradle
```

## 🎯 Common Tasks

### Create Project
```
Main Screen → Create → Name + Package → Create
```

### Open File
```
Editor → FAB → File Browser → Select
```

### Save Work
```
Editor → Save Button → ✓
```

### Create New File
```
Editor → FAB → New File → Name → Create
```

### Switch Files
```
Tap file tab at top
```

## 🔧 File Operations

| Operation | Steps |
|-----------|-------|
| **Open** | FAB → File Browser → Select |
| **New** | FAB → New File → Name |
| **Save** | Save button or toolbar |
| **Close** | FAB → Close File |
| **Switch** | Tap tab |

## 📝 Code Snippets

### Activity Template
```java
public class MyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }
}
```

### Button Click
```java
Button button = findViewById(R.id.button);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // Your code here
    }
});
```

### Update TextView
```java
TextView textView = findViewById(R.id.textView);
textView.setText("Hello World!");
```

## 🎨 Layout XML

### Basic Layout
```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">
    
    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello" />
        
    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Click Me" />
        
</LinearLayout>
```

## 🐛 Troubleshooting

| Problem | Solution |
|---------|----------|
| Can't create projects | Check storage permissions |
| Files not saving | Verify permissions, check storage |
| Editor slow | Close unused tabs, restart app |
| Can't find project | Use "Open Project" button |

## ⚙️ Settings & Permissions

### Required Permissions
- ✅ Storage (read/write)
- ℹ️ Internet (optional, future)

### Storage Location
```
Internal Storage/Android/data/
com.appforge.mobile/files/AppForge/
```

## 💡 Pro Tips

1. **💾 Save Often** - Use save button frequently
2. **🎹 External Keyboard** - Faster coding
3. **📱 Use Tablet** - Better screen space
4. **📝 Small Files** - Keep under 500 lines
5. **🔄 Close Tabs** - Better performance
6. **📚 Read Docs** - Check USERGUIDE.md

## ⚡ Keyboard Shortcuts

*(with Bluetooth keyboard)*

| Shortcut | Action |
|----------|--------|
| Ctrl+S | Save file |
| Ctrl+Z | Undo |
| Ctrl+Y | Redo |
| Tab | Indent |
| Shift+Tab | Un-indent |

## 📊 Limits & Specs

| Spec | Value |
|------|-------|
| Min Android | 7.0 (API 24) |
| Target Android | 14 (API 34) |
| File Size | Unlimited |
| Projects | Unlimited |
| Open Tabs | Unlimited* |
| Storage | Based on device |

*Performance may vary

## 🆘 Quick Help

### Can't Build?
- Note: Full compilation requires Android SDK
- Use for code editing and structure
- Transfer to PC for actual APK

### Lost Projects?
- Check "Open Project" list
- Projects in app storage
- Never deleted by app

### Need More Help?
- 📖 USERGUIDE.md - Detailed guide
- 🏗️ ARCHITECTURE.md - Technical docs
- 💡 EXAMPLES.md - Code examples
- 📚 README.md - Overview

## 🔗 Quick Links

| Document | Purpose |
|----------|---------|
| README.md | Project overview |
| USERGUIDE.md | How to use |
| SETUP.md | Installation |
| ARCHITECTURE.md | Technical details |
| FEATURES.md | Feature list |
| EXAMPLES.md | Code tutorials |
| QUICKREF.md | This file |

## 📞 Support

- 🐛 **Issues**: GitHub Issues
- 💬 **Questions**: GitHub Discussions
- 📧 **Contact**: See README.md
- 🌐 **Website**: GitHub Repository

## 🎓 Learning Resources

### Start Here
1. Create first project
2. Follow EXAMPLES.md
3. Experiment with code
4. Read USERGUIDE.md

### Concepts to Learn
- Java basics
- Android Activity
- Layouts (XML)
- Event handling
- findViewById

### Practice Projects
- Counter app
- Calculator
- Note taker
- Simple game

## ⭐ Key Features

✨ **Code Editor** - Professional editing  
🗂️ **Projects** - Full management  
📁 **Files** - Complete browsing  
🎨 **UI** - Material Design  
💾 **Storage** - Local & secure  
📱 **Mobile** - Touch-optimized  

## 📈 Version Info

- **Current**: v1.0
- **Platform**: Android
- **License**: MIT
- **Status**: Active

---

## 🎯 Remember

```
CODE → SAVE → BUILD → CREATE! 🚀
```

**AppForge** - Build Apps on Your Phone! 📱💻
