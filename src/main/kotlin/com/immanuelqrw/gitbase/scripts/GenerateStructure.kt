package com.immanuelqrw.gitbase.scripts

import Configuration.BRANCHES
import Configuration.EXTRA_ISSUES
import Configuration.ISSUES
import Configuration.LABELS
import Configuration.MILESTONES
import Configuration.REPOSITORY_INIT
import Configuration.SCRIPT_ACTION
import com.immanuelqrw.gitbase.domain.*
import com.immanuelqrw.gitbase.models.*
import mu.KotlinLogging
import org.kohsuke.github.*

private val logger = KotlinLogging.logger {}

/**
 * Creates standardized GitHub repository with related issues, labels, milestones, branches, etc.
 *
 * Generates the structure of a standardized GitHub repository by instantiating pieces found through configuration
 */
fun main() {
    val client: GitHub = GitHubBuilder.fromPropertyFile().build();

    val (
        createRepository,
        createBranches,
        createUsers,
        createProjects,
        createLabels,
        removeLabels,
        createMilestones,
        createIssues,
        removeIssues,
        shouldCreateExtraIssues
    ) = SCRIPT_ACTION

    val githubRepository: GHRepository = if (createRepository) {
        createRepository(
            client = client,
            repositoryInit = REPOSITORY_INIT
        )
    } else {
        val repositoryName: String = if (REPOSITORY_INIT.hasSuffix) {
            "${REPOSITORY_INIT.owner}/${REPOSITORY_INIT.name}-${REPOSITORY_INIT.type}"
        } else {
            "${REPOSITORY_INIT.owner}/${REPOSITORY_INIT.name}"
        }

        logger.debug { "Repository Name: $repositoryName" }

        client.getRepository(repositoryName)
    }

    val githubBranches: List<GHRef> = if (createBranches) {
        githubRepository.createRefs(branches = BRANCHES)
    } else {
        BRANCHES.map { branch ->
            githubRepository.getRef(branch.name)
        }
    }

    logger.debug { "Repository Branches: $githubBranches" }

    // TODO Create Users?
    // TODO Create Projects

    if (removeLabels) {
        githubRepository.listLabels().forEach { label: GHLabel ->
            label.delete()
        }
    }

    val githubLabels: List<GHLabel> = if (createLabels) {
        githubRepository.createLabels(labels = LABELS)
    } else {
        LABELS.map { label ->
            githubRepository.getLabel(label.name)
        }
    }

    val milestoneMapping: Map<Milestone, GHMilestone> = if (createMilestones) {
        githubRepository.createMilestones(milestones = MILESTONES)
    } else {
        MILESTONES.withIndex().associate { (index, milestone) ->
            // TODO Test that this works for milestones
            milestone to githubRepository.getMilestone(index + 1)
        }
    }

    if (removeIssues) {
        // - Currently there is no method in the REST API to delete issues
        githubRepository.listIssues(GHIssueState.OPEN).forEach { issue: GHIssue ->
            issue.close()
        }
    }

    val githubIssues: List<GHIssue> = if (createIssues) {
        githubRepository.createIssues(issues = ISSUES, type = REPOSITORY_INIT.type, milestoneMapping = milestoneMapping)
    } else {
        githubRepository.getIssues(GHIssueState.ALL)
    }

    val extraIssues: List<GHIssue> = if (shouldCreateExtraIssues) {
        githubRepository.createIssues(issues = EXTRA_ISSUES, type = REPOSITORY_INIT.type, milestoneMapping = milestoneMapping)
    } else {
        listOf()
    }
}
