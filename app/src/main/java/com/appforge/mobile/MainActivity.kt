package com.appforge.mobile

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.appforge.mobile.databinding.ActivityMainBinding
import com.appforge.mobile.databinding.DialogCreateProjectBinding
import com.google.android.material.snackbar.Snackbar
import java.io.File

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var projectManager: ProjectManager
    private lateinit var projectAdapter: ProjectAdapter
    
    companion object {
        private const val STORAGE_PERMISSION_CODE = 100
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        projectManager = ProjectManager(this)
        
        setupRecyclerView()
        setupButtons()
        checkStoragePermission()
    }
    
    override fun onResume() {
        super.onResume()
        loadRecentProjects()
    }
    
    private fun setupRecyclerView() {
        projectAdapter = ProjectAdapter { project ->
            openProject(project)
        }
        binding.rvRecentProjects.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = projectAdapter
        }
    }
    
    private fun setupButtons() {
        binding.btnCreateProject.setOnClickListener {
            showCreateProjectDialog()
        }
        
        binding.btnOpenProject.setOnClickListener {
            showProjectList()
        }
        
        binding.fabCreate.setOnClickListener {
            showCreateProjectDialog()
        }
    }
    
    private fun checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                showPermissionDialog()
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
                    STORAGE_PERMISSION_CODE
                )
            }
        }
    }
    
    private fun showPermissionDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.permission_required)
            .setMessage("AppForge needs storage access to save your projects and build apps.")
            .setPositiveButton(R.string.grant_permission) { _, _ ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                    startActivity(intent)
                }
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }
    
    private fun showCreateProjectDialog() {
        val dialogBinding = DialogCreateProjectBinding.inflate(LayoutInflater.from(this))
        
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()
        
        dialogBinding.btnCreate.setOnClickListener {
            val projectName = dialogBinding.etProjectName.text.toString().trim()
            val packageName = dialogBinding.etPackageName.text.toString().trim()
            
            if (projectName.isEmpty()) {
                dialogBinding.etProjectName.error = "Project name is required"
                return@setOnClickListener
            }
            
            if (packageName.isEmpty() || !isValidPackageName(packageName)) {
                dialogBinding.etPackageName.error = "Invalid package name"
                return@setOnClickListener
            }
            
            createProject(projectName, packageName)
            dialog.dismiss()
        }
        
        dialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        
        dialog.show()
    }
    
    private fun isValidPackageName(packageName: String): Boolean {
        val regex = "^[a-z][a-z0-9_]*(\\.[a-z][a-z0-9_]*)+$".toRegex()
        return regex.matches(packageName)
    }
    
    private fun createProject(projectName: String, packageName: String) {
        try {
            val project = projectManager.createProject(projectName, packageName)
            Snackbar.make(binding.root, R.string.project_created, Snackbar.LENGTH_SHORT).show()
            openProject(project)
        } catch (e: Exception) {
            AlertDialog.Builder(this)
                .setTitle(R.string.error)
                .setMessage("Failed to create project: ${e.message}")
                .setPositiveButton("OK", null)
                .show()
        }
    }
    
    private fun openProject(project: Project) {
        val intent = Intent(this, CodeEditorActivity::class.java).apply {
            putExtra("PROJECT_PATH", project.path)
            putExtra("PROJECT_NAME", project.name)
        }
        startActivity(intent)
    }
    
    private fun showProjectList() {
        val intent = Intent(this, ProjectListActivity::class.java)
        startActivity(intent)
    }
    
    private fun loadRecentProjects() {
        val projects = projectManager.getRecentProjects(5)
        if (projects.isEmpty()) {
            binding.tvNoProjects.visibility = android.view.View.VISIBLE
            binding.rvRecentProjects.visibility = android.view.View.GONE
        } else {
            binding.tvNoProjects.visibility = android.view.View.GONE
            binding.rvRecentProjects.visibility = android.view.View.VISIBLE
            projectAdapter.submitList(projects)
        }
    }
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Snackbar.make(binding.root, "Permission granted", Snackbar.LENGTH_SHORT).show()
            } else {
                showPermissionDialog()
            }
        }
    }
}
