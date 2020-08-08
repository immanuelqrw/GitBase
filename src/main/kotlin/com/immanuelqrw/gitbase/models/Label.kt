package com.immanuelqrw.gitbase.models

/**
 * Represents a Label called [name] with [color], described by [description] and replaces any other name in [aliases]
 *
 * @property name
 * @property color
 * @property description
 * @property aliases
 */
data class Label(
    val name: String,
    val color: String?,
    val description: String?,
    val aliases: List<String>?
)
