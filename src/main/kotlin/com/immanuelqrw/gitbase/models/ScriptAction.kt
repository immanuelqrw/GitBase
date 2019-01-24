package com.immanuelqrw.gitbase.models

/**
 * Represents actions taken with script
 *
 * @property shouldCreateRepository Whether a repository should be created
 * @property shouldCreateBranches Whether branches should be created
 * @property shouldCreateUsers Whether users should be created
 * @property shouldCreateProjects Whether projects should be created
 * @property shouldCreateLabels Whether labels should be created
 * @property shouldCreateMilestones Whether milestones should be created
 * @property shouldCreateIssues Whether issues should be created
 */
data class ScriptAction(
    val shouldCreateRepository: Boolean,
    val shouldCreateBranches: Boolean,
    val shouldCreateUsers: Boolean,
    val shouldCreateProjects: Boolean,
    val shouldCreateLabels: Boolean,
    val shouldCreateMilestones: Boolean,
    val shouldCreateIssues: Boolean
)