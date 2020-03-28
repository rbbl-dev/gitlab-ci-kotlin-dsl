package pcimcioch.gitlabci.dsl.include

import org.junit.jupiter.api.Test
import pcimcioch.gitlabci.dsl.DslTestBase
import pcimcioch.gitlabci.dsl.gitlabCi

internal class IncludeDslTest : DslTestBase() {

    @Test
    fun `should create empty`() {
        // given
        val testee = IncludeDsl()

        // then
        assertDsl(IncludeDsl.serializer(), testee,
                """
                    []
                """.trimIndent()
        )
    }

    @Test
    fun `should create full`() {
        // given
        val testee = IncludeDsl().apply {
            local("local 1")
            file("project 1", "file 1")
            template("template 1")
            remote("remote 1")
            local("local 2")
            file("project 2", "file 2", "ref 2")
            template("template 2")
            remote("remote 2")
        }

        // then
        assertDsl(IncludeDsl.serializer(), testee,
                """
                    - local: "local 1"
                    - project: "project 1"
                      file: "file 1"
                    - template: "template 1"
                    - remote: "remote 1"
                    - local: "local 2"
                    - project: "project 2"
                      file: "file 2"
                      ref: "ref 2"
                    - template: "template 2"
                    - remote: "remote 2"
                """.trimIndent()
        )
    }
}