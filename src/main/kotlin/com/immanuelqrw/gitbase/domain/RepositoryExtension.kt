package com.immanuelqrw.gitbase.domain

import com.immanuelqrw.gitbase.models.Branch
import com.immanuelqrw.gitbase.models.Issue
import com.immanuelqrw.gitbase.models.Label
import com.immanuelqrw.gitbase.models.Milestone
import org.kohsuke.github.*


fun createRepository(
    client: GitHub,
    name: String,
    description: String,
    isPrivate: Boolean,
    hasEnabledAutoInit: Boolean,
    hasEnabledDownloads: Boolean,
    language: String,
    license: String
): GHRepository {
    val repositoryBuilder: GHCreateRepositoryBuilder = client.createRepository(name)

    repositoryBuilder
        .description(description)
        .private_(isPrivate)
        .autoInit(hasEnabledAutoInit)
        .downloads(hasEnabledDownloads)
        .gitignoreTemplate(language)
        .licenseTemplate(license)

    return repositoryBuilder.create()
}


fun GHRepository.createRefs(branches: List<Branch>): List<GHRef> {
    return branches.map { branch ->
        // TODO Get most recent commit
        val hash: String = this.getBranch("origin/master").shA1
        this.createRef(branch.name, hash)
    }
}


fun GHRepository.createLabels(labels: List<Label>): List<GHLabel> {
    return labels.map { label ->
        this.createLabel(label.name, label.color)
    }
}


fun GHRepository.createMilestones(milestones: List<Milestone>): Map<Milestone, GHMilestone> {
    return milestones.associate { milestone ->
        milestone to this.createMilestone(milestone.name, milestone.description)
    }
}


fun GHRepository.createIssues(issues: List<Issue>, milestoneMapping: Map<Milestone, GHMilestone>): List<GHIssue> {
    return issues.map { issue ->
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