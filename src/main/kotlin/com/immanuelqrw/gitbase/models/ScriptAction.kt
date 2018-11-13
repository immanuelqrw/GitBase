package com.immanuelqrw.gitbase.models

data class ScriptAction(
    val createRepository: Boolean,
    val createBranches: Boolean,
    val createUsers: Boolean,
    val createProjects: Boolean,
    val createLabels: Boolean,
    val createMilestones: Boolean,
    val createIssues: Boolean
)