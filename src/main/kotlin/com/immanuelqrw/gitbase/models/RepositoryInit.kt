package com.immanuelqrw.gitbase.models

/**
 * Represents initialization of Repository called [name] described by [description]
 *
 * @param isPrivate Whether repository is enabledß
 * @param hasEnabledAutoInit Whether auto initialization was enabled
 * @param hasEnabledDownloads Whether downloads are enabled
 * @param language Main Repository Language
 * @param license License
 */
data class RepositoryInit(
    val name: String,
    val description: String,
    val isPrivate: Boolean,
    val hasEnabledAutoInit: Boolean,
    val hasEnabledDownloads: Boolean,
    val language: String,
    val license: String
)