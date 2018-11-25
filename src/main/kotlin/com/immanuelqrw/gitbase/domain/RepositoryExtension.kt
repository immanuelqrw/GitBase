package com.immanuelqrw.gitbase.domain

import com.immanuelqrw.gitbase.models.*
import org.kohsuke.github.*

/**
 * Builds repository builder initialized by [repositoryInit]
 *
 * @param client GitHub connection
 * @return Repository Builder object
 */
fun buildRepository(client: GitHub, repositoryInit: RepositoryInit): GHCreateRepositoryBuilder {
    val repositoryBuilder: GHCreateRepositoryBuilder = client.createRepository("${repositoryInit.name}-${repositoryInit.type.name.toLowerCase()}")

    return repositoryBuilder
        .description(repositoryInit.description)
        .private_(repositoryInit.isPrivate)
        .autoInit(repositoryInit.hasEnabledAutoInit)
        .downloads(repositoryInit.hasEnabledDownloads)
        .gitignoreTemplate(repositoryInit.language)
        .licenseTemplate(repositoryInit.license)
}

/**
 * Creates repository on GitHub initialized by [repositoryInit]
 *
 * @param client GitHub connection
 * @return Repository created
 */
fun createRepository(
    client: GitHub,
    repositoryInit: RepositoryInit
): GHRepository {
    val repositoryBuilder: GHCreateRepositoryBuilder = buildRepository(client = client, repositoryInit = repositoryInit)

    return repositoryBuilder.create()
}


/**
 * Creates branches on GitHub
 *
 * Parses [branches] to create GitHub branches
 *
 * @receiver [GHRepository]
 * @param branches List of branches to create
 * @return List of references created
 */
fun GHRepository.createRefs(branches: List<Branch>): List<GHRef> {
    return branches.map { branch ->
        // TODO Get most recent commit
        val hash: String = this.getRef("heads/master").`object`.sha
        this.createRef("refs/${branch.name}", hash)
    }
}


/**
 * Creates labels on GitHub
 *
 * Parses [labels] to create GitHub labels
 *
 * @receiver [GHRepository]
 * @param labels List of labels to create
 * @return List of labels created
 */
fun GHRepository.createLabels(labels: List<Label>): List<GHLabel> {
    return labels.map { label ->
        this.createLabel(label.name, label.color)
    }
}


/**
 * Creates milestones on GitHub
 *
 * Parses [milestones] to create GitHub milestones
 *
 * @receiver [GHRepository]
 * @param milestones List of milestones to create
 * @return Map from local milestone to GitHub milestone
 */
fun GHRepository.createMilestones(milestones: List<Milestone>): Map<Milestone, GHMilestone> {
    return milestones.associate { milestone ->
        milestone to this.createMilestone(milestone.name, milestone.description)
    }
}


/**
 * Creates issues on GitHub
 *
 * Parses [issues] to create GitHub issues
 *
 * @receiver [GHRepository]
 * @param issues List of issues to create
 * @param type [RepositoryType] to create
 * @param milestoneMapping Map from local milestone to GitHub milestone
 * @return List of issues created
 */
fun GHRepository.createIssues(
    issues: List<Issue>,
    type: RepositoryType,
    milestoneMapping: Map<Milestone, GHMilestone>
): List<GHIssue> {
    return issues
        .filter { issue -> type in issue.repositoryTypes }
        .map { issue ->
        val issueBuilder: GHIssueBuilder = this.createIssue(issue.title)

        issueBuilder
            .assignee(issue.assignee?.name)
            .body(issue.description)
            .milestone(milestoneMapping[issue.milestone])

        issue.labels?.forEach { label ->
            issueBuilder.label(label.name)
        }

        val githubIssue: GHIssue = issueBuilder.create()

        githubIssue
    }
}