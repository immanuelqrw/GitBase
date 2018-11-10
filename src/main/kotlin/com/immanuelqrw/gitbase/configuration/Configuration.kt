import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.immanuelqrw.gitbase.models.*
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

// TODO Get list of all languages and licenses to validates

val MAPPER = jacksonObjectMapper()

// TODO Get True path of directory
val ROOT_DIR: Path = Paths.get(".").toAbsolutePath()
val CONFIG_DIR: Path = ROOT_DIR.resolve("config")

// TODO Create super data class for everything to load from
inline fun <reified T> loadFromFile(path: Path): T {
    return Files.newBufferedReader(path).use { bufferedReader ->
        MAPPER.readValue(bufferedReader, T::class.java)
    }
}


val GIT_BASE_CONFIG_PATH: Path = CONFIG_DIR.resolve("git-base-config.yaml")
val GIT_BASE_CONFIG: GitBaseConfig = loadFromFile(GIT_BASE_CONFIG_PATH)

val USERS: List<User> = GIT_BASE_CONFIG.users
val PROJECTS: List<Project> = GIT_BASE_CONFIG.projects
val LABELS: List<Label> = GIT_BASE_CONFIG.labels
val MILESTONES: List<Milestone> = GIT_BASE_CONFIG.milestones
val ISSUES: List<Issue> = GIT_BASE_CONFIG.issues


val REPOSITORY_INIT_PATH: Path = CONFIG_DIR.resolve("repository-init.yaml")
val REPOSITORY_INIT: RepositoryInit = loadFromFile(REPOSITORY_INIT_PATH)