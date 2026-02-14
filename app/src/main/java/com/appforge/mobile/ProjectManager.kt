package com.appforge.mobile

import android.content.Context
import android.os.Environment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

data class Project(
    val name: String,
    val packageName: String,
    val path: String,
    val createdDate: Long = System.currentTimeMillis(),
    val lastModified: Long = System.currentTimeMillis()
)

class ProjectManager(private val context: Context) {
    
    private val gson = Gson()
    private val projectsFile: File
    
    init {
        val appDir = File(context.getExternalFilesDir(null), "AppForge")
        if (!appDir.exists()) {
            appDir.mkdirs()
        }
        projectsFile = File(appDir, "projects.json")
    }
    
    fun createProject(projectName: String, packageName: String): Project {
        val projectsDir = File(context.getExternalFilesDir(null), "AppForge/projects")
        if (!projectsDir.exists()) {
            projectsDir.mkdirs()
        }
        
        val projectDir = File(projectsDir, projectName)
        if (projectDir.exists()) {
            throw IllegalArgumentException("Project with this name already exists")
        }
        
        projectDir.mkdirs()
        
        // Create project structure
        createProjectStructure(projectDir, packageName)
        
        val project = Project(
            name = projectName,
            packageName = packageName,
            path = projectDir.absolutePath
        )
        
        saveProject(project)
        return project
    }
    
    private fun createProjectStructure(projectDir: File, packageName: String) {
        // Create basic Android project structure
        val srcDir = File(projectDir, "src/main/java/${packageName.replace('.', '/')}")
        val resDir = File(projectDir, "src/main/res")
        val layoutDir = File(resDir, "layout")
        val valuesDir = File(resDir, "values")
        
        srcDir.mkdirs()
        layoutDir.mkdirs()
        valuesDir.mkdirs()
        
        // Create MainActivity.java
        val mainActivity = File(srcDir, "MainActivity.java")
        mainActivity.writeText(getMainActivityTemplate(packageName))
        
        // Create activity_main.xml
        val activityMain = File(layoutDir, "activity_main.xml")
        activityMain.writeText(getActivityMainLayoutTemplate())
        
        // Create strings.xml
        val strings = File(valuesDir, "strings.xml")
        strings.writeText(getStringsTemplate())
        
        // Create AndroidManifest.xml
        val manifestDir = File(projectDir, "src/main")
        val manifest = File(manifestDir, "AndroidManifest.xml")
        manifest.writeText(getManifestTemplate(packageName))
        
        // Create build.gradle
        val buildGradle = File(projectDir, "build.gradle")
        buildGradle.writeText(getBuildGradleTemplate(packageName))
    }
    
    private fun getMainActivityTemplate(packageName: String): String {
        return """package $packageName;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TextView textView = findViewById(R.id.textView);
        textView.setText("Hello from your app!");
    }
}
"""
    }
    
    private fun getActivityMainLayoutTemplate(): String {
        return """<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        android:textSize="24sp" />

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Click Me" />

</LinearLayout>
"""
    }
    
    private fun getStringsTemplate(): String {
        return """<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="app_name">MyApp</string>
</resources>
"""
    }
    
    private fun getManifestTemplate(packageName: String): String {
        return """<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="$packageName">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:theme="@android:style/Theme.Material.Light">
        
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        
    </application>

</manifest>
"""
    }
    
    private fun getBuildGradleTemplate(packageName: String): String {
        return """plugins {
    id 'com.android.application'
}

android {
    compileSdk 34

    defaultConfig {
        applicationId "$packageName"
        minSdk 24
        targetSdk 34
        versionCode 1
        versionName "1.0"
    }

    buildTypes {
        release {
            minifyEnabled false
        }
    }
}

dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
}
"""
    }
    
    private fun saveProject(project: Project) {
        val projects = getAllProjects().toMutableList()
        projects.add(project)
        projectsFile.writeText(gson.toJson(projects))
    }
    
    fun getAllProjects(): List<Project> {
        if (!projectsFile.exists()) {
            return emptyList()
        }
        
        return try {
            val json = projectsFile.readText()
            val type = object : TypeToken<List<Project>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    fun getRecentProjects(limit: Int = 5): List<Project> {
        return getAllProjects()
            .sortedByDescending { it.lastModified }
            .take(limit)
    }
    
    fun updateProjectLastModified(projectPath: String) {
        val projects = getAllProjects().toMutableList()
        val index = projects.indexOfFirst { it.path == projectPath }
        if (index != -1) {
            projects[index] = projects[index].copy(lastModified = System.currentTimeMillis())
            projectsFile.writeText(gson.toJson(projects))
        }
    }
}
