package com.immanuelqrw.gitbase.models

/**
 * Represents an Issue called [title] described by [description]
 * Includes [labels], [milestone], [project]
 * Assigned to [assignee]
 *
 * @property title
 * @property description
 * @property labels
 * @property milestone
 * @property project
 * @property assignee
 */
data class Issue(
    val title: String,
    val description: String?,
    val labels: List<Label>?,
    val milestone: Milestone?,
    val project: Project?,
    val assignee: User?
)