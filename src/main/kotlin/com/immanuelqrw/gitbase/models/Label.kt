package com.immanuelqrw.gitbase.models

// TODO Add description -- current package doesn't use but API does
/**
 * Represents a Label called [name] with [color]
 *
 * @property name
 * @property color
 */
data class Label(
    val name: String,
    val color: String?
)