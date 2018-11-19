package com.immanuelqrw.gitbase.models

/**
 * Represents GitBase configuration for creating standard Repository
 *
 * @property variables Stored variables, unused in class but used in configuration file
 * @property branches List of branches
 * @property users List of users
 * @property projects List of projects
 * @property labels List of labels
 * @property milestones List of milestones
 * @property issues List of issues
 */
data class GitBaseConfig(
    val variables: List<String>,
    val branches: List<Branch>,
    val users: List<User>,
    val projects: List<Project>,
    val labels: List<Label>,
    val milestones: List<Milestone>,
    val issues: List<Issue>
)