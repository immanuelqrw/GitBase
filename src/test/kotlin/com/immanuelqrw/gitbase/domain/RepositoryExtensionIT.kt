package com.immanuelqrw.gitbase.domain

import io.mockk.junit5.MockKExtension
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.kohsuke.github.GHRepository
import org.kohsuke.github.GitHub
import org.kohsuke.github.GitHubBuilder

/**
 * Integration Test for RepositoryExtension class
 *
 * Tests main functionality of GitBase
 * @property gitHub GitHub connection
 * @property repository Test repository
 * @property propertyFile Test property file to point to correct repository
 * @property repositoryName Test repository to create
 */
@ExtendWith(MockKExtension::class)
class RepositoryExtensionIT {
    private lateinit var gitHub: GitHub
    private lateinit var repository: GHRepository

    // TODO Add Property file
    private val propertyFile = ""

    // TODO Find example repository name
    private val repositoryName = "test"

    /**
     * Setup for all integration tests
     */
    @BeforeAll
    fun setUp() {
        // TODO Delete all test repositories before running tests
        gitHub = GitHubBuilder.fromPropertyFile(propertyFile).build()
        repository = gitHub.getRepository(repositoryName)
    }

    // TODO Look up ways to initialize fake data
    @Nested
    inner class BaseMethods {
        @ParameterizedTest
        @ValueSource(strings = ["test-1", "test-2", "test-3"])
        fun `create repository with valid input`(repositoryName: String) {
            val repository: GHRepository = gitHub.createRepository(repositoryName).create()

            repository.name shouldEqual repositoryName
        }
    }

    @Nested
    inner class ExtensionMethods {
        fun `create branches with valid input`() {}
        fun `create labels with valid input`() {}
        fun `create milestones with valid input`() {}
        fun `create issues with valid input`() {}
        fun `create projects with valid input`() {}
    }
}