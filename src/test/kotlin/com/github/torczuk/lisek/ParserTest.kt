package com.github.torczuk.lisek

import com.github.torczuk.lisek.model.Atom
import com.github.torczuk.lisek.model.List
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParserTest {
    private val parser = Parser()

    @Test
    fun `should parse empty list`() {
        val expression = " ( ) "

        val tokens = parser.parse(expression)

        assertThat(tokens).isEqualTo(List())
    }

    @Test
    fun `should parse atom`() {
        val expression = "1"

        val tokens = parser.parse(expression)

        assertThat(tokens).isEqualTo(Atom("1"))
    }

    @Test
    fun `should parse list expression`() {
        val expression = "(+ 1 2)"

        val tokens = parser.parse(expression)

        assertThat(tokens).isEqualTo(
            List(
                Atom("+"),
                Atom("1"),
                Atom("2")
            )
        )
    }

    @Test
    fun `should parse list containings empty lists`() {
        val expression = "(() () ())"

        val tokens = parser.parse(expression)

        assertThat(tokens).isEqualTo(
            List(
                List(),
                List(),
                List()
            )
        )
    }

    @Test
    fun `should parse lambda expression`() {
        val expression = "((lambda (arg) (+ arg 1 )) 5)"

        val tokens = parser.parse(expression)

        assertThat(tokens).isEqualTo(
            List(
                List(
                    Atom("lambda"),
                    List(Atom("arg")),
                    List(Atom("+"), Atom("arg"), Atom("1"))
                ),
                Atom("5")
            )
        )
    }

}