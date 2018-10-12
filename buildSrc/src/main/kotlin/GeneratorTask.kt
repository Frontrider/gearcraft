import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

open class GeneratorTask {

    @Internal
    var entries = ArrayList<RecipeTypeEntry>()

    @TaskAction
    open fun generate() {

    }

    @Input
    fun addRecipes(vararg entires:RecipeTypeEntry) {
        this.entries.addAll(entires)
    }
}