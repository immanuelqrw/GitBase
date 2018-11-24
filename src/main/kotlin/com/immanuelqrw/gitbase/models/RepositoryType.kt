package com.immanuelqrw.gitbase.models

/**
 * Represents type of Repository
 *
 * @property API API
 * @property APP Application
 * @property LIB Library
 * @property SCRIPT Script
 * @property SITE Website
 */
enum class RepositoryType {
    API,
    APP,
    LIB,
    SCRIPT,
    SITE
}