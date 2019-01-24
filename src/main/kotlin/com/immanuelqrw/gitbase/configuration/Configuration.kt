import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.*
import com.immanuelqrw.gitbase.models.*
import java.io.InputStream
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Configuration
 *
 * Stores configuration values and methods needed to acquire them
 */
object Configuration {
    /**
     * Object Mapper used for parsing JSON into data classes
     */
    // TODO Fix Expansion of YAML templates -- or write conversion method
    private val MAPPER = ObjectMapper(YAMLFactory()).registerKotlinModule()

    /**
     * Loads resource from filename
     *
     * Using filename finds resource and creates usable stream
     *
     * @param name Name of resource file
     * @return InputStream of resource
     */
    private fun loadResource(name: String): InputStream {
        return object {}.javaClass.getResourceAsStream(name)
    }

    /**
     * Loads resource into data class
     *
     * Parses resource file and generates data class instance
     *
     * @param resourceName Name of resource file
     * @return Data class instance
     */
    private inline fun <reified T> loadResourceFromFile(resourceName: String): T {
        val inputStream: InputStream = loadResource(name = resourceName)
        return parseObjectFromInput(inputStream = inputStream, objectMapper = MAPPER)
    }

    /**
     * Creates data class instance from input
     *
     * Parses inputStream and generates data class instance
     *
     * @param inputStream InputStream of data file
     * @param objectMapper Object Mapper used to parse inputStream
     * @return Data class instance
     */
    private inline fun <reified T> parseObjectFromInput(inputStream: InputStream, objectMapper: ObjectMapper): T {
        return objectMapper.readValue(inputStream)
    }


    /**
     * GitBase Configuration data class instance loaded from data file
     */
    private val GIT_BASE_CONFIG: GitBaseConfig = loadResourceFromFile("git-base-config.yaml")

    /**
     * Configuration constants for repository
     */
    val BRANCHES: List<Branch> = GIT_BASE_CONFIG.branches
    val USERS: List<User> = GIT_BASE_CONFIG.users
    val PROJECTS: List<Project> = GIT_BASE_CONFIG.projects
    val LABELS: List<Label> = GIT_BASE_CONFIG.labels
    val MILESTONES: List<Milestone> = GIT_BASE_CONFIG.milestones
    val ISSUES: List<Issue> = GIT_BASE_CONFIG.issues

    /**
     * Repository Initialization class initialization from configuration file
     */
    val REPOSITORY_INIT: RepositoryInit = loadResourceFromFile("repository-init.yaml")

    /**
     * Script action class initialization from configuration file
     */
    val SCRIPT_ACTION: ScriptAction = loadResourceFromFile("script-action.yaml")
}