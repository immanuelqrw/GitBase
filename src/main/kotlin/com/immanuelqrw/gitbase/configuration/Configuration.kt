import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.immanuelqrw.gitbase.models.*
import java.io.InputStream
import java.nio.file.Path
import java.nio.file.Paths


val MAPPER = jacksonObjectMapper()

fun loadResource(name: String): InputStream {
    return object {}.javaClass.getResourceAsStream(name)
}

inline fun <reified T> loadResourceFromFile(resourceName: String): T {
    val inputStream: InputStream = loadResource(name = resourceName)
    return parseObjectFromInput(inputStream = inputStream)
}

inline fun <reified T> parseObjectFromInput(inputStream: InputStream): T {
    return MAPPER.readValue(inputStream, T::class.java)
}


val GIT_BASE_CONFIG: GitBaseConfig = loadResourceFromFile("git-base-config.yaml")

val BRANCHES: List<Branch> = GIT_BASE_CONFIG.branches
val USERS: List<User> = GIT_BASE_CONFIG.users
val PROJECTS: List<Project> = GIT_BASE_CONFIG.projects
val LABELS: List<Label> = GIT_BASE_CONFIG.labels
val MILESTONES: List<Milestone> = GIT_BASE_CONFIG.milestones
val ISSUES: List<Issue> = GIT_BASE_CONFIG.issues


val ROOT_DIR: Path = Paths.get(".").toAbsolutePath()
val CONFIG_DIR: Path = ROOT_DIR.resolve("config")

val REPOSITORY_INIT_PATH: Path = CONFIG_DIR.resolve("repository-init.yaml")
val REPOSITORY_INIT: RepositoryInit = parseObjectFromInput(REPOSITORY_INIT_PATH.toFile().inputStream())

val SCRIPT_ACTION_PATH: Path = CONFIG_DIR.resolve("script-action.yaml")
val SCRIPT_ACTION: ScriptAction = parseObjectFromInput(SCRIPT_ACTION_PATH.toFile().inputStream())