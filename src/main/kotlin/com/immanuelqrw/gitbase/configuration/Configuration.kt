import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.immanuelqrw.gitbase.models.*
import java.io.InputStream


val MAPPER = jacksonObjectMapper()

fun loadResource(name: String): InputStream {
    return object {}.javaClass.getResourceAsStream(name)
}

inline fun <reified T> loadFromFile(resourceName: String): T {
    val inputStream: InputStream = loadResource(name = resourceName)
    return MAPPER.readValue(inputStream, T::class.java)
}


val GIT_BASE_CONFIG: GitBaseConfig = loadFromFile("git-base-config.yaml")

val USERS: List<User> = GIT_BASE_CONFIG.users
val PROJECTS: List<Project> = GIT_BASE_CONFIG.projects
val LABELS: List<Label> = GIT_BASE_CONFIG.labels
val MILESTONES: List<Milestone> = GIT_BASE_CONFIG.milestones
val ISSUES: List<Issue> = GIT_BASE_CONFIG.issues


val REPOSITORY_INIT: RepositoryInit = loadFromFile("repository-init.yaml")