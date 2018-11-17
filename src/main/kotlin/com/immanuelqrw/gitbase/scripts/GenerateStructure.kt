import com.immanuelqrw.gitbase.domain.*
import org.kohsuke.github.*
import com.immanuelqrw.gitbase.models.*


fun main(args: Array<String>) {
    // TODO add ~/.github credentials file
    val client: GitHub = GitHub.connect()

    val (
        createRepository,
        createBranches,
        createUsers,
        createProjects,
        createLabels,
        createMilestones,
        createIssues
    ) = SCRIPT_ACTION

    val githubRepository: GHRepository = if (createRepository) {
        createRepository(
            client = client,
            name = REPOSITORY_INIT.name,
            isPrivate = REPOSITORY_INIT.isPrivate,
            hasEnabledAutoInit = REPOSITORY_INIT.hasEnabledAutoInit,
            hasEnabledDownloads = REPOSITORY_INIT.hasEnabledDownloads,
            description = REPOSITORY_INIT.description,
            language = REPOSITORY_INIT.language,
            license = REPOSITORY_INIT.license
        )
    } else {
        client.getRepository(REPOSITORY_INIT.name)
    }

    val githubBranches: List<GHRef> = if (createBranches) {
        githubRepository.createRefs(branches = BRANCHES)
    } else {
        BRANCHES.map { branch ->
            githubRepository.getRef(branch.name)
        }
    }

    // TODO Create Users?
    // TODO Create Projects

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

    val githubIssues: List<GHIssue> = if(createIssues) {
        githubRepository.createIssues(issues = ISSUES, milestoneMapping = milestoneMapping)
    } else {
        githubRepository.getIssues(GHIssueState.ALL)
    }
}