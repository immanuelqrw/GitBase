package com.immanuelqrw.gitbase.models


data class Issue(
    val title: String,
    val description: String?,
    val labels: List<Label>?,
    val milestone: Milestone?,
    val project: Project?,
    val assignee: User?
)