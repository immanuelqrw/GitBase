package com.immanuelqrw.gitbase.models

/**
 * Represents a Label called [name] with [color]
 *
 * @property name
 * @property color
 * @property description
 */
data class Label(
    val name: String,
    val color: String?,
    val description: String?
)