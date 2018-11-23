import Configuration.BRANCHES
import Configuration.ISSUES
import Configuration.LABELS
import Configuration.MILESTONES
import Configuration.REPOSITORY_INIT
import Configuration.SCRIPT_ACTION
import com.immanuelqrw.gitbase.domain.*
import org.kohsuke.github.*
import com.immanuelqrw.gitbase.models.*

/**
 * Creates standardized GitHub repository with related issues, labels, milestones, branches, etc.
 *
 * Generates the structure of a standardized GitHub repository by instantiating pieces found through configuration
 *
 * @param args Input arguments, currently unused
 */
fun main(args: Array<String>) {
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
            repositoryInit = REPOSITORY_INIT
        )
    } else {
        client.getRepository(REPOSITORY_INIT.name)
    }

//    // TODO Fix functionality
//    val githubBranches: List<GHRef> = if (createBranches) {
//        githubRepository.createRefs(branches = BRANCHES)
//    } else {
//        BRANCHES.map { branch ->
//            githubRepository.getRef(branch.name)
//        }
//    }
//
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