package com.immanuelqrw.gitbase.models

/**
 * Represents a Milestone called [name] described by [description]
 */
data class Milestone(
    val name: String,
    val description: String?
)