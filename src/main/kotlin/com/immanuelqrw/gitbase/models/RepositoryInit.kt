package com.immanuelqrw.gitbase.models


data class RepositoryInit(
    val name: String,
    val description: String,
    val isPrivate: Boolean,
    val hasEnabledAutoInit: Boolean,
    val hasEnabledDownloads: Boolean,
    val language: String,
    val license: String
)