package com.immanuelqrw.gitbase.models

/**
 * Represents an Issue called [title] described by [description]
 * Includes [labels], [milestone], [project]
 * Assigned to [assignee]
 */
data class Issue(
    val title: String,
    val description: String?,
    val labels: List<Label>?,
    val milestone: Milestone?,
    val project: Project?,
    val assignee: User?
)