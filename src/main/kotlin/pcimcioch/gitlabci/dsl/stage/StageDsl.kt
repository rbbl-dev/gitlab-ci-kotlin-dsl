package pcimcioch.gitlabci.dsl.stage

import kotlinx.serialization.Serializable
import pcimcioch.gitlabci.dsl.DslBase
import pcimcioch.gitlabci.dsl.DslBase.Companion.addError
import pcimcioch.gitlabci.dsl.GitlabCiDslMarker
import pcimcioch.gitlabci.dsl.isEmpty

@GitlabCiDslMarker
@Serializable
class StageDsl(
        var name: String? = null
) : DslBase {

    object Default {
        const val PRE_STAGE = ".pre"
        const val POST_STAGE = ".post"
    }

    override fun validate(errors: MutableList<String>) {
        addError(errors, isEmpty(name), "[stage name='$name'] name '$name' is incorrect")
    }
}

fun createStage(name: String) = StageDsl(name)
fun createStage(block: StageDsl.() -> Unit) = StageDsl().apply(block)
fun createStage(name: String, block: StageDsl.() -> Unit) = StageDsl(name).apply(block)