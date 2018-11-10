// Login
// Create Repository -- specify language for gitignore and license
// Create Labels (if needed)
// Create Milestones
// Create Projects [if functionality] -- no functionality, add in future release
// Create beginning issues and attach labels and milestones to them depending on template
import org.kohsuke.github.*
import com.immanuelqrw.gitbase.configuration.*
import com.immanuelqrw.gitbase.models.*

fun createRepository(
  client: Github,
  name: String,
  description: String,
  isPrivate: Boolean,
  hasEnabledAutoInit: Boolean,
  hasEnabledDownloads: Boolean,
  language: String,
  license: String
): GHRepository {
  val repositoryBuilder: GHCreateRepositoryBuilder = client.createRepository(name = name)

  repositoryBuilder
    .description(description)
    .private_(isPrivate)
    .autoInit(hasEnabledAutoInit)
    .downloads(hasEnabledDownloads)
    .gitignoreTemplate(language)
    .licenseTemplate(license)

  return repositoryBuilder.create()
}


// Multiple due to creating defaults
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
  val githubIssues: MutableList<GHIssue> = mutableListOf()

  issues.forEach { issue ->
    val issueBuilder: GHIssueBuilder = this.createIssue(issue.title)
    issueBuilder
      .assignee(issue.assignee?.name)
      .body(issue.description)
      .milestone(milestoneMapping[issue.milestone])

    issue.labels?.forEach { label ->
      issueBuilder.label(label.name)
    }

    val githubIssue: GHIssue = issueBuilder.create()

    githubIssues.add(githubIssue)
  }

  return githubIssues.toList()
}


fun main(args: Array<String>) {
  // TODO add ~/.github credentials file
  val client: Github = Github.connect()
  val githubRepository: GHRepository = createRepository(
    client = client,
    name = REPOSITORY_INIT.name,
    isPrivate = REPOSITORY_INIT.isPrivate,
    hasEnabledAutoInit = REPOSITORY_INIT.hasEnabledAutoInit,
    hasEnabledDownloads = REPOSITORY_INIT.hasEnabledDownloads,
    description = REPOSITORY_INIT.description,
    language = REPOSITORY_INIT.language,
    license = REPOSITORY_INIT.license
  )

  // TODO Create Users?
  // TODO Create Projects

  val githubLabels: List<GHLabel> = githubRepository.createLabels(labels = LABELS)
  val milestoneMapping: Map<Milestone, GHMilestone> = githubRepository.createMilestones(milestones = MILESTONES)
  val githubIssues: List<GHIssue> = githubRepository.createIssues(issues = ISSUES, milestoneMapping = milestoneMapping)
}