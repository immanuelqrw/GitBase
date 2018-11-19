import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.immanuelqrw.gitbase.models.*
import java.io.InputStream
import java.nio.file.Path
import java.nio.file.Paths

object Configuration {
    private val MAPPER = jacksonObjectMapper()

    private fun loadResource(name: String): InputStream {
        return object {}.javaClass.getResourceAsStream(name)
    }

    private inline fun <reified T> loadResourceFromFile(resourceName: String): T {
        val inputStream: InputStream = loadResource(name = resourceName)
        return parseObjectFromInput(inputStream = inputStream, objectMapper = MAPPER)
    }

    private inline fun <reified T> parseObjectFromInput(inputStream: InputStream, objectMapper: ObjectMapper): T {
        return objectMapper.readValue(inputStream, T::class.java)
    }


    private val GIT_BASE_CONFIG: GitBaseConfig = loadResourceFromFile("git-base-config.yaml")

    val BRANCHES: List<Branch> = GIT_BASE_CONFIG.branches
    val USERS: List<User> = GIT_BASE_CONFIG.users
    val PROJECTS: List<Project> = GIT_BASE_CONFIG.projects
    val LABELS: List<Label> = GIT_BASE_CONFIG.labels
    val MILESTONES: List<Milestone> = GIT_BASE_CONFIG.milestones
    val ISSUES: List<Issue> = GIT_BASE_CONFIG.issues


    private val ROOT_DIR: Path = Paths.get(".").toAbsolutePath()
    private val CONFIG_DIR: Path = ROOT_DIR.resolve("config")

    private val REPOSITORY_INIT_PATH: Path = CONFIG_DIR.resolve("repository-init.yaml")
    val REPOSITORY_INIT: RepositoryInit = parseObjectFromInput(REPOSITORY_INIT_PATH.toFile().inputStream(), MAPPER)

    private val SCRIPT_ACTION_PATH: Path = CONFIG_DIR.resolve("script-action.yaml")
    val SCRIPT_ACTION: ScriptAction = parseObjectFromInput(SCRIPT_ACTION_PATH.toFile().inputStream(), MAPPER)
}