package com.immanuelqrw.gitbase.models

/**
 * Represents a Project called [title] described by [description]
 *
 * @property title
 * @property description
 */
data class Project(
    val title: String,
    val description: String
)