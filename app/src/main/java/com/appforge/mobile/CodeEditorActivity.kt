package com.appforge.mobile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.appforge.mobile.databinding.ActivityCodeEditorBinding
import com.appforge.mobile.databinding.DialogCreateProjectBinding
import com.google.android.material.snackbar.Snackbar
import io.github.rosemoe.sora.langs.java.JavaLanguage
import io.github.rosemoe.sora.widget.CodeEditor
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme
import java.io.File

class CodeEditorActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityCodeEditorBinding
    private lateinit var projectManager: ProjectManager
    private var projectPath: String = ""
    private var projectName: String = ""
    private var currentFile: File? = null
    private val openFiles = mutableListOf<File>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodeEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        projectPath = intent.getStringExtra("PROJECT_PATH") ?: ""
        projectName = intent.getStringExtra("PROJECT_NAME") ?: ""
        
        supportActionBar?.title = projectName
        
        projectManager = ProjectManager(this)
        
        setupCodeEditor()
        setupButtons()
        loadDefaultFile()
    }
    
    private fun setupCodeEditor() {
        binding.codeEditor.apply {
            setEditorLanguage(JavaLanguage())
            colorScheme = EditorColorScheme.DARCULA
            isLineNumberEnabled = true
            typefaceText = android.graphics.Typeface.MONOSPACE
            setTextSize(14f)
        }
    }
    
    private fun setupButtons() {
        binding.btnSave.setOnClickListener {
            saveCurrentFile()
        }
        
        binding.btnRun.setOnClickListener {
            runProject()
        }
        
        binding.btnBuild.setOnClickListener {
            buildProject()
        }
        
        binding.fabFiles.setOnClickListener {
            showFileMenu()
        }
    }
    
    private fun loadDefaultFile() {
        val projectDir = File(projectPath)
        val srcDir = File(projectDir, "src/main/java")
        
        // Find the MainActivity.java file
        val mainActivity = findFile(srcDir, "MainActivity.java")
        if (mainActivity != null) {
            openFile(mainActivity)
        }
    }
    
    private fun findFile(dir: File, filename: String): File? {
        if (!dir.exists() || !dir.isDirectory) return null
        
        dir.listFiles()?.forEach { file ->
            if (file.isDirectory) {
                val found = findFile(file, filename)
                if (found != null) return found
            } else if (file.name == filename) {
                return file
            }
        }
        return null
    }
    
    private fun openFile(file: File) {
        try {
            currentFile = file
            if (!openFiles.contains(file)) {
                openFiles.add(file)
                updateTabs()
            }
            
            val content = file.readText()
            binding.codeEditor.setText(content)
            
            supportActionBar?.subtitle = file.name
            
            projectManager.updateProjectLastModified(projectPath)
        } catch (e: Exception) {
            showError("Failed to open file: ${e.message}")
        }
    }
    
    private fun updateTabs() {
        binding.tabContainer.removeAllViews()
        openFiles.forEach { file ->
            val tabView = LayoutInflater.from(this)
                .inflate(android.R.layout.simple_list_item_1, binding.tabContainer, false)
            (tabView as android.widget.TextView).apply {
                text = file.name
                setPadding(32, 16, 32, 16)
                setOnClickListener { openFile(file) }
                if (file == currentFile) {
                    setBackgroundColor(getColor(R.color.primary))
                    setTextColor(getColor(R.color.white))
                }
            }
            binding.tabContainer.addView(tabView)
        }
    }
    
    private fun saveCurrentFile() {
        currentFile?.let { file ->
            try {
                val content = binding.codeEditor.text.toString()
                file.writeText(content)
                Snackbar.make(binding.root, R.string.file_saved, Snackbar.LENGTH_SHORT).show()
                projectManager.updateProjectLastModified(projectPath)
            } catch (e: Exception) {
                showError("Failed to save file: ${e.message}")
            }
        }
    }
    
    private fun showFileMenu() {
        val items = arrayOf(
            "Open File",
            "New File",
            "Close File",
            "File Browser"
        )
        
        AlertDialog.Builder(this)
            .setTitle("File Menu")
            .setItems(items) { _, which ->
                when (which) {
                    0 -> showFileBrowser()
                    1 -> showNewFileDialog()
                    2 -> closeCurrentFile()
                    3 -> showFileBrowser()
                }
            }
            .show()
    }
    
    private fun showFileBrowser() {
        val projectDir = File(projectPath)
        val files = getAllFiles(projectDir)
        
        val fileNames = files.map { it.relativeTo(projectDir).path }.toTypedArray()
        
        AlertDialog.Builder(this)
            .setTitle("Select File")
            .setItems(fileNames) { _, which ->
                openFile(files[which])
            }
            .show()
    }
    
    private fun getAllFiles(dir: File): List<File> {
        val files = mutableListOf<File>()
        dir.listFiles()?.forEach { file ->
            if (file.isDirectory) {
                files.addAll(getAllFiles(file))
            } else {
                files.add(file)
            }
        }
        return files
    }
    
    private fun showNewFileDialog() {
        val dialogBinding = DialogCreateProjectBinding.inflate(LayoutInflater.from(this))
        
        // Reuse the dialog but change the labels
        dialogBinding.etProjectName.hint = "File Name (e.g., MyClass.java)"
        dialogBinding.etPackageName.visibility = android.view.View.GONE
        
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()
        
        dialogBinding.btnCreate.setOnClickListener {
            val fileName = dialogBinding.etProjectName.text.toString().trim()
            if (fileName.isEmpty()) {
                dialogBinding.etProjectName.error = "File name is required"
                return@setOnClickListener
            }
            
            createNewFile(fileName)
            dialog.dismiss()
        }
        
        dialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        
        dialog.show()
    }
    
    private fun createNewFile(fileName: String) {
        try {
            val projectDir = File(projectPath)
            val srcDir = File(projectDir, "src/main/java")
            
            // Find package directory
            val packageDir = srcDir.listFiles()?.firstOrNull { it.isDirectory }
            if (packageDir != null) {
                val newFile = File(findPackageRoot(packageDir), fileName)
                if (!newFile.exists()) {
                    newFile.createNewFile()
                    openFile(newFile)
                    Snackbar.make(binding.root, "File created", Snackbar.LENGTH_SHORT).show()
                } else {
                    showError("File already exists")
                }
            }
        } catch (e: Exception) {
            showError("Failed to create file: ${e.message}")
        }
    }
    
    private fun findPackageRoot(dir: File): File {
        var current = dir
        while (current.listFiles()?.size == 1 && current.listFiles()?.first()?.isDirectory == true) {
            current = current.listFiles()!!.first()
        }
        return current
    }
    
    private fun closeCurrentFile() {
        currentFile?.let { file ->
            openFiles.remove(file)
            updateTabs()
            
            if (openFiles.isNotEmpty()) {
                openFile(openFiles.last())
            } else {
                currentFile = null
                binding.codeEditor.setText("")
                supportActionBar?.subtitle = ""
            }
        }
    }
    
    private fun buildProject() {
        Snackbar.make(binding.root, R.string.build_started, Snackbar.LENGTH_LONG).show()
        
        // In a real implementation, this would invoke the Android build tools
        // For this demo, we'll show a success message
        binding.root.postDelayed({
            AlertDialog.Builder(this)
                .setTitle(R.string.build_success)
                .setMessage("Your app has been built successfully!\n\nNote: Full build functionality requires Android build tools. This is a demonstration of the IDE interface.")
                .setPositiveButton("OK", null)
                .show()
        }, 2000)
    }
    
    private fun runProject() {
        saveCurrentFile()
        
        AlertDialog.Builder(this)
            .setTitle("Run Project")
            .setMessage("To run your project:\n\n1. Build the project first\n2. The APK will be generated\n3. Install and run the APK\n\nNote: Full build and run functionality requires Android SDK and build tools to be installed on the device.")
            .setPositiveButton("Build & Run") { _, _ ->
                buildProject()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun showError(message: String) {
        AlertDialog.Builder(this)
            .setTitle(R.string.error)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_editor, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.action_save -> {
                saveCurrentFile()
                true
            }
            R.id.action_undo -> {
                binding.codeEditor.undo()
                true
            }
            R.id.action_redo -> {
                binding.codeEditor.redo()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    override fun onBackPressed() {
        // Save before leaving
        saveCurrentFile()
        super.onBackPressed()
    }
}
