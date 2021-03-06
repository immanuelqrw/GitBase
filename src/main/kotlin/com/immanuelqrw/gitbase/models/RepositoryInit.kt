package com.immanuelqrw.gitbase.models

/**
 * Represents initialization of Repository called [name] described by [description]
 *
 * @property owner Owner of repository
 * @property name
 * @property description
 * @param isPrivate Whether repository is enabled
 * @param hasEnabledAutoInit Whether auto initialization was enabled
 * @param hasEnabledDownloads Whether downloads are enabled
 * @param language Main Repository Language
 * @param license License
 * @param type Type of Repository
 * @param hasSuffix Whether to add type as suffix to name
 */
data class RepositoryInit(
    val owner: String,
    val name: String,
    val description: String,
    val isPrivate: Boolean,
    val hasEnabledAutoInit: Boolean,
    val hasEnabledDownloads: Boolean,
    val language: String,
    val license: String,
    val type: RepositoryType,
    val hasSuffix: Boolean
)