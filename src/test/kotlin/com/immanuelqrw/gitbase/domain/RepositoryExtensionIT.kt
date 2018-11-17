package com.immanuelqrw.gitbase.domain

import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class)
class RepositoryExtensionIT {
    @Nested
    inner class BaseMethods {
        fun `create repository with valid input`() {}
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