package com.github.torczuk.lisek

import com.github.torczuk.lisek.model.Atom
import com.github.torczuk.lisek.model.Expression
import com.github.torczuk.lisek.model.Lst

class Parser {
    companion object {
        const val leftParenthesis = "("
        const val rightParenthesis = ")"
    }

    fun parse(expression: String): Expression = parse(tokenize(expression).toMutableList())

    private fun tokenize(expression: String): List<String> {
        return expression
            .replace(leftParenthesis, " $leftParenthesis ")
            .replace(rightParenthesis, " $rightParenthesis ")
            .split(Regex("(\\s)+"))
            .map { token -> token.trim() }
            .filter { token -> token.isNotBlank() }
    }

    private fun parse(expression: MutableList<String>): Expression {
        var token = expression.removeAt(0)

        if (leftParenthesis == token) {
            val inList = mutableListOf<Expression>()
            while (token != rightParenthesis) {
                val ex = parse(expression)
                inList.add(ex)
                token = expression.first()
            }
            expression.removeAt(0)
            return Lst(inList)
        } else if (rightParenthesis == token) {
            throw RuntimeException("Illegal ')'")
        } else {
            return Atom(token)
        }
    }
}

