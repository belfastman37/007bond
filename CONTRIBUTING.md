# Contributing to AppForge

Thank you for considering contributing to AppForge! This document provides guidelines and instructions for contributing.

## 🌟 Ways to Contribute

### Code Contributions
- Fix bugs
- Add new features
- Improve performance
- Enhance UI/UX
- Add tests

### Documentation
- Improve existing docs
- Add tutorials
- Fix typos
- Translate docs
- Add examples

### Other Contributions
- Report bugs
- Suggest features
- Test the app
- Provide feedback
- Share the project

## 🚀 Getting Started

### Prerequisites
- Android Studio (latest stable)
- Android SDK (API 24+)
- Git
- GitHub account
- Basic Kotlin knowledge

### Setup Development Environment

1. **Fork the Repository**
   ```bash
   # On GitHub, click "Fork"
   ```

2. **Clone Your Fork**
   ```bash
   git clone https://github.com/YOUR_USERNAME/007bond.git
   cd 007bond
   ```

3. **Add Upstream Remote**
   ```bash
   git remote add upstream https://github.com/belfastman37/007bond.git
   ```

4. **Open in Android Studio**
   - File → Open → Select project folder
   - Wait for Gradle sync

5. **Create a Branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

## 💻 Development Workflow

### 1. Make Changes

- Follow existing code style
- Write clear commit messages
- Keep changes focused
- Test your changes

### 2. Commit Guidelines

**Format**:
```
Type: Brief description

Detailed explanation (if needed)

Fixes #issue_number (if applicable)
```

**Types**:
- `Add:` - New feature
- `Fix:` - Bug fix
- `Update:` - Update existing feature
- `Refactor:` - Code refactoring
- `Docs:` - Documentation
- `Style:` - Code style changes
- `Test:` - Add/update tests
- `Chore:` - Maintenance tasks

**Examples**:
```bash
git commit -m "Add: Code completion feature"
git commit -m "Fix: Save button not working on Android 11"
git commit -m "Docs: Update installation instructions"
```

### 3. Push Changes

```bash
git push origin feature/your-feature-name
```

### 4. Create Pull Request

1. Go to your fork on GitHub
2. Click "New Pull Request"
3. Select your branch
4. Fill in the PR template
5. Submit!

## 📝 Code Style Guide

### Kotlin Style

**Follow Kotlin conventions**:
```kotlin
// Good
class ProjectManager(private val context: Context) {
    fun createProject(name: String): Project {
        return Project(name = name)
    }
}

// Avoid
class projectmanager(context: Context) {
    fun CreateProject(Name: String): Project {
        return Project(Name)
    }
}
```

**Naming**:
- Classes: `PascalCase`
- Functions: `camelCase`
- Variables: `camelCase`
- Constants: `UPPER_SNAKE_CASE`

**Formatting**:
- 4 spaces (no tabs)
- Line length: 120 characters
- Braces on same line

### XML Style

**Follow Android conventions**:
```xml
<!-- Good -->
<Button
    android:id="@+id/saveButton"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/save" />

<!-- Avoid -->
<Button android:id="@+id/saveButton" android:layout_width="wrap_content" android:layout_height="wrap_content" android:text="Save"/>
```

## 🧪 Testing

### Unit Tests
```bash
./gradlew test
```

### Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

### Manual Testing
1. Test on real device
2. Test different Android versions
3. Test different screen sizes
4. Test edge cases

## 🐛 Bug Reports

### Before Reporting
- Search existing issues
- Try latest version
- Check documentation

### Issue Template
```markdown
**Description**
Clear description of the bug

**Steps to Reproduce**
1. Open app
2. Tap button
3. See error

**Expected Behavior**
What should happen

**Actual Behavior**
What actually happens

**Environment**
- Device: [e.g., Pixel 5]
- Android Version: [e.g., 13]
- App Version: [e.g., 1.0.0]

**Screenshots**
If applicable
```

## 💡 Feature Requests

### Before Requesting
- Check existing requests
- Consider if it fits project scope
- Think about implementation

