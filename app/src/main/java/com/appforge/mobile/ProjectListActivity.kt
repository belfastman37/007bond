package com.appforge.mobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.appforge.mobile.databinding.ActivityMainBinding

class ProjectListActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var projectManager: ProjectManager
    private lateinit var projectAdapter: ProjectAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "All Projects"
        
        projectManager = ProjectManager(this)
        
        setupRecyclerView()
        loadProjects()
        
        // Hide unused buttons
        binding.btnCreateProject.visibility = android.view.View.GONE
        binding.btnOpenProject.visibility = android.view.View.GONE
        binding.fabCreate.visibility = android.view.View.GONE
    }
    
    private fun setupRecyclerView() {
        projectAdapter = ProjectAdapter { project ->
            // Open project and finish this activity
            val intent = android.content.Intent(this, CodeEditorActivity::class.java).apply {
                putExtra("PROJECT_PATH", project.path)
                putExtra("PROJECT_NAME", project.name)
            }
            startActivity(intent)
            finish()
        }
        binding.rvRecentProjects.apply {
            layoutManager = LinearLayoutManager(this@ProjectListActivity)
            adapter = projectAdapter
        }
    }
    
    private fun loadProjects() {
        val projects = projectManager.getAllProjects()
        if (projects.isEmpty()) {
            binding.tvNoProjects.visibility = android.view.View.VISIBLE
            binding.rvRecentProjects.visibility = android.view.View.GONE
        } else {
            binding.tvNoProjects.visibility = android.view.View.GONE
            binding.rvRecentProjects.visibility = android.view.View.VISIBLE
            binding.tvRecentProjects.text = "All Projects"
            projectAdapter.submitList(projects.sortedByDescending { it.lastModified })
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
