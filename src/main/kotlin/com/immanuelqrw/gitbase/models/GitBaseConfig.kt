package com.immanuelqrw.gitbase.models

data class GitBaseConfig(
    val variables: List<String>,
    val users: List<User>,
    val projects: List<Project>,
    val labels: List<Label>,
    val milestones: List<Milestone>,
    val issues: List<Issue>
)