### Request Template
```markdown
**Feature Description**
Clear description of the feature

**Use Case**
Why is this needed?

**Proposed Solution**
How could it work?

**Alternatives**
Other approaches considered

**Additional Context**
Any other information
```

## 🔍 Code Review Process

### What We Look For
- Code quality
- Test coverage
- Documentation
- Performance impact
- Breaking changes
- Security issues

### Review Timeline
- Initial review: 2-7 days
- Feedback incorporation: varies
- Final approval: 1-3 days

## 📚 Documentation Standards

### Code Comments
```kotlin
/**
 * Creates a new Android project with the given configuration.
 *
 * @param name The project name
 * @param packageName The package identifier (e.g., com.example.app)
 * @return The created Project instance
 * @throws IllegalArgumentException if project already exists
 */
fun createProject(name: String, packageName: String): Project {
    // Implementation
}
```

### Documentation Files
- Use Markdown
- Clear headings
- Code examples
- Step-by-step guides
- Screenshots when helpful

## 🎯 Priority Areas

### High Priority
- Bug fixes
- Performance improvements
- Security issues
- Critical features

### Medium Priority
- UI/UX improvements
- New features
- Documentation
- Tests

### Low Priority
- Code refactoring
- Style improvements
- Minor enhancements

## 🚫 What Not to Do

- Don't commit large binary files
- Don't include personal information
- Don't break existing functionality
- Don't ignore review feedback
- Don't violate the license
- Don't add unnecessary dependencies

## ✅ Checklist Before Submitting

- [ ] Code follows style guide
- [ ] Tests pass locally
- [ ] Documentation updated
- [ ] Commit messages are clear
- [ ] No merge conflicts
- [ ] PR description is complete
- [ ] Screenshots included (if UI change)

## 🤝 Community Guidelines

### Be Respectful
- Treat everyone with respect
- Be constructive in feedback
- Help others learn
- Celebrate successes

### Be Professional
- Keep discussions on-topic
- Avoid spam
- No self-promotion
- Follow code of conduct

## 📞 Getting Help

### Questions?
- GitHub Discussions
- Issue comments
- Documentation

### Stuck?
- Review existing code
- Check documentation
- Ask for help
- Take breaks!

## 🎓 Learning Resources

### Kotlin
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- Kotlin Koans
- Android Developer Guides

### Android
- [Android Developer](https://developer.android.com/)
- Android Basics Course
- Material Design Guidelines

### Git
- [Git Documentation](https://git-scm.com/doc)
- GitHub Learning Lab
- Pro Git Book

## 🏆 Recognition

### Contributors
- Listed in README
- Featured in releases
- Community recognition

### Types of Recognition
- Code contributors
- Documentation contributors
- Bug reporters
- Feature suggesters

## 📋 Pull Request Template

```markdown
## Description
Brief description of changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Testing
- [ ] Unit tests added
- [ ] Integration tests added
- [ ] Manual testing completed

## Checklist
- [ ] Code follows style guide
- [ ] Documentation updated
- [ ] Tests pass
- [ ] No merge conflicts

## Screenshots (if applicable)
Add screenshots here

## Related Issues
Fixes #issue_number
```

## 🔄 Keeping Your Fork Updated

```bash
# Fetch upstream changes
git fetch upstream

# Merge upstream main into your main
git checkout main
git merge upstream/main

# Push updates to your fork
git push origin main
```

## 📅 Release Process

### Version Numbering
- Major.Minor.Patch (e.g., 1.2.3)
- Major: Breaking changes
- Minor: New features
- Patch: Bug fixes

### Release Steps
1. Update version in `build.gradle`
2. Update `CHANGELOG.md`
3. Create release branch
4. Test thoroughly
5. Create GitHub release
6. Build and upload APK

## 🎉 Thank You!

Your contributions make AppForge better for everyone. Whether you're fixing a typo or adding a major feature, every contribution matters!

---

**Questions?** Open an issue or start a discussion!

**Ready to contribute?** Fork the repo and get started!

**Happy Coding! 🚀**
