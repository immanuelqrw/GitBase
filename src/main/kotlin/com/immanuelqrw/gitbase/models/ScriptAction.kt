package com.immanuelqrw.gitbase.models

/**
 * Represents actions taken with script
 *
 * @property shouldCreateRepository Whether a repository should be created
 * @property shouldCreateBranches Whether branches should be created
 * @property shouldCreateUsers Whether users should be created
 * @property shouldCreateProjects Whether projects should be created
 * @property shouldCreateLabels Whether labels should be created
 * @property shouldRemoveLabels Whether labels should be removed
 * @property shouldCreateMilestones Whether milestones should be created
 * @property shouldCreateIssues Whether issues should be created
 * @property shouldRemoveIssues Whether issues should be removed
 * @property shouldCreateExtraIssues Whether extra issues should be created
 */
data class ScriptAction(
    val shouldCreateRepository: Boolean,
    val shouldCreateBranches: Boolean,
    val shouldCreateUsers: Boolean,
    val shouldCreateProjects: Boolean,
    val shouldCreateLabels: Boolean,
    val shouldRemoveLabels: Boolean,
    val shouldCreateMilestones: Boolean,
    val shouldCreateIssues: Boolean,
    val shouldRemoveIssues: Boolean,
    val shouldCreateExtraIssues: Boolean
)